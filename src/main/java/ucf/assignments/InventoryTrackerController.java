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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class InventoryTrackerController {

    @FXML private TextField serialNumberInputField;
    @FXML private TextField itemNameInputField;
    @FXML private TextField priceInputField;
    @FXML private TextField searchBar;
    @FXML private TableColumn<Item, String> serialNumberColumn;
    @FXML private TableColumn<Item, String> itemNameColumn;
    @FXML private TableColumn<Item, String> priceColumn;
    @FXML private TableView<Item> inventoryTableView;
    private Stage stage;

    private ObservableList<Item> displayedItems = FXCollections.observableArrayList();
    private final InventoryList inventoryList = new InventoryList();
    private final Validator validator = new Validator();

    //Variable to store the serial number of item being edited to access TreeMap
    private String currentSerialNumber;

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
            addItem(inputSerialNumber, inputItemName, inputPrice);
            //Update table view with observable list with searchBarTyped to only show search results if applicable,
            //otherwise all results shown
            searchBarTyped();
            //Clear text fields
            clearTextFields();
        }
        else{
            //Set warning message according to which input is invalid
            if(!validSerialNumber){
                warningDialogHandler(1);
            }
            else if(!validInputName){
                warningDialogHandler(2);
            }
            else{
                warningDialogHandler(0);
            }
        }
    }

    public void addItem(String inputSerialNumber, String inputItemName, String inputPrice){

        //Call inventoryList's addItem method
        inventoryList.addItem(inputSerialNumber, inputItemName, Double.parseDouble(inputPrice));
        //Get inventory list as observable list
        displayedItems = getItemObservableList();
    }

    public ObservableList<Item> getItemObservableList(){

        return inventoryList.getItemObservableList();
    }

    private void updateTableView(ObservableList<Item> displayedItems){

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

    private void clearTextFields(){
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
            displayedItems = getItemObservableList();
            //Update table view with observable list with searchBarTyped to only show search results if applicable,
            //otherwise all results shown
            searchBarTyped();
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

    @FXML
    public void editStart(){

        //Get the item that user wants to edit
        int selectedItemIndex = inventoryTableView.getSelectionModel().getSelectedIndex();
        Item selectedItem = displayedItems.get(selectedItemIndex);

        //Store the current serial number of item
        currentSerialNumber = selectedItem.getSerialNumber();
    }

    @FXML
    public void editCommit(TableColumn.CellEditEvent<Item, String> event){

        //Gets the row and column of the edited cell
        int row = event.getTablePosition().getRow();
        int column = event.getTablePosition().getColumn();

        //Get new values from table
        String newValue = event.getNewValue();

        //Validate the format of the input data according to the column number
        boolean validEdit = false;
        switch (column) {
            case 0 -> validEdit = validator.validatePrice(newValue);
            case 1 -> validEdit = validator.validateSerialNumber(newValue, inventoryList.getItemList());
            case 2 -> validEdit = validator.validateItemName(newValue);
        }

        //If user edit is valid, commit the edit into TreeMap
        if (validEdit){
            editItem(column, row, newValue, currentSerialNumber);
        }
        //If edit is invalid display warning dialog box
        else{
            warningDialogHandler(column);
        }
        //Update TableView to reflect changes if edit is valid or revert to original value if edit is invalid
        updateTableView(displayedItems);
    }

    private void warningDialogHandler(int category){

        //Set warning message according to which input is invalid
        String warningMessage = "";

        switch (category) {
            case 0 -> warningMessage = "Price must be number larger than 0.";
            case 1 -> warningMessage = "Please enter a unique alphanumeric serial number of 10 characters.";
            case 2 -> warningMessage = "Item name must be between 2 and 256 characters long.";
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

    public void editItem(int column, int row, String newValue, String currentSerialNumber){

        //Update inventory list depending on which value was updated
        switch (column) {
            //Column 0 updates price
            case 0 -> {
                //Set price of corresponding item in TreeMap to new value
                inventoryList.getItemList().get(currentSerialNumber).setPrice(Double.parseDouble(newValue));
                //Update displayedItems Observable List
                displayedItems = getItemObservableList();
            }
            case 1 -> {
                //Get edited item from Observable List
                Item editedItem = displayedItems.get(row);
                //Set serial number to new value
                editedItem.setSerialNumber(newValue);
                //Remove item from TreeMap
                inventoryList.getItemList().remove(currentSerialNumber);
                //Add edited item with new serial number as its key
                inventoryList.getItemList().put(newValue, editedItem);
                //Update displayedItems Observable List
                displayedItems = getItemObservableList();
            }
            case 2 -> {
                //Set item name of corresponding item in TreeMap to new value
                inventoryList.getItemList().get(currentSerialNumber).setItemName(newValue);
                //Update displayedItems Observable List
                displayedItems = getItemObservableList();
            }
        }
    }

    @FXML
    public void searchBarTyped(){

        //Set displayedItems to search results if search query is provided, otherwise set to display all items
        if (searchBar.getText().equals("")){
            displayedItems = getItemObservableList();
        }
        else{
            String searchString = searchBar.getText();
            displayedItems = inventoryList.getSearchResults(searchString);
        }
        //Update table with either search results or full list
        updateTableView(displayedItems);
    }

    public InventoryList getInventoryList(){
        return inventoryList;
    }

    @FXML
    public void openButtonClicked(){

    }

    @FXML
    public void saveButtonClicked(){

        //Create a new file chooser and set it to show txt, html, and json files
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TSV Files", "*.txt"),
                new FileChooser.ExtensionFilter("HTML Files", "*.html"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        //Get location and file type using file chooser
        File selectedFile = fileChooser.showSaveDialog(stage);

        //Create new fileManager object
        FileManager fileManager = new FileManager(selectedFile);
        //If user selects a file, save the file
        if (selectedFile != null){
            fileManager.save(inventoryList);
        }
    }
}