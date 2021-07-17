/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;


public class Item {

    private String serialNumber;
    private String itemName;
    private String price;

    public Item(String serialNumber, String itemName, Double price) {
        this.serialNumber = serialNumber;
        this.itemName = itemName;
        this.price = String.format("$%.2f", price);
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

    public void setPrice(String price) {
        this.price = price;
    }

}
