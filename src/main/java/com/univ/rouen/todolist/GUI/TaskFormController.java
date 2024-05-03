package com.univ.rouen.todolist.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TaskFormController implements Initializable {

    @FXML
    private ChoiceBox<String> formChoiceBox;

    @FXML
    private SubScene subScene;

    @FXML
    private void displaySelectedForm() {
        String selectedForm = formChoiceBox.getValue();
        if (selectedForm == null) {
            return; // No form selected
        }

        switch (selectedForm) {
            case "Complex Task":
                displayComplexTaskForm();
                break;
            case "Boolean Task":
                displayBooleanTaskForm();
                break;
            default:
                //  Do nothing
                break;
        }
    }

    private void displayComplexTaskForm() {
        try {
            Parent complexTaskForm = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/univ/rouen/todolist/ComplexTaskForm-view.fxml")));
            subScene.setRoot(complexTaskForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayBooleanTaskForm() {
        try {
            Parent booleanTaskForm = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/univ/rouen/todolist/BooleanTaskForm-view.fxml")));
            subScene.setRoot(booleanTaskForm);
        } catch (Exception e) {
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
        formChoiceBox.getItems().addAll("Complex Task", "Boolean Task");
        formChoiceBox.setValue("Complex Task");


    }
}
