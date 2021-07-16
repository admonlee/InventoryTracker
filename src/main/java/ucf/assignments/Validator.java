/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Admon Lee
 */

package ucf.assignments;

import java.util.TreeMap;

public class Validator {

    public boolean validateItemName(String inputItemName){

        //Return true if item name is between 2 and 256 characters inclusive
        return (inputItemName.length() >= 2 && inputItemName.length() <= 256);
    }

    public boolean validateSerialNumber(String inputSerialNumber, TreeMap<String, Item> itemList){

        boolean serialNumberValidity = false;

        //Check if serial number is alphanumeric
        if (inputSerialNumber.matches("^[a-zA-Z0-9]{10,}$")){
            //Check if serial number already exists in the item list TreeMap
            serialNumberValidity = !itemList.containsKey(inputSerialNumber);
        }

        return serialNumberValidity;
    }

    public boolean validatePrice(String inputPrice){

        try{
            //Check if price is parsable as a double
            double price = Double.parseDouble(inputPrice);
            //Check if price is larger than $0
            return price > 0;
        }
        catch(NumberFormatException e){
            //Return false if the price is not a number
            return false;
        }
    }
}
