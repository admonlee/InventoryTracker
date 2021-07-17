package ucf.assignments;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryTrackerControllerTest {

    @Test
    void getDisplayedItemsList_one_item() {

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add one item
        test.addItem("1234567890", "Test", "12.50");
        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list has one item
        assertEquals(1, actual.size());
    }

    @Test
    void getDisplayedItemsList_multiple_items() {

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test1", "12.50");
        test.addItem("ABCDEFGHIJ", "Test2", "30.00");
        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list has two items
        assertEquals(2, actual.size());
    }

    @Test
    void getDisplayedItemsList_100_items() {

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add 100 items with a for loop
        for(int i = 0; i < 100; i++){
            test.addItem(String.valueOf(i + 1000000000), "Test", "12.50");
        }
        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list has two items
        assertEquals(100, actual.size());
    }

    @Test
    void deleteItem_one_item(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add item
        test.addItem("1234567890", "Test", "12.50");
        //Get created item
        Item testItem = test.getDisplayedItemsList().get(0);
        //Delete item
        test.deleteItem(testItem);

        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list is empty
        assertEquals(0, actual.size());
    }

    @Test
    void deleteItem_first_item_from_list_of_two(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add items
        test.addItem("1234567890", "Test", "12.50");
        test.addItem("1234567899", "Test", "12.50");
        //Get first item
        Item testItem = test.getDisplayedItemsList().get(0);
        //Delete first item
        test.deleteItem(testItem);

        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list has one item
        assertEquals(1, actual.size());
    }

    @Test
    void deleteItem_second_item_from_list_of_two(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add items
        test.addItem("1234567890", "Test", "12.50");
        test.addItem("1234567899", "Test", "12.50");
        //Get second item
        Item testItem = test.getDisplayedItemsList().get(1);
        //Delete item
        test.deleteItem(testItem);

        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list has one item
        assertEquals(1, actual.size());
    }

    @Test
    void deleteItem_one_hundred_items_from_top(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add one hundred items with a for loop
        for (int i = 0; i < 100; i++){
            test.addItem(String.valueOf(1000000000 + i), "Test", "12.50");
        }
        //Delete all items with a for loop
        for (int i = 0; i < 100; i++){
            Item testItem = test.getDisplayedItemsList().get(0);
            test.deleteItem(testItem);
        }

        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list is empty
        assertEquals(0, actual.size());
    }

    @Test
    void deleteItem_one_hundred_items_from_bottom(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add one hundred items with a for loop
        for (int i = 0; i < 100; i++){
            test.addItem(String.valueOf(1000000000 + i), "Test", "12.50");
        }
        //Delete all items with a for loop
        for (int i = 99; i >= 0; i--){
            Item testItem = test.getDisplayedItemsList().get(i);
            test.deleteItem(testItem);
        }

        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list is empty
        assertEquals(0, actual.size());
    }

    @Test
    void editItem_price(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add item
        test.addItem("1234567890", "Test", "12.50");
        //Edit serial number
        test.editItem(0, 0, "12.50", "1234567890");

        //Get edited serial number
        String actual = test.getDisplayedItemsList().get(0).getPrice();
        assertEquals("$12.50", actual);
    }

    @Test
    void editItem_item_name(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add item
        test.addItem("1234567890", "Test", "12.50");
        //Edit serial number
        test.editItem(2, 0, "Test edit", "1234567890");

        //Get edited serial number
        String actual = test.getDisplayedItemsList().get(0).getItemName();
        assertEquals("Test edit", actual);
    }

    @Test
    void editItem_serial_number(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add item
        test.addItem("1234567890", "Test", "12.50");
        test.addItem("1234567899", "Test", "12.50");
        //Edit serial number
        test.editItem(1, 0, "123456788", "1234567890");

        //Get edited serial number
        String actual = test.getDisplayedItemsList().get(0).getSerialNumber();
        assertEquals("123456788", actual);
    }

    @Test
    void getSearchResults_serial_number_complete_match_numbers(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("ABCDEFGHIJ", "Test 2", "12.50");

        //Get search results using complete serial number as search query
        ObservableList<Item> actual = test.getSearchResults("1234567890");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_serial_number_partial_match_numbers(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("ABCDEFGHIJ", "Test 2", "12.50");

        //Get search results using partial serial number as search query
        ObservableList<Item> actual = test.getSearchResults("123");

        //Assert that search results has one match
        assertEquals(1, actual.size());
    }

    @Test
    void getSearchResults_serial_number_complete_match_letters(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("ABCDEFGHIJ", "Test 2", "12.50");

        //Get search results using complete serial number as search query
        ObservableList<Item> actual = test.getSearchResults("ABCDEFGHIJ");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_serial_number_partial_match_letters(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("ABCDEFGHIJ", "Test 2", "12.50");

        //Get search results using partial serial number as search query
        ObservableList<Item> actual = test.getSearchResults("DEF");

        //Assert that search results has one match
        assertEquals(1, actual.size());
    }

    @Test
    void getSearchResults_serial_number_complete_match_letters_mixed_case(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("ABCDEFGHIJ", "Test 2", "12.50");

        //Get search results using complete serial number with mixed case letters as search query
        ObservableList<Item> actual = test.getSearchResults("ABcDEFgHIj");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_serial_number_complete_match_letters_and_numbers(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("123ABC4567", "Test 2", "12.50");

        //Get search results using complete serial number with letters and numbers as search query
        ObservableList<Item> actual = test.getSearchResults("123abc4567");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_item_name_complete_match(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("123ABC4567", "Test 2", "12.50");

        //Get search results using complete item name as search query
        ObservableList<Item> actual = test.getSearchResults("Test 1");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_item_name_lower_case(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("123ABC4567", "Test 2", "12.50");

        //Get search results using complete lower case item name as search query
        ObservableList<Item> actual = test.getSearchResults("test 1");

        //Assert that search results has one match
        assertEquals(1, actual.size());

    }

    @Test
    void getSearchResults_item_name_partial_match(){

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("123ABC4567", "Test 2", "12.50");

        //Get search results using partial item name as search query
        ObservableList<Item> actual = test.getSearchResults("test");

        //Assert that search results has two matches
        assertEquals(2, actual.size());

    }
}