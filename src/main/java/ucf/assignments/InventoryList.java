/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.TreeMap;

public class InventoryList {

    TreeMap<String, Item> itemList = new TreeMap<>();

    //Overloaded method with price as a double
    public void addItem(String serialNumber, String name, double price){

        //Create new item
        Item newItem = new Item(serialNumber, name, price);
        //Add new item to TreeMap with serial number as the key
        itemList.put(serialNumber, newItem);
    }

    //Overloaded method with price as a string with "$" symbol
    public void addItem(String serialNumber, String name, String price){

        //Create new item
        Item newItem = new Item(serialNumber, name, price);
        //Add new item to TreeMap with serial number as the key
        itemList.put(serialNumber, newItem);
    }

    public void editItem(int column, String newValue, String currentSerialNumber){

        //Update inventory list depending on which value was updated
        switch (column) {
            //Column 0 updates price
            case 0 -> //Set price of corresponding item in TreeMap to new value
                    itemList.get(currentSerialNumber).setPrice(Double.parseDouble(newValue));
            case 1 -> {
                //Get edited item from Observable List
                Item editedItem = itemList.get(currentSerialNumber);
                //Set serial number to new value
                editedItem.setSerialNumber(newValue);
                //Remove item from TreeMap
                itemList.remove(currentSerialNumber);
                //Add edited item with new serial number as its key
                itemList.put(newValue, editedItem);
            }
            case 2 -> //Set item name of corresponding item in TreeMap to new value
                    itemList.get(currentSerialNumber).setItemName(newValue);
        }
    }

    public void deleteItem(Item selectedItem){

        //Get list of keys in inventory TreeMap
        List<String> keys = itemList.keySet().stream().toList();

        //Remove item from TreeMap
        for(int i = 0; i < itemList.size(); i++){
            //If selected item is equal to item in current iteration, remove from TreeMap
            if(selectedItem == itemList.get(keys.get(i))){
                itemList.remove(keys.get(i));
                break;
            }
        }
    }

    public TreeMap<String, Item> getItemList() {
        return itemList;
    }

    public ObservableList<Item> getItemObservableList(){

        //Create Observable List to display items in TableView
        ObservableList<Item> updatedItems = FXCollections.observableArrayList();
        //Get list of keys in inventoryList map
        List<String> keys = getItemList().keySet().stream().toList();
        //Iterate through inventoryList to add items to Observable List
        for (String key : keys) {
            updatedItems.add(getItemList().get(key));
        }

        //Return observable list of items
        return updatedItems;
    }

    public ObservableList<Item> getSearchResults(String searchString){

        //Create Observable List to store search results
        ObservableList<Item> searchResults = FXCollections.observableArrayList();
        //Get list of keys of inventoryList TreeMap
        List<String> keys = getItemList().keySet().stream().toList();
        //Iterate through TreeMap using keys to check if search query matches any serial numbers or item names
        for(String key : keys){
            if (key.toLowerCase().contains(searchString.toLowerCase()) || getItemList().get(key).getItemName().toLowerCase().contains(searchString.toLowerCase())){
                //Add to search results
                searchResults.add(getItemList().get(key));
            }
        }

        //Return search results
        return searchResults;
    }
}
