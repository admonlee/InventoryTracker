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
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
            //Add serial number
            saveString.append(inventoryList.getItemList().get(key).getSerialNumber());
            //Add tab char
            saveString.append("\t");
            //Add item name
            saveString.append(inventoryList.getItemList().get(key).getItemName());
            //Add tab char
            saveString.append("\t");
            //Add price
            saveString.append(inventoryList.getItemList().get(key).getPrice());
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
            //Add serial number
            saveString.append(inventoryList.getItemList().get(key).getSerialNumber());
            //Add element end and next element start tag
            saveString.append("</td>\n<td>");
            //Add item name
            saveString.append(inventoryList.getItemList().get(key).getItemName());
            //Add element end and next element start tag
            saveString.append("</td>\n<td>");
            //Add price
            saveString.append(inventoryList.getItemList().get(key).getPrice());
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
}
