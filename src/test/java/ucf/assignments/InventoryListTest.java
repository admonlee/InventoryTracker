/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryListTest {

    @Test
    void addItem_one_item() {

        //Create new InventoryList object
        InventoryList testList = new InventoryList();
        //Add one item
        testList.addItem("1234567890", "Test", 12.50);
        //Assert that testList has one item
        assertEquals(1, testList.getItemList().size());
    }

    @Test
    void addItem_multiple_items() {

        //Create new InventoryList object
        InventoryList testList = new InventoryList();
        //Add two items
        testList.addItem("1234567890", "Test", 12.50);
        testList.addItem("1234567891", "Test", 12.50);
        //Assert that testList has two items
        assertEquals(2, testList.getItemList().size());
    }

    @Test
    void addItem_one_hundred_items() {

        //Create new InventoryList object
        InventoryList testList = new InventoryList();
        //Add 100 items using a for loop
        for (int i = 0; i < 100; i++){
            int serialNumber = (1000000000 + i);
            testList.addItem(String.valueOf(serialNumber), "Test", 12.50);
        }
        //Assert that testList has 100 items
        assertEquals(100, testList.getItemList().size());
    }

    @Test
    void getSearchResults_serial_number_complete_match_numbers(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();

        //Add two items to inventory list
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("ABCDEFGHIJ", "Test 1", "$12.50");

        //Get search results
        ObservableList<Item> actual = inventoryList.getSearchResults("1234567890");

        //Assert that search results has one match
        assertEquals(1, actual.size());
    }

    @Test
    void getSearchResults_serial_number_partial_match_numbers(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("ABCDEFGHIJ", "Test 2", "$12.50");

        //Get search results using partial serial number as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("123");

        //Assert that search results has one match
        assertEquals(1, actual.size());
    }

    @Test
    void getSearchResults_serial_number_complete_match_letters(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("ABCDEFGHIJ", "Test 2", "$12.50");

        //Get search results using complete serial number as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("ABCDEFGHIJ");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_serial_number_partial_match_letters(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("ABCDEFGHIJ", "Test 2", "$12.50");

        //Get search results using partial serial number as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("DEF");

        //Assert that search results has one match
        assertEquals(1, actual.size());
    }

    @Test
    void getSearchResults_serial_number_complete_match_letters_mixed_case(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("ABCDEFGHIJ", "Test 2", "$12.50");

        //Get search results using complete serial number with mixed case letters as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("ABcDEFgHIj");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_serial_number_complete_match_letters_and_numbers(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("123ABC4567", "Test 2", "$12.50");

        //Get search results using complete serial number with letters and numbers as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("123abc4567");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_item_name_complete_match(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("123ABC4567", "Test 2", "$12.50");

        //Get search results using complete item name as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("Test 1");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_item_name_lower_case(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("123ABC4567", "Test 2", "$12.50");

        //Get search results using complete lower case item name as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("test 1");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_item_name_partial_match(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test 1", "$12.50");
        inventoryList.addItem("123ABC4567", "Test 2", "$12.50");

        //Get search results using partial item name as search query
        ObservableList<Item> actual = inventoryList.getSearchResults("test");

        //Assert that search results has two matches
        assertEquals(2, actual.size());

    }

    @Test
    void editItem_price(){

        //Create new InventoryList object
        InventoryList inventoryList = new InventoryList();
        //Add item to inventory list
        inventoryList.addItem("1234567890", "Test", 12.50);
        //Edit price of item
        inventoryList.editItem(0, "12.00", "1234567890");

        //Get the new price of item
        String actual = inventoryList.getItemObservableList().get(0).getPrice();
        //Assert that price has been updated
        assertEquals("$12.00", actual);

    }

    @Test
    void editItem_item_name(){

        //Create new InventoryList object
        InventoryList inventoryList = new InventoryList();
        //Add item to inventory list
        inventoryList.addItem("1234567890", "Test", 12.50);
        //Edit item name
        inventoryList.editItem(2, "New Item", "1234567890");

        //Get the new item name
        String actual = inventoryList.getItemObservableList().get(0).getItemName();
        //Assert that item name has been updated
        assertEquals("New Item", actual);
    }

    @Test
    void editItem_serial_number(){


        //Create new InventoryList object
        InventoryList inventoryList = new InventoryList();
        //Add item to inventory list
        inventoryList.addItem("1234567890", "Test", 12.50);
        //Edit item serial number
        inventoryList.editItem(1, "abcdefghij", "1234567890");

        //Get the new serial number of item
        String actual = inventoryList.getItemObservableList().get(0).getSerialNumber();
        //Assert that serial number has been updated
        assertEquals("abcdefghij", actual);
    }

    @Test
    void deleteItem_one_item(){

        //Create new InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add one item to inventory list
        inventoryList.addItem("1234567890", "Test", "$12.50");
        //Get the created item from inventory list
        Item testItem = inventoryList.getItemObservableList().get(0);
        //Delete item
        inventoryList.deleteItem(testItem);

        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list is empty
        assertEquals(0, actual.size());

    }

    @Test
    void deleteItem_first_item_from_list_of_two(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add items
        inventoryList.addItem("1234567890", "Test", "$12.50");
        inventoryList.addItem("1234567899", "Test", "$12.50");
        //Get first item
        Item testItem = inventoryList.getItemObservableList().get(0);
        //Delete first item
        inventoryList.deleteItem(testItem);

        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list has one item
        assertEquals(1, actual.size());
    }

    @Test
    void deleteItem_second_item_from_list_of_two(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add items
        inventoryList.addItem("1234567890", "Test", "$12.50");
        inventoryList.addItem("1234567899", "Test", "$12.50");
        //Get first item
        Item testItem = inventoryList.getItemObservableList().get(1);
        //Delete first item
        inventoryList.deleteItem(testItem);

        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list has one item
        assertEquals(1, actual.size());
    }

    @Test
    void deleteItem_one_hundred_items_from_top(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add one hundred items with a for loop
        for (int i = 0; i < 100; i++){
            inventoryList.addItem(String.valueOf(1000000000 + i), "Test", "$12.50");
        }
        //Delete all items with a for loop
        for (int i = 0; i < 100; i++){
            Item testItem = inventoryList.getItemObservableList().get(0);
            inventoryList.deleteItem(testItem);
        }

        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list is empty
        assertEquals(0, actual.size());
    }

    @Test
    void deleteItem_one_hundred_items_from_bottom(){

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add one hundred items with a for loop
        for (int i = 0; i < 100; i++){
            inventoryList.addItem(String.valueOf(1000000000 + i), "Test", "$12.50");
        }
        //Delete all items with a for loop
        for (int i = 99; i >= 0; i--){
            Item testItem = inventoryList.getItemObservableList().get(i);
            inventoryList.deleteItem(testItem);
        }

        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list is empty
        assertEquals(0, actual.size());
    }

    @Test
    void getCompleteItemList_one_item() {

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add one item
        inventoryList.addItem("1234567890", "Test", "$12.50");
        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list has one item
        assertEquals(1, actual.size());
    }

    @Test
    void getCompleteItemList_multiple_items() {

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add two items
        inventoryList.addItem("1234567890", "Test", "$12.50");
        inventoryList.addItem("ABCDEFGHIJ", "Test", "$12.50");
        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list has two items
        assertEquals(2, actual.size());
    }

    @Test
    void getCompleteItemList_100_items() {

        //Create new instance of InventoryList
        InventoryList inventoryList = new InventoryList();
        //Add 100 items with a for loop
        for(int i = 0; i < 100; i++){
            inventoryList.addItem(String.valueOf(i + 1000000000), "Test", "12.50");
        }
        //Get observable list
        ObservableList<Item> actual = inventoryList.getItemObservableList();

        //Assert that observable list has two items
        assertEquals(100, actual.size());
    }

}