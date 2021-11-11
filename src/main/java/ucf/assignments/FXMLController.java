/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Nicholas Rubio
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FXMLController implements Initializable {
    //Our ToDoList with its list of items
    public static ArrayList<Item> items = new ArrayList<>();
    public static ToDoList toDoList = new ToDoList(items);

    //Stage, scene, and pane for the main menu display
    private Stage stage;
    private Scene scene;
    @FXML
    private BorderPane mainPane;

    //For opening file explorer for 'load file'
    FileChooser fileChooser = new FileChooser();

    /*----All FXML labels----*/
    @FXML
    private Label descError;
    @FXML
    private Label dueError;
    @FXML
    private Label invalidItemNumberLabel;
    @FXML
    private Label newDescriptionError;
    @FXML
    private Label invalidItemNumberHelpLabel;
    @FXML
    private Label invalidItemNumberLabel2;
    @FXML
    private Label invalidItemNumberHelpLabel2;
    @FXML
    private Label newDueDateError;
    @FXML
    private Label markCompleteItemNumberError;
    @FXML
    private Label markCompleteItemNumberHelp;
    @FXML
    private Label deleteItemError;
    @FXML
    private Label deleteItemHelp;
    @FXML
    private Label fileNameErrorLabel;
    @FXML
    private Label loadFileErrorLabel;

    /*----All FXML Text Fields----*/
    @FXML
    private TextField descTextField;
    @FXML
    private TextField dueTextField;
    @FXML
    private TextField itemNumberTextField;
    @FXML
    private TextField newDescriptionTextField;
    @FXML
    private TextField itemNumberTextField2;
    @FXML
    private TextField newDueDateTextField;
    @FXML
    private TextField markCompleteItemNumber;
    @FXML
    private TextField deleteItemNumber;
    @FXML
    private TextField fileNameTextField;

    /*----All FXML Text Areas----*/
    @FXML
    private TextArea allItemsTextArea;
    @FXML
    private TextArea completeItemsTextArea;
    @FXML
    private TextArea incompleteItemsTextArea;
    @FXML
    private TextArea loadFileTextArea;

    //Function to return scene to main menu
    @FXML
    public void setMainScene(ActionEvent event) throws IOException {
        //Load the main menu file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainScene.fxml")));
        //Set the fxml file to be the new scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Function to initialize file explorer directory
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set up initial directory for the file explorer window
        fileChooser.setInitialDirectory(new File("src/main/java/ucf/assignments/"));
    }

    /*----All FXML Button Actions----*/
    @FXML
    private void addDeleteButtonAction() throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ItemCreateDeleteScene");
        mainPane.setCenter(view);
    }

    @FXML
    private void EditButtonAction() throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("ItemEditScene");
        mainPane.setCenter(view);
    }

    @FXML
    private void displayButtonAction() throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("DisplayScene");
        mainPane.setCenter(view);
    }

    @FXML
    private void saveLoadButtonAction() throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("SaveLoadScene");
        mainPane.setCenter(view);
    }

    @FXML
    private void helpButtonAction() throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("HelpScene");
        mainPane.setCenter(view);
    }

    @FXML
    private void createItemButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateItemScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void submitAddItem(ActionEvent event) throws IOException {
        //Booleans to check user input
        boolean checkDesc = false, checkDue = false;
        //Get input from user from text fields
        String desc = descTextField.getText();
        String due = dueTextField.getText();
        //Check the length of the description
        if (desc.length() > 256 || desc.length() == 0) {
            descError.setText("Please ensure description is between 1-256 characters.");
        }
        else {
            descError.setText("");
            checkDesc = true;
        }
        //Check the format of the due date
        if (!due.matches("^([0-9]{4}[-/]?((0[13-9]|1[012])[-/]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-/]?31|02[-/]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)[-/]?02[-/]?29)$")) {
            dueError.setText("Please ensure due date is in Gregorian Calendar format (YYYY-MM-DD) and is a valid date");
        }
        else {
            dueError.setText("");
            checkDue = true;
        }
        //Complete the method if both the due date and description are valid
        if(checkDesc && checkDue) {
            toDoList.addItem(desc, due, false);
            setMainScene(event);
        }
    }

    @FXML
    private void cancelAddItem(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void displayAllItemsButtonAction (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DisplayAllItemsScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fillAllItemsText() {
        String itemInfo;
        //Check if the list is empty
        if (toDoList.getItemList().size() == 0) {
            allItemsTextArea.appendText("There are currently no items in the list.\n");
        }
        //If not, display all items using ToDoList's displayAllItems()
        else {
            itemInfo = toDoList.displayAllItems();
            allItemsTextArea.appendText(itemInfo);
        }
    }

    @FXML
    private void doneDisplayAllButtonAction(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void displayCompleteItemsButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DisplayCompleteItemsScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fillCompleteItemsText() {
        String itemInfo;
        //Check to see if the list is empty
        if (toDoList.getItemList().size() == 0) {
            completeItemsTextArea.appendText("There are currently no items in the list.\n");
        }
        //If not, display complete items using displayCompleteItems()
        else {
            itemInfo = toDoList.displayCompleteItems();
            //If all items are incomplete, display this case
            if (itemInfo.equals("")) {
                completeItemsTextArea.appendText("All items in the list are incomplete.\n");
            } else {
                completeItemsTextArea.appendText(itemInfo);
            }
        }
    }

    @FXML
    private void doneDisplayCompleteButtonAction(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void displayIncompleteItemsButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DisplayIncompleteItemsScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fillIncompleteItemsText() {
        String itemInfo;
        //Check to see if the list is empty
        if (toDoList.getItemList().size() == 0) {
            incompleteItemsTextArea.appendText("There are currently no items in the list.\n");
        }
        //If not, use displayIncompleteItems() to display items
        else {
            itemInfo = toDoList.displayIncompleteItems();
            //If all items are complete, display this case
            if (itemInfo.equals("")) {
                incompleteItemsTextArea.appendText("All items in the list are complete.\n");
            } else {
                incompleteItemsTextArea.appendText(itemInfo);
            }
        }
    }

    @FXML
    private void doneDisplayIncompleteButtonAction(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void editDescriptionButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditDescriptionScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitNewDescriptionButton(ActionEvent event) throws IOException {
        int itemNum = -1;
        //Get new description from user
        String newDesc = newDescriptionTextField.getText();
        boolean checkNum = false, checkDesc = false;
        //Get item number from user
        try {
            //Check the item number and make sure it is valid and exists
            itemNum = Integer.parseInt(itemNumberTextField.getText());
            itemNum -= 1;
            if(itemNum >= toDoList.getItemList().size() || itemNum < 0) {
                invalidItemNumberLabel.setText("Invalid Item Number: Item Number doesn't exist");
                invalidItemNumberHelpLabel.setText("Note: If you don't know the item's number you'd like to edit, please cancel and refer to\n'Display -> Show All Items' to view the item's number.");
            }
            else  {
                invalidItemNumberLabel.setText("");
                checkNum = true;
            }
        }
        catch (NumberFormatException e) {
            invalidItemNumberLabel.setText("Invalid Item Number: Numbers Only");
        }
        //Check the description and make sure it is valid
        if (newDesc.length() > 256 || newDesc.length() == 0) {
            newDescriptionError.setText("Please ensure description is between 1-256 characters.");
        }
        else {
            newDescriptionError.setText("");
            checkDesc = true;
        }
        //If the description and item number are valid, complete the edit method and return to main menu
        if(checkDesc && checkNum) {
            toDoList.editItemDescription(itemNum, newDesc);
            setMainScene(event);
        }
    }

    @FXML
    private void cancelEditDescription(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void editDueDateButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditDueDateScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitNewDueDateButton(ActionEvent event) throws IOException {
        int itemNum = -1;
        //Get the new due date from user
        String newDueDate = newDueDateTextField.getText();
        boolean checkNum = false, checkDesc = false;
        //Get the item number from user
        try {
            itemNum = Integer.parseInt(itemNumberTextField2.getText());
            itemNum -= 1;
            //Check if the item number is valid and exists
            if(itemNum >= toDoList.getItemList().size() || itemNum < 0) {
                invalidItemNumberLabel2.setText("Invalid Item Number: Item Number doesn't exist");
                invalidItemNumberHelpLabel2.setText("Note: If you don't know the item's number you'd like to edit, please cancel and refer to\n'Display -> Show All Items' to view the item's number.");
            }
            else  {
                invalidItemNumberLabel2.setText("");
                checkNum = true;
            }
        }
        catch (NumberFormatException e) {
            invalidItemNumberLabel2.setText("Invalid Item Number: Numbers Only");
        }
        //Check the due date and make sure it follows the proper format
        if (!newDueDate.matches("^([0-9]{4}[-/]?((0[13-9]|1[012])[-/]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-/]?31|02[-/]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)[-/]?02[-/]?29)$")) {
            newDueDateError.setText("Please ensure due date is in Gregorian Calendar format (YYYY-MM-DD) and is a valid date");
        }
        else {
            newDueDateError.setText("");
            checkDesc = true;
        }
        //If the due date and item number are valid, complete the edit action and return to the main menu
        if(checkDesc && checkNum) {
            toDoList.editDueDate(itemNum, newDueDate);
            setMainScene(event);
        }
    }

    @FXML
    private void cancelEditDueDate(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void markCompleteButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MarkCompleteScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitMarkCompleteButton(ActionEvent event) throws IOException {
        int itemNum = -1;
        boolean checkNum = false;
        //Get the item number from user
        try {
            itemNum = Integer.parseInt(markCompleteItemNumber.getText());
            itemNum -= 1;
            //Check if the item number is valid and exists
            if(itemNum >= toDoList.getItemList().size() || itemNum < 0) {
                markCompleteItemNumberError.setText("Invalid Item Number: Item Number doesn't exist");
                markCompleteItemNumberHelp.setText("Note: If you don't know the item's number you'd like to edit, please cancel and refer to\n'Display -> Show All Items' to view the item's number.");
            }
            else  {
                checkNum = true;
            }
        }
        catch (NumberFormatException e) {
            markCompleteItemNumberError.setText("Invalid Item Number: Numbers Only");
        }
        //If the item number is valid and exists, mark it complete and return to main menu
        if (checkNum) {
            toDoList.markItemComplete(itemNum);
            setMainScene(event);
        }
    }

    @FXML
    private void cancelMarkCompleteButton(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void deleteItemButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteItemScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitDeleteItemButton(ActionEvent event) throws IOException {
        int itemNum = -1;
        boolean checkNum = false;
        //Get the item number from user
        try {
            itemNum = Integer.parseInt(deleteItemNumber.getText());
            itemNum -= 1;
            //Check if the item number is valid and exists
            if(itemNum >= toDoList.getItemList().size() || itemNum < 0) {
                deleteItemError.setText("Invalid Item Number: Item Number doesn't exist");
                deleteItemHelp.setText("Note: If you don't know the item's number you'd like to delete, please cancel and refer to\n'Display -> Show All Items' to view the item's number.");
            }
            else  {
                checkNum = true;
            }
        }
        catch (NumberFormatException e) {
            deleteItemError.setText("Invalid Item Number: Numbers Only");
        }
        //If the item number is valid/exists, remove the associated item and return to main menu
        if (checkNum) {
            toDoList.removeItem(itemNum);
            setMainScene(event);
        }
    }

    @FXML
    private void cancelDeleteItemButton(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void clearAllItemsButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ClearAllItemsScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitClearAllButton(ActionEvent event) throws IOException {
        //Clear all items in the toDoList
        toDoList.clearAllItems();
        setMainScene(event);
    }

    @FXML
    private void cancelClearAllButton(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void saveToDoListButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SaveToDoListScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitSaveToDoList(ActionEvent event) throws IOException {
        //Get the new file name from user
        String fileName = fileNameTextField.getText();
        boolean checkFileName = false;
        //Check to make sure the file name isn't empty
        if(fileName.length() == 0) {
            fileNameErrorLabel.setText("File name entry must not be left blank.");
        }
        else {
            checkFileName = true;
        }
        //If the file name isn't blank, save the toDoList to a new file with the user given name
        if(checkFileName) {
            toDoList.saveItems(fileName);
            setMainScene(event);
        }
    }

    @FXML
    private void cancelSaveToDoList(ActionEvent event) throws IOException {
        setMainScene(event);
    }

    @FXML
    private void loadToDoListButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoadToDoListScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loadFileTextArea () {
        try {
            //Create a file chooser to allow user to select file
            File file = fileChooser.showOpenDialog(new Stage());
            try {
                //Make sure the user selects and opens a valid file
                Scanner scanner = new Scanner(file);
                loadFileErrorLabel.setText("");
                //Display the contents of the file in the text area
                while(scanner.hasNextLine()) {
                    loadFileTextArea.appendText(scanner.nextLine() + "\n");
                }
            } catch (FileNotFoundException e) {
                loadFileErrorLabel.setText("Error: Please ensure you are selecting and opening a valid file.");
            }
        }
        catch (Exception e) {
            loadFileErrorLabel.setText("Error: Please ensure you are selecting and opening a valid file.");
        }
    }

    @FXML
    private void submitLoadFileButton(ActionEvent event) throws IOException {
        setMainScene(event);
    }
}
