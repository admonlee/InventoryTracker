/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

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

        String expected = "$12.50\t1234567890\tTest\n";

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

        String expected = "$12.50\t1234567890\tTest\n$20.00\tqwertyuiop\tTest\n";

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
                <style>
                table, th, td {border: 1px solid black};
                </style>
                <table>
                <tr>
                <td>$12.50</td>
                <td>1234567890</td>
                <td>Test</td>
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
                <style>
                table, th, td {border: 1px solid black};
                </style>
                <table>
                <tr>
                <td>$12.50</td>
                <td>1234567890</td>
                <td>Test</td>
                </tr>
                <tr>
                <td>$20.00</td>
                <td>qwertyuiop</td>
                <td>Test</td>
                </tr>
                </table>""";

        //Assert that HTML string matches intended format
        assertEquals(expected, actual);
    }

    @Test
    void parseJSON_one_object(){

        //Create JSON input string representing one object
        String input = """
                {
                  "itemList": {
                    "1234567890": {
                      "serialNumber": "1234567890",
                      "itemName": "Test",
                      "price": "12.50"
                    }
                  }
                }""";

        //Create new file manager
        FileManager fileManager = new FileManager(new File("Test.txt"));

        //Get inventory list from JSON parser
        InventoryList inventoryList = fileManager.parseJSON(input);

        //Assert that inventory list has one item
        assertEquals(1, inventoryList.getItemList().size());
    }

    @Test
    void parseJSON_multiple_objects(){

        //Create JSON input string representing two objects
        String input = """
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

        //Create new file manager
        FileManager fileManager = new FileManager(new File("Test.txt"));

        //Get inventory list from JSON parser
        InventoryList inventoryList = fileManager.parseJSON(input);

        //Assert that inventory list has two item
        assertEquals(2, inventoryList.getItemList().size());
    }

    @Test
    void parseTSV_one_object(){

        //Create TSV input string representing one object
        String input = "$12.50\t1234567890\tTest\n";

        //Create new file manager
        FileManager fileManager = new FileManager(new File("Test.txt"));

        //Get inventory list from TSV parser
        InventoryList inventoryList = fileManager.parseTSV(input);

        //Assert that inventory list has one item
        assertEquals(1, inventoryList.getItemList().size());

    }

    @Test
    void parseTSV_multiple_objects(){

        //Create TSV input string representing two objects
        String input = "$12.50\t1234567890\tTest\n$20.00\tqwertyuiop\tTest\n";

        //Create new file manager
        FileManager fileManager = new FileManager(new File("Test.txt"));

        //Get inventory list from TSV parser
        InventoryList inventoryList = fileManager.parseTSV(input);

        //Assert that inventory list has one item
        assertEquals(2, inventoryList.getItemList().size());

    }

    @Test
    void parseHTML_one_object(){

        //Create HTML string input representing one object
        String input = """
                <style>
                table, th, td {border: 1px solid black};
                </style>
                <table>
                <tr>
                <td>$12.50</td>
                <td>1234567890</td>
                <td>Test</td>
                </tr>
                </table>""";

        //Create new file manager
        FileManager fileManager = new FileManager(new File("Test.txt"));

        //Get inventory list from HTML parser
        InventoryList inventoryList = fileManager.parseHTML(input);

        //Assert that inventory list has one item
        assertEquals(1, inventoryList.getItemList().size());

    }

    @Test
    void parseHTML_two_objects(){

        //Create HTML string input representing two objects
        String input = """
                <style>
                table, th, td {border: 1px solid black};
                </style>
                <table>
                <tr>
                <td>$12.50</td>
                <td>1234567890</td>
                <td>Test</td>
                </tr>
                <tr>
                <td>$20.00</td>
                <td>qwertyuiop</td>
                <td>Test</td>
                </tr>
                </table>""";

        //Create new file manager
        FileManager fileManager = new FileManager(new File("Test.txt"));

        //Get inventory list from HTML parser
        InventoryList inventoryList = fileManager.parseHTML(input);

        //Assert that inventory list has one item
        assertEquals(2, inventoryList.getItemList().size());
    }
}