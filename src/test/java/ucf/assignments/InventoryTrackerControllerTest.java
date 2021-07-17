package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTrackerControllerTest {

    @Test
    void getDisplayedItemsList_one_item() {

        //Create new instance of InventoryTrackerController
        InventoryTrackerController test = new InventoryTrackerController();
        //Add one item
        test.addItems("1234567890", "Test", "12.50");
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
        test.addItems("1234567890", "Test1", "12.50");
        test.addItems("ABCDEFGHIJ", "Test2", "30.00");
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
            test.addItems(String.valueOf(i + 1000000000), "Test", "12.50");
        }
        //Get observable list
        ObservableList<Item> actual = test.getDisplayedItemsList();

        //Assert that observable list has two items
        assertEquals(100, actual.size());
    }
}