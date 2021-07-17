/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class InventoryTrackerController {

    @FXML private TextField serialNumberInputField;
    @FXML private TextField itemNameInputField;
    @FXML private TextField priceInputField;
    @FXML private TableColumn<Item, String> serialNumberColumn;
    @FXML private TableColumn<Item, String> itemNameColumn;
    @FXML private TableColumn<Item, String> priceColumn;
    @FXML private TableView<Item> inventoryTableView;

    private ObservableList<Item> displayedItems = FXCollections.observableArrayList();
    private final InventoryList inventoryList = new InventoryList();
    private final Validator validator = new Validator();

    @FXML
    public void addItemButtonClicked(){

        //Get user inputs from text fields
        String inputSerialNumber = serialNumberInputField.getText();
        String inputItemName = itemNameInputField.getText();
        String inputPrice = priceInputField.getText();

        //Check if serial number, input name, and price are valid inputs
        boolean validInputName = validator.validateItemName(inputItemName);
        boolean validSerialNumber = validator.validateSerialNumber(inputSerialNumber, inventoryList.getItemList());
        boolean validPrice = validator.validatePrice(inputPrice);

        //If all inputs are valid, add items to inventory list
        if (validPrice && validSerialNumber && validInputName){
            //Add item to inventory list
            addItems(inputSerialNumber, inputItemName, inputPrice);
            //Get inventory list as observable list
            displayedItems = getDisplayedItemsList();
            //Update table view with observable list
            updateTableView(displayedItems);
            //Clear text fields
            clearTextFields();
        }
        else{
            String warningMessage;
            //Set warning message according to which input is invalid
            if(!validSerialNumber){
                warningMessage = "Please enter a unique alphanumeric serial number of 10 characters.";
            }
            else if(!validInputName){
                warningMessage = "Item name must be between 2 and 256 characters long.";
            }
            else{
                warningMessage = "Price must be number larger than 0.";
            }
            //Create new alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //Set title, header, and content text of alert dialog box
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid input.");
            alert.setContentText(warningMessage);
            //Show alert dialog box
            alert.showAndWait();
        }
    }

    public void addItems(String inputSerialNumber, String inputItemName, String inputPrice){

        //Call inventoryList's addItem method
        inventoryList.addItem(inputSerialNumber, inputItemName, Double.parseDouble(inputPrice));
    }

    public ObservableList<Item> getDisplayedItemsList(){

        //Create Observable List to display items in TableView
        ObservableList<Item> updatedItems = FXCollections.observableArrayList();
        //Get list of keys in inventoryList map
        List<String> keys = inventoryList.getItemList().keySet().stream().toList();
        //Iterate through inventoryList to add items to Observable List
        for (String key : keys) {
            updatedItems.add(inventoryList.getItemList().get(key));
        }

        //Return observable list of items
        return updatedItems;
    }

    public void updateTableView(ObservableList<Item> displayedItems){

        //Set the table columns to display the serial number, item name, and price
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Set up cell factories so that table entries are editable
        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        itemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Set TableView to display items from Observable List
        inventoryTableView.setItems(displayedItems);
    }

    public void clearTextFields(){
        //Clear serial number input text field
        serialNumberInputField.clear();
        //Clear item name input text field
        itemNameInputField.clear();
        //Clear price input text field
        priceInputField.clear();
    }

    @FXML
    public void deleteButtonClicked(){

        //Get index of selected item
        int selectedItemIndex = inventoryTableView.getSelectionModel().getSelectedIndex();

        //If an item is selected, selectedItemIndex will be >= 0
        if (selectedItemIndex > -1){
            //Get selected item from Observable List
            Item selectedItem = displayedItems.get(selectedItemIndex);
            //Delete selected item
            deleteItem(selectedItem);
            //Get updated inventory list as observable list
            displayedItems = getDisplayedItemsList();
            //Update table view with observable list
            updateTableView(displayedItems);
        }
    }

    public void deleteItem(Item selectedItem){

        //Get list of keys in inventory TreeMap
        List<String> keys = inventoryList.getItemList().keySet().stream().toList();

        //Remove item from TreeMap
        for(int i = 0; i < inventoryList.getItemList().size(); i++){
            //If selected item is equal to item in current iteration, remove from TreeMap
            if(selectedItem == inventoryList.getItemList().get(keys.get(i))){
                inventoryList.getItemList().remove(keys.get(i));
                break;
            }
        }
    }
}
