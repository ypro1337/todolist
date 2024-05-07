package com.univ.rouen.todolist.controllers;

import com.univ.rouen.todolist.model.TaskManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the main model interface.
 * Initializes the GUI and handles user actions.
 */
public class TaskController implements Initializable {

    @FXML
    private Button addTaskButton;

    @FXML
    private SubScene subScene;

    @FXML
    private Button TreeTableViewButton;

    private TreeTableController treeTableController;

    private TaskManager taskManager;

    /**
     * Displays the TreeTableView in the SubScene.
     */
    @FXML
    public void displayTreeTableView() {
        if (!subScene.isVisible()) {
            treeTableController.initialize(null, null);
            subScene.setVisible(true);
            TreeTableViewButton.setText("Hide Tree Table View");
        } else {
            subScene.setVisible(false);
            TreeTableViewButton.setText("Show Tree Table View");
        }
    }

    /**
     * Displays the model form in a new window.
     */
    @FXML
    public void showTaskForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/views/TaskForm.fxml"));
            Region content = loader.load();
            subScene.setRoot(content);
            subScene.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/views/TreeTable.fxml"));
            Region content = loader.load();
            treeTableController = loader.getController();
            subScene.setRoot(content);
            subScene.setVisible(false);
            taskManager = TaskManager.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the model form in a new window.
     */
    @FXML
    public void addTask(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/univ/rouen/todolist/views/TaskForm.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Task");
            stage.setScene(new Scene(root, 400, 350));

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save XML File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                if (file.createNewFile()) {
                    taskManager.saveTasksToFile(file.getCanonicalPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads a tree from an XML file.
     */
    @FXML
    public void openTree(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                taskManager.getTasksFromFile(file.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
