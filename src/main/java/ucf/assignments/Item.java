/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

public class Item {

    private String serialNumber;
    private String itemName;
    private double price;

    public Item(String serialNumber, String itemName, double price) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
