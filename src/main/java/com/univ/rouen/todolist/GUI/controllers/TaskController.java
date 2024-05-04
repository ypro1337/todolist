package com.univ.rouen.todolist.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for the main task interface.
 * Initializes the GUI and handles user actions.
 */
public class TaskController implements Initializable {

    @FXML
    private Button addTaskButton;
    @FXML
    private SubScene subScene;
    @FXML
    private Button showTreeTableViewButton;
    @FXML
    private Button hideTreeTableViewButton;
    private TreeTableController treeTableController;

    /**
     * Displays the TreeTableView in the SubScene.
     */
    @FXML
    private void showTreeTableView() {
        subScene.setVisible(true);
        // Hide the showTreeTableViewButton and show the hideTreeTableViewButton
        showTreeTableViewButton.setVisible(false);
        hideTreeTableViewButton.setVisible(true);
    }

    /**
     * Hides the TreeTableView in the SubScene.
     */
    @FXML
    private void hideTreeTableView() {
        subScene.setVisible(false);
        // Hide the hideTreeTableViewButton and show the showTreeTableViewButton
        hideTreeTableViewButton.setVisible(false);
        showTreeTableViewButton.setVisible(true);
    }



    /**
     * Displays the task form in a new window.
     */
    @FXML
    private void showTaskForm() {
        try {
            // Load the TaskForm.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/views/TaskForm.fxml"));
            Region content = loader.load();
            subScene.setRoot(content);
            subScene.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The views used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/views/TreeTable.fxml"));
            Region content = loader.load();
            treeTableController = loader.getController();
            subScene.setRoot(content);
            subScene.setVisible(false); // Initially hide the subScene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the task form in a new window.
     */
    @FXML
    public void addTask(ActionEvent actionEvent) {
        try {
            // Load the TaskForm.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/views/TaskForm.fxml"));
            Parent root = (Parent)loader.load();

            // Create a new stage for the task form
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Task");
            stage.setScene(new Scene(root, 400, 350));

            // Show the stage
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Saves the current tree to an XML file.
     */
    @FXML
    public void saveTree(ActionEvent actionEvent) {
        // Implement saving logic here
    }

    /**
     * Loads a tree from an XML file.
     */
    @FXML
    public void openTree(ActionEvent actionEvent) {
        // Implement loading logic here
    }
}
