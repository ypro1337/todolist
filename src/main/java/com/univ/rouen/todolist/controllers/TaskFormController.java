package com.univ.rouen.todolist.controllers;

import com.univ.rouen.todolist.model.TaskManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class for the model form selector.
 * Initializes the form choice box and handles the display of different model forms.
 */
public class TaskFormController extends AbstractTaskFormController implements Initializable {

    @FXML
    private ChoiceBox<String> formChoiceBox;

    @FXML
    private SubScene subScene;

    /**
     * Displays the selected model form in the SubScene.
     */
    @FXML
    public void displaySelectedForm() {
        String selectedForm = formChoiceBox.getValue();
        switch (selectedForm) {
            case "Complex Task":
                displayComplexTaskForm();
                break;
            case "Boolean Task":
                displayBooleanTaskForm();
                break;
            default:
                // Do nothing
                break;
        }
    }

    /**
     * Displays the complex model form in the SubScene.
     */
    public void displayComplexTaskForm() {
        try {
            Parent complexTaskForm = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/univ/rouen/todolist/views/ComplexTaskForm.fxml")));
            subScene.setRoot(complexTaskForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the boolean model form in the SubScene.
     */
    public void displayBooleanTaskForm() {
        try {
            Parent booleanTaskForm = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/univ/rouen/todolist/views/BooleanTaskForm.fxml")));
            subScene.setRoot(booleanTaskForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or {@code null} if the location is not known.
     * @param resources The views used to localize the root object, or {@code null} if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TaskManager taskManager = TaskManager.getInstance();
        formChoiceBox.getItems().addAll("Complex Task", "Boolean Task");
        formChoiceBox.setValue("Complex Task");
    }
}
