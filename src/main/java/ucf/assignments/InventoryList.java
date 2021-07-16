/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

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

}
