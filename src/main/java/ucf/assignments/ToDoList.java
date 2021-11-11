/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Nicholas Rubio
 */

package ucf.assignments;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToDoList {

    private ArrayList<Item> items;

    public ToDoList(ArrayList<Item> items) {
        //Set items as this ToDoList's item list
        this.items = items;
    }

    public ArrayList<Item> getItemList() {
        //returns the title of the ToDoList
        return this.items;
    }

    public void addItem(String description, String dueDate, boolean complete) {
        //Create a new item object to build upon with attributes:
        //Set new item's description to its description
        //Set new item's dueDate to its due date
        //Set new item's complete to its completion status
        Item item = new Item(description, dueDate, complete);
        //Add the item to this ToDoList's item list
        this.items.add(item);
    }

    public void removeItem(int index) {
        //remove item in list at given index
        this.getItemList().remove(index);
    }

    public void clearAllItems() {
        //clear item list
        this.getItemList().clear();
    }

    public void editItemDescription(int index, String newDescription) {
        //Call the ToDoList's item at given index
        this.getItemList().get(index).editDescription(newDescription);
        //Use editDescription() in item class
    }

    public void editDueDate(int index, String newDueDate) {
        //Get the ToDoList's item at given index
        this.getItemList().get(index).editDueDate(newDueDate);
        //Call editDueDate() in item class
    }

    public void markItemComplete(int index) {
        //Get the ToDoList's item at given index
        this.getItemList().get(index).markComplete();
        //Call markComplete in item class
    }

    public String displayAllItems() {
        String completion;
        StringBuilder stb = new StringBuilder();
        //Get size of the list
        int listSize = this.getItemList().size();
        //Iterate through the list with its size
        for (int i = 0; i < listSize; i++) {
            //Append each item's contents to a string
            int itemNum = i + 1;
            stb.append("ITEM " + itemNum + ":\n");
            stb.append("Description: " + this.getItemList().get(i).getDescription() + "\n");
            stb.append("Due Date: " + this.getItemList().get(i).getDueDate() + "\n");
            //Append completion status appropriately
            if (this.getItemList().get(i).getComplete()) {
                completion = "Complete.";
            }
            else  {
                completion  = "Incomplete.";
            }
            stb.append("Status: " + completion + "\n\n");
        }
        //return the built string
        return stb.toString();

    }

    public String displayIncompleteItems() {
        StringBuilder stb = new StringBuilder();
        //Get the size of the list
        int listSize = this.getItemList().size();
        //Iterate through the list with its size
        for (int i = 0; i < listSize; i++) {
            int itemNum = i + 1;
            //Check if given item at index of loop is incomplete
            if (!this.getItemList().get(i).getComplete()) {
                //If incomplete, append its contents to a string
                stb.append("ITEM " + itemNum + ":\n");
                stb.append("Description: " + this.getItemList().get(i).getDescription() + "\n");
                stb.append("Due Date: " + this.getItemList().get(i).getDueDate() + "\n");
                stb.append("Status: Incomplete.\n\n");
            }

        }
        //return the built string
        return stb.toString();
    }

    public String displayCompleteItems() {
        StringBuilder stb = new StringBuilder();
        //Get the size of item list
        int listSize = this.getItemList().size();
        //Iterate through item list size
        for (int i = 0; i < listSize; i++) {
            int itemNum = i + 1;
            //If item at index of loop is complete, append its contents to string
            if (this.getItemList().get(i).getComplete()) {
                stb.append("ITEM " + itemNum + ":\n");
                stb.append("Description: " + this.getItemList().get(i).getDescription() + "\n");
                stb.append("Due Date: " + this.getItemList().get(i).getDueDate() + "\n");
                stb.append("Status: Complete.\n\n");
            }

        }
        //return built string
        return stb.toString();
    }

    public void saveItems(String fileName) throws IOException {
        //Create a new file to write in with given file name
        FileWriter writer = new FileWriter("src/main/java/ucf/assignments/" + fileName + ".txt");
        //Get a string that contains the contents of the item list
        String items = this.displayAllItems();
        //Write the string into the file
        writer.write(items);
        //Close the file
        writer.close();
    }
}
