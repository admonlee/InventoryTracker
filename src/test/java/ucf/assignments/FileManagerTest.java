package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void getJSONString_one_object() {

        //Create new inventory list
        InventoryList inventoryList = new InventoryList();
        //Add one item to inventory list
        inventoryList.addItem("1234567890", "Test", "12.50");

        //Create new FileManager object
        FileManager fileManager = new FileManager(new File("Test.json"));
        //Get JSON String
        String actual = fileManager.getJSONString(inventoryList);

        String expected = """
                {
                  "itemList": {
                    "1234567890": {
                      "serialNumber": "1234567890",
                      "itemName": "Test",
                      "price": "12.50"
                    }
                  }
                }""";

        //Assert that JSON string matches intended format
        assertEquals(expected, actual);
    }

    @Test
    void getJSONString_multiple_objects() {

        //Create new inventory list
        InventoryList inventoryList = new InventoryList();
        //Add two items to inventory list
        inventoryList.addItem("1234567890", "Test 1", "12.50");
        inventoryList.addItem("qwertyuiop", "Test 2", "25.00");

        //Create new FileManager object
        FileManager fileManager = new FileManager(new File("Test.json"));
        //Get JSON String
        String actual = fileManager.getJSONString(inventoryList);

        String expected = """
                {
                  "itemList": {
                    "1234567890": {
                      "serialNumber": "1234567890",
                      "itemName": "Test 1",
                      "price": "12.50"
                    },
                    "qwertyuiop": {
                      "serialNumber": "qwertyuiop",
                      "itemName": "Test 2",
                      "price": "25.00"
                    }
                  }
                }""";

        //Assert that JSON string matches intended format
        assertEquals(expected, actual);
    }

    @Test
    void getTSVString_one_object() {

        //Create new inventory list
        InventoryList inventoryList = new InventoryList();
        //Add one item to inventory list
        inventoryList.addItem("1234567890", "Test", 12.50);

        //Create new FileManager object
        FileManager fileManager = new FileManager(new File("Test.txt"));
        //Get TSV string
        String actual = fileManager.getTSVString(inventoryList);

        String expected = "1234567890\tTest\t$12.50\n";

        //Assert that TSV string matches intended format
        assertEquals(expected, actual);
    }

    @Test
    void getTSVString_multiple_objects() {

        //Create new inventory list
        InventoryList inventoryList = new InventoryList();
        //Add two items to inventory list
        inventoryList.addItem("1234567890", "Test", 12.50);
        inventoryList.addItem("qwertyuiop", "Test", 20.00);

        //Create new FileManager object
        FileManager fileManager = new FileManager(new File("Test.txt"));
        //Get TSV string
        String actual = fileManager.getTSVString(inventoryList);

        String expected = "1234567890\tTest\t$12.50\nqwertyuiop\tTest\t$20.00\n";

        //Assert that TSV string matches intended format
        assertEquals(expected, actual);
    }

    @Test
    void getHTMLString_one_object() {

        //Create new inventory list
        InventoryList inventoryList = new InventoryList();
        //Add one item to inventory list
        inventoryList.addItem("1234567890", "Test", 12.50);

        //Create new FileManager object
        FileManager fileManager = new FileManager(new File("Test.html"));
        //Get HTML string
        String actual = fileManager.getHTMLString(inventoryList);

        String expected = """
                <table>
                <tr>
                <td>1234567890</td>
                <td>Test</td>
                <td>$12.50</td>
                </tr>
                </table>""";

        //Assert that HTML string matches intended format
        assertEquals(expected, actual);
    }

    @Test
    void getHTMLString_multiple_objects() {

        //Create new inventory list
        InventoryList inventoryList = new InventoryList();
        //Add two items to inventory list
        inventoryList.addItem("1234567890", "Test", 12.50);
        inventoryList.addItem("qwertyuiop", "Test", 20.00);

        //Create new FileManager object
        FileManager fileManager = new FileManager(new File("Test.html"));
        //Get HTML string
        String actual = fileManager.getHTMLString(inventoryList);

        String expected = """
                <table>
                <tr>
                <td>1234567890</td>
                <td>Test</td>
                <td>$12.50</td>
                </tr>
                <tr>
                <td>qwertyuiop</td>
                <td>Test</td>
                <td>$20.00</td>
                </tr>
                </table>""";

        //Assert that HTML string matches intended format
        assertEquals(expected, actual);
    }
}