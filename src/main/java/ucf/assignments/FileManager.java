/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private final File selectedFile;

    public FileManager(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    private String getFileType(){

        //Get the file extension of user selected file as a string
        return FilenameUtils.getExtension(selectedFile.toString());
    }

    public String getJSONString(InventoryList inventoryList){

        //Create new Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Parse json elements from inventory list
        JsonElement jsonElement = gson.toJsonTree(inventoryList);

        //Return json elements as string
        return gson.toJson(jsonElement);
    }

    public String getTSVString(InventoryList inventoryList){

        StringBuilder saveString = new StringBuilder();

        //Get list of keys in inventory list for iterating
        List<String> keys = inventoryList.getItemList().keySet().stream().toList();
        //Iterate through inventory list to add each item to save file string
        for (String key : keys){
            //Add price
            saveString.append(inventoryList.getItemList().get(key).getPrice());
            //Add tab char
            saveString.append("\t");
            //Add serial number
            saveString.append(inventoryList.getItemList().get(key).getSerialNumber());
            //Add tab char
            saveString.append("\t");
            //Add item name
            saveString.append(inventoryList.getItemList().get(key).getItemName());
            //Go to next line
            saveString.append("\n");
        }

        //Return save string
        return saveString.toString();
    }

    public String getHTMLString(InventoryList inventoryList){

        StringBuilder saveString = new StringBuilder();

        //Get list of keys in inventory list for iterating
        List<String> keys = inventoryList.getItemList().keySet().stream().toList();
        //Add table start tag
        saveString.append("<table>\n");
        for (String key : keys){
            //Add row and element start tag
            saveString.append("<tr>\n<td>");
            //Add price
            saveString.append(inventoryList.getItemList().get(key).getPrice());
            //Add element end and next element start tag
            saveString.append("</td>\n<td>");
            //Add serial number
            saveString.append(inventoryList.getItemList().get(key).getSerialNumber());
            //Add element end and next element start tag
            saveString.append("</td>\n<td>");
            //Add item name
            saveString.append(inventoryList.getItemList().get(key).getItemName());
            //Add element and row end tag
            saveString.append("</td>\n</tr>\n");
        }
        //Add table end tag
        saveString.append("</table>");

        //Return save string
        return saveString.toString();
    }

    public void save(InventoryList inventoryList){

        //Get file extension of selected file
        String fileType = getFileType();
        //Get save string according to file type
        String saveString = switch (fileType) {
            case "json" -> getJSONString(inventoryList);
            case "txt" -> getTSVString(inventoryList);
            case "html" -> getHTMLString(inventoryList);
            default -> "";
        };

        try {
            //Open file
            FileWriter fileWriter = new FileWriter(selectedFile);
            //Write json string to file
            fileWriter.write(saveString);
            //Close file
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public InventoryList open(){

        //Get contents of file as string
        String fileData = "";

        try {
            fileData = getFileData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Get file extension of selected file
        String fileType = getFileType();

        //Get InventoryList object from file data string according to file type
        InventoryList inventoryList;

        inventoryList = switch (fileType) {
            case "json" -> parseJSON(fileData);
            case "txt" -> parseTSV(fileData);
            case "html" -> parseHTML(fileData);
            default -> new InventoryList();
        };

        //Return inventory list
        return inventoryList;
    }

    public String getFileData() throws FileNotFoundException {

        //Create new Scanner
        StringBuilder fileData = new StringBuilder();
        Scanner input = new Scanner(selectedFile);

        //Scan in lines from file while there is data remaining
        while(input.hasNextLine()){
            fileData.append(input.nextLine());
            fileData.append("\n");
        }
        //Return file data string
        return fileData.toString();
    }


    public InventoryList parseJSON(String fileData){

        //Create new GSON object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Return InventoryList parsed from JSON data
        return gson.fromJson(fileData, InventoryList.class);
    }

    public InventoryList parseHTML(String fileData){

        InventoryList inventoryList = new InventoryList();

        //Split file data string into individual lines
        String[] itemArray = fileData.split("\n");

        //Iterate through each line of file data
        for (int i = 1; i < itemArray.length; i++){
            //If current line is the start of row, the next three lines are object data
            if (itemArray[i].equals("<tr>")){
                //Get serial number
                String serialNumber = itemArray[i+2].substring(4, itemArray[i+2].length() - 5);
                //Get item name
                String itemName = itemArray[i+3].substring(4, itemArray[i+3].length() - 5);
                //Get price
                String price = itemArray[i+1].substring(4, itemArray[i+1].length() - 5);
                //Add new item to inventory list
                inventoryList.addItem(serialNumber, itemName, price);
                //Advance counter to parse next object
                i += 4;
            }
        }

        //Return inventory list
        return inventoryList;
    }

    public InventoryList parseTSV(String fileData){

        InventoryList inventoryList = new InventoryList();

        //Split file data string into individual lines
        String[] itemArray = fileData.split("\n");

        //Iterate through each line of data
        for (String s : itemArray) {
            //Split each line into serial number, item name, and price
            String[] itemDataArray = s.split("\t");
            //Add new item to inventory list
            for (int j = 0; j < itemDataArray.length; j++) {
                inventoryList.addItem(itemDataArray[1], itemDataArray[2], itemDataArray[0]);
            }
        }

        //Return inventory list
        return inventoryList;
    }
}