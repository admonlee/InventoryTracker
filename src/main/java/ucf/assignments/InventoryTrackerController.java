/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InventoryTrackerController {

    @FXML private TextField serialNumberInputField;
    @FXML private TextField itemNameInputField;
    @FXML private TextField priceInputField;
    private InventoryList inventoryList = new InventoryList();

    private ObservableList<Item> displayedItems = FXCollections.observableArrayList();


    @FXML
    public void addItemButtonClicked(){

        //Get user inputs from text fields
        String inputSerialNumber = serialNumberInputField.getText();
        String inputItemName = itemNameInputField.getText();
        String inputPrice = priceInputField.getText();

    }

    public void addItem(String serialNumber, String itemName, double price){

        //Call inventoryList's addItem method to add the new item
        inventoryList.addItem(serialNumber, itemName, price);
    }


}
