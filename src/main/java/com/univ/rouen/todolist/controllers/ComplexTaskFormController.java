package com.univ.rouen.todolist.controllers;

import com.univ.rouen.todolist.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the ComplexTask form.
 * Extends AbstractTaskFormController and implements Initializable.
 */
public class ComplexTaskFormController extends AbstractTaskFormController implements Initializable {

    private TaskManager taskManager;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private ChoiceBox<Priority> priorityChoiceBox;

    @FXML
    private TreeView<String> taskTreeView;

    private boolean isDescriptionLengthValid = true;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up event listeners for form fields.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskManager = TaskManager.getInstance();
        priorityChoiceBox.getItems().addAll(Priority.values());
        priorityChoiceBox.setValue(Priority.NORMAL); // Default value
        parentTaskComboBox.getItems().addAll(taskManager.getParentTasks());
        submitButton.setDisable(true);

        descriptionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            isDescriptionLengthValid = validateDescriptionLength(newValue);
            updateSubmitButtonState();
        });
    }

    /**
     * Handles the form submission.
     * Validates form fields and retrieves form values.
     * For demonstration, prints the form values.
     */
    public void submitForm() {
        // Validate fields
        if (descriptionTextField.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        // Get form values
        String description = descriptionTextField.getText();
        Priority priority = priorityChoiceBox.getValue();
        Task parentTask = parentTaskComboBox.getValue();

        ComplexTask complexTask = new ComplexTaskBuilder()
                .description(description)
                .priority(priority)
                .parentTask(parentTask)
                .build();

        taskManager.getParentTasks().add(complexTask);
        taskManager.addTask(complexTask);
        closeFormWindow();
        //TODO Create TreeItem + Task
    }

    /**
     * Updates the state of the submit button based on field validations.
     */
    private void updateSubmitButtonState() {
        submitButton.setDisable(!(isDescriptionLengthValid));
    }
}
