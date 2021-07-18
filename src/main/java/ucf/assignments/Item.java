/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;


public class Item {

    private String serialNumber;
    private String itemName;
    private String price;

    //Overloaded constructor that takes price as a double
    public Item(String serialNumber, String itemName, double price) {
        this.serialNumber = serialNumber;
        this.itemName = itemName;
        this.price = String.format("$%.2f", price);
    }

    //Overloaded constructor that takes price as a string with a "$" symbol
    public Item(String serialNumber, String itemName, String price) {
        this.serialNumber = serialNumber;
        this.itemName = itemName;
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    //Overloaded setter for price as a double
    public void setPrice(double price) {
        this.price = String.format("$%.2f", price);
    }

    //Overloaded setter for price as a string with a "$" symbol
    public void setPrice(String price) {
        this.price = price;
    }

}
