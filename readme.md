# Inventory Tracker User Guide
This is a simple inventory tracker app that manages an inventory list.

## Adding items to the list
To add an item to the list, enter the information of the item in the text fields at the bottom of the window. The app accepts price values as positive integers or decimal numbers and displays them as dollars rounded to the nearest cent. Serial numbers must be 10 alphanumeric characters long and unique. Item names must be between 2 and 256 characters long. Once all three text fields are filled, click the Add Item button to add the item to the list.

## Deleting items from the list
To delete an item from the list, right click on the item in the table and click on Delete Item in the context menu.

## Editing items on the list
To edit an item, double click on the value that you want to edit. The selected table cell will turn into a text field. Type in the new value and press enter on your keyboard to commit changes. The requirements for edit values are the same as the requirements of values when adding a new item.

## Sorting items on the list
To sort items on the list, click on the table header of the column that to sort the items by. The items will be sorted in ascending order. Click on the table header again to sort in descending order.

## Search for an item
To search for an item by serial number or name, type the serial number or name into the search bar at the top right corner of the window. The table will update as you type to display the search results. To exit search mode, clear the search bar and all items will be displayed on the table.

## Save an inventory list
To save an inventory list, click on File on the menu bar. Select Save from the dropdown menu and a save dialog box will appear. Navigate to the desired directory, enter a file name and select the file type. The app supports saving in JSON, TSV, and HTML format. Click Save and the inventory list will be saved.

## Open a saved inventory list
To open a previously saved inventory list, click on File on the menu bar. Select Open from the dropdown menu and an open dialog box will appear. Navigate to the save file location and select the file type (JSON, HTML, or txt). Select the file and click open. The contents of the file will be loaded into the app.