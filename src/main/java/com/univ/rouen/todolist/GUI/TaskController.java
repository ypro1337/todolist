package com.univ.rouen.todolist.GUI;

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

    @FXML
    private void showTreeTableView() {
        subScene.setVisible(true);
        // Hide the showTreeTableViewButton and show the hideTreeTableViewButton
        showTreeTableViewButton.setVisible(false);
        hideTreeTableViewButton.setVisible(true);
    }

    // Method to hide the TreeTableView in the SubScene
    @FXML
    private void hideTreeTableView() {
        subScene.setVisible(false);
        // Hide the hideTreeTableViewButton and show the showTreeTableViewButton
        hideTreeTableViewButton.setVisible(false);
        showTreeTableViewButton.setVisible(true);
    }



    @FXML
    private void showTaskForm() {
        try {
            // Load the TaskForm.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/TaskForm-view.fxml"));
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
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/TreeTable-view.fxml"));
            Region content = loader.load();
            treeTableController = loader.getController();
            subScene.setRoot(content);
            subScene.setVisible(false); // Initially hide the subScene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addTask(ActionEvent actionEvent) {
        try {
            // Load the TaskForm.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/TaskForm-view.fxml"));
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
    @FXML
    public void saveTree(ActionEvent actionEvent) {
    }
    @FXML
    public void openTree(ActionEvent actionEvent) {
    }
}
