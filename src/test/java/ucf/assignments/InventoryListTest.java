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

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add two items
        test.addItem("1234567890", "Test 1", "12.50");
        test.addItem("ABCDEFGHIJ", "Test 2", "12.50");

        //Get search results using complete serial number as search query
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("1234567890");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("123");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("ABCDEFGHIJ");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("DEF");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("ABcDEFgHIj");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("123abc4567");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("Test 1");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("test 1");

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
        ObservableList<Item> actual = test.getInventoryList().getSearchResults("test");

        //Assert that search results has two matches
        assertEquals(2, actual.size());

    }
}