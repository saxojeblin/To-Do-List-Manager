/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Nicholas Rubio
 */

package ucf.assignments;

public class Item {

    private String description;
    private String dueDate;
    private boolean complete;


    public Item (String description, String dueDate, boolean complete)  {
        this.description = description;
        this.dueDate = dueDate;
        this.complete = complete;
    }

    public void editDescription(String newDescription) {
        //Set this.description equal to newDescription
        this.description = newDescription;
    }

    public void editDueDate(String newDueDate) {
        //Set this.dueDate equal to newDueDate
        this.dueDate = newDueDate;
    }

    public void markComplete() {
        //Set this.complete equal to 'true'
        this.complete = true;
    }

    public String getDescription() {
        //return item description
        return this.description;
    }

    public String getDueDate() {
        //return item due date
        return this.dueDate;
    }

    public boolean getComplete() {
        //return item complete boolean
        return this.complete;
    }
}