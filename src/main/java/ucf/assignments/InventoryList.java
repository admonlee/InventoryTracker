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

    public void addItem(String serialNumber, String name, double price){
        //Create new item
        Item newItem = new Item(serialNumber, name, price);
        //Add new item to TreeMap with serial number as the key
        itemList.put(serialNumber, newItem);
    }

    public TreeMap<String, Item> getItemList() {
        return itemList;
    }

    public ObservableList<Item> getCompleteInventoryList(){

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
