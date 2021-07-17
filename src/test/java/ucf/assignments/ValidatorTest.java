package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateItemName_test_for_one_character_string() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns false when item name is one character long
        String input = "a";
        assertFalse(validator.validateItemName(input));
    }

    @Test
    void validateItemName_test_for_two_character_string() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns true when item name is two characters long
        String input = "ab";
        assertTrue(validator.validateItemName(input));
    }

    @Test
    void validateItemName_test_for_three_character_string() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns true when item name is three characters long
        String input = "abc";
        assertTrue(validator.validateItemName(input));
    }

    @Test
    void validateItemName_test_for_256_character_string() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns true when item name is 256 characters long
        String input = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv";
        assertTrue(validator.validateItemName(input));
    }

    @Test
    void validateItemName_test_for_260_character_string() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns false when item name exceeds 256 characters
        String input = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" +
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        assertFalse(validator.validateItemName(input));
    }

    @Test
    void validateSerialNumber_blank_item_list_insufficient_chars() {

        //Create new validator and empty TreeMap
        Validator validator = new Validator();
        TreeMap<String, Item> testMap = new TreeMap<>();
        String input = "ABCDEFG";
        //Assert that validator returns false when serial number is fewer than 10 characters long
        assertFalse(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validateSerialNumber_blank_item_list_sufficient_chars_wrong_format() {

        //Create new validator and empty TreeMap
        Validator validator = new Validator();
        TreeMap<String, Item> testMap = new TreeMap<>();
        String input = "ABCDEFGHI.";
        //Assert that validator returns false when serial number is 10 characters long with an invalid character
        assertFalse(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validateSerialNumber_blank_item_list_exceeding_chars_wrong_format() {

        //Create new validator and empty TreeMap
        Validator validator = new Validator();
        TreeMap<String, Item> testMap = new TreeMap<>();
        String input = "ABCDEFGHIJK";
        //Assert that validator returns false when serial number is 10 characters long with an invalid character
        assertFalse(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validateSerialNumber_blank_item_list_sufficient_chars_all_letters() {

        //Create new validator and empty TreeMap
        Validator validator = new Validator();
        TreeMap<String, Item> testMap = new TreeMap<>();
        String input = "ABCDEFGHIJ";
        //Assert that validator returns true when serial number is 10 characters long with all letters
        assertTrue(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validateSerialNumber_blank_item_list_sufficient_chars_all_numbers() {

        //Create new validator and empty TreeMap
        Validator validator = new Validator();
        TreeMap<String, Item> testMap = new TreeMap<>();
        String input = "0123456789";
        //Assert that validator returns true when serial number is 10 characters long with all numbers
        assertTrue(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validateSerialNumber_blank_item_list_sufficient_chars_mixed_chars() {

        //Create new validator and empty TreeMap
        Validator validator = new Validator();
        TreeMap<String, Item> testMap = new TreeMap<>();
        String input = "0ABC45DEF9";
        //Assert that validator returns true when serial number is 10 characters long with letters and numbers
        assertTrue(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validateSerialNumber_non_duplicate_sufficient_chars_mixed_chars() {

        //Create new validator
        Validator validator = new Validator();
        //Create TreeMap with one item
        TreeMap<String, Item> testMap = new TreeMap<>();
        Item testItem = new Item("0123456789", "Test", 19.00);
        //Create new input that is different from the serial number in the TreeMap
        String input = "0ABC45DEF9";
        //Assert that validator returns true
        assertTrue(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validateSerialNumber_duplicate_sufficient_chars_mixed_chars() {

        //Create new validator
        Validator validator = new Validator();
        //Create TreeMap with one item
        TreeMap<String, Item> testMap = new TreeMap<>();
        Item testItem = new Item("0ABC45DEF9", "Test", 19.00);
        testMap.put("0ABC45DEF9", testItem);
        //Create new input the same as the serial number in the TreeMap
        String input = "0ABC45DEF9";
        //Assert that validator returns false
        assertFalse(validator.validateSerialNumber(input, testMap));

    }

    @Test
    void validatePrice_whole_number() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns true when price is a positive integer
        String input = "9";
        assertTrue(validator.validatePrice(input));
    }

    @Test
    void validatePrice_decimal_number() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns true when price is a positive double
        String input = "9.50";
        assertTrue(validator.validatePrice(input));
    }

    @Test
    void validatePrice_text_string() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns false when price is a text string
        String input = "abc";
        assertFalse(validator.validatePrice(input));
    }

    @Test
    void validatePrice_negative_number() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns false when price is a negative number
        String input = "-9.50";
        assertFalse(validator.validatePrice(input));
    }

    @Test
    void validatePrice_zero() {

        //Create new validator
        Validator validator = new Validator();
        //Assert that validator returns false when price is 0
        String input = "0";
        assertFalse(validator.validatePrice(input));
    }
}