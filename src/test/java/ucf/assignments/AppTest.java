/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Nicholas Rubio
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AppTest {

    /* ---Tests of methods to add/delete items in a to-do list--- */
    @Test
    public void testAddItemToDoList() {
        //Create two ToDoLists, 1 and 2
        ArrayList<Item> items = new ArrayList<>();
        ToDoList actualList = new ToDoList(items);
        ToDoList testList = new ToDoList(items);
        //add an item to '1' with some hard coded attributes-> this is the expected
        //add an item to '2' using addItemToDoList() passing in the same attributes
        Item testItem = new Item("test","1111-11-11", false);
        actualList.getItemList().add(testItem);
        testList.addItem("test", "1111-11-11", false);
        //assert equal 1's and 2's item object lists values
        assertEquals(actualList.getItemList().get(0).getDescription(), testList.getItemList().get(0).getDescription());
        assertEquals(actualList.getItemList().get(0).getDueDate(), testList.getItemList().get(0).getDueDate());
        assertEquals(actualList.getItemList().get(0).getComplete(), testList.getItemList().get(0).getComplete());
    }

    @Test
    public void testRemoveItemToDoList() {
        //Create ToDoList 'actual' and add one item called 'a'
        //Create ToDoList 'test' and add two items called 'a' and 'b'
        ArrayList<Item> items = new ArrayList<>();
        ToDoList actualList = new ToDoList(items);
        ToDoList testList = new ToDoList(items);
        actualList.addItem("test", "1111-11-11", false);
        testList.addItem("test", "1111-11-11", false);
        testList.addItem("remove", "2222-11-11", true);
        //Use removeItem() one '2' and remove 'b'
        testList.removeItem(1);
        //Assert equal 1 and 2 to see if they have the same items and are same size
        assertEquals(actualList.getItemList().size(), testList.getItemList().size());
        assertEquals(actualList.getItemList().get(0).getDescription(), testList.getItemList().get(0).getDescription());
        assertEquals(actualList.getItemList().get(0).getDueDate(), testList.getItemList().get(0).getDueDate());
        assertEquals(actualList.getItemList().get(0).getComplete(), testList.getItemList().get(0).getComplete());
    }

    @Test
    public void testClearAllItems() {
        //Create a to-do list that will be cleared
        ArrayList<Item> items = new ArrayList<>();
        ToDoList testList = new ToDoList(items);
        //Add some items to the to-do list
        testList.addItem("test", "2222-11-11", false);
        testList.addItem("test2", "2222-11-11", true);
        //Use clearAllItems() to clear out the to do list
        testList.clearAllItems();
        //assert equal the number of items in the to do list to zero
        assertEquals(0, testList.getItemList().size());
    }

    /* ---Tests of methods to edit items in a to-do list--- */
    @Test
    public void testEditToDoListItemDescription() {
        //Create ToDoList '1' and add one item called 'a'
        //Create ToDoList '2' and add one item called 'b', which is the same as 'a' but with a different description
        ArrayList<Item> items = new ArrayList<>();
        ToDoList actualList = new ToDoList(items);
        ToDoList testList = new ToDoList(items);
        actualList.addItem("test", "1111-11-11", false);
        testList.addItem("remove", "1111-11-11", false);
        //Use editToDoListItemDescription() on '2' and pass in 'a''s item description
        testList.editItemDescription(0 , "test");
        //assert equal 'a''s description and 'b''s description in '1' and '2'
        assertEquals(actualList.getItemList().get(0).getDescription(), testList.getItemList().get(0).getDescription());
    }

    @Test
    public void testEditToDoListItemDueDate() {
        //Create ToDoList '1' and add one item called 'a'
        //Create ToDoList '2' and add one item called 'b', which is the same as 'a' but with a different due date
        ArrayList<Item> items = new ArrayList<>();
        ToDoList actualList = new ToDoList(items);
        ToDoList testList = new ToDoList(items);
        actualList.addItem("test", "1111-11-11", false);
        testList.addItem("test", "2222-11-11", false);
        //Use editToDoListItemDueDate() on '2' and pass in 'a''s item due date
        testList.editDueDate(0 , "1111-11-11");
        //assert equal 'a''s due date and 'b''s due date in '1' and '2'
        assertEquals(actualList.getItemList().get(0).getDueDate(), testList.getItemList().get(0).getDueDate());
    }

    @Test
    public void testMarkToDoListItemComplete() {
        //Create an expected item, whose 'complete' is set to true
        //Create an actual ToDoList, and add the expected item, except with its 'complete' set to false
        ArrayList<Item> items = new ArrayList<>();
        ToDoList testList = new ToDoList(items);
        testList.addItem("test", "2222-11-11", false);
        //use markToDoListItemComplete() on the actual and index 0
        testList.markItemComplete(0);
        //assert equal 'complete' of the expected item and the item in index 0 in the ToDoList
        assertTrue(testList.getItemList().get(0).getComplete());
    }

    /*Tests of methods that should display item information to the user */
    @Test
    public void testDisplayAllToDoListItems() {
        //Create two strings to represent data displayed to user
        String expectedDisplay, actualDisplay;
        //Create expected string
        expectedDisplay = """
                ITEM 1:
                Description: test1
                Due Date: 1111-11-11
                Status: Incomplete.

                ITEM 2:
                Description: test2
                Due Date: 2222-11-11
                Status: Incomplete.

                """;
        //Create a to-do list and add items too it
        ArrayList<Item> items = new ArrayList<>();
        ToDoList testList = new ToDoList(items);
        testList.addItem("test1", "1111-11-11", false);
        testList.addItem("test2", "2222-11-11", false);
        //Get actual display string using displayAllItems()
        actualDisplay =  testList.displayAllItems();
        //Assert equal the expected and actual strings
        assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void testDisplayAllToDoListIncompleteItems() {
        //Create two strings to represent data displayed to user
        String expectedDisplay, actualDisplay;
        //Create expected string
        expectedDisplay = """
                ITEM 2:
                Description: test2
                Due Date: 2222-11-11
                Status: Incomplete.

                """;
        //Create a to-do list and add items too it
        ArrayList<Item> items = new ArrayList<>();
        ToDoList testList = new ToDoList(items);
        testList.addItem("test1", "1111-11-11", true);
        testList.addItem("test2", "2222-11-11", false);
        //Get actual display string using displayIncompleteItems()
        actualDisplay =  testList.displayIncompleteItems();
        //Assert equal the expected and actual strings
        assertEquals(expectedDisplay, actualDisplay);
    }

    @Test
    public void testDisplayAllToDoListCompleteItems() {
        //Create two strings to represent data displayed to user
        String expectedDisplay, actualDisplay;
        //Create expected string
        expectedDisplay = """
                ITEM 2:
                Description: test2
                Due Date: 2222-11-11
                Status: Complete.

                """;
        //Create a to-do list and add items too it
        ArrayList<Item> items = new ArrayList<>();
        ToDoList testList = new ToDoList(items);
        testList.addItem("test1", "1111-11-11", false);
        testList.addItem("test2", "2222-11-11", true);
        //Get actual display string using displayIncompleteItems()
        actualDisplay =  testList.displayCompleteItems();
        //Assert equal the expected and actual strings
        assertEquals(expectedDisplay, actualDisplay);
    }

    /*---Tests of methods that load/save a to-do list---*/
    @Test
    public void testSaveToDoList() throws IOException {
        //Create a ToDoList '1'
        ArrayList<Item> items = new ArrayList<>();
        ToDoList testList = new ToDoList(items);
        testList.addItem("test1", "1111-11-11", true);
        testList.addItem("test2", "2222-11-11", false);
        //Use saveToDoList() with '1' with a different, actual file
        testList.saveItems("test");
        //Verify the file was successfully created
        File testFile = new File("src/main/java/ucf/assignments/test.txt");
        assertTrue(testFile.exists());
    }

    @Test
    public void testLoadToDoList() {
        //This method is not testable.
        //It uses fileChooser in a javafx window to select a file, then
        //displays that file in a scene in App.
        //Therefore, there is no calculation/computation, so it isn't testable
    }

}