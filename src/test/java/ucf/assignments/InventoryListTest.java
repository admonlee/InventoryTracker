package ucf.assignments;

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
}