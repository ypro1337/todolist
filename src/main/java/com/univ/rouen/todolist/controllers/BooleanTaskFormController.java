package com.univ.rouen.todolist.controllers;

import com.univ.rouen.todolist.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller class for the BooleanTask form.
 * Extends AbstractTaskFormController and implements Initializable.
 */
public class BooleanTaskFormController extends AbstractTaskFormController implements Initializable {

    private TaskManager taskManager;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private ChoiceBox<Priority> priorityChoiceBox;

    @FXML
    private TextField estimatedDurationTextField;

    @FXML
    private CheckBox completedCheckBox;

    private boolean isDateValid = true;
    private boolean isDurationValid = true;
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
        parentTaskComboBox.setValue(null);
        // Set today's date to dateField
        dueDatePicker.setValue(LocalDate.now());
        estimatedDurationTextField.setText("0");
        // Disable submitButton initially
        submitButton.setDisable(true);

        // Add listeners to form fields
        estimatedDurationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            isDurationValid = validateDuration(newValue);
            updateSubmitButtonState();
        });

        dueDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            isDateValid = validateDate(newValue);
            updateSubmitButtonState();
        });

        descriptionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            isDescriptionLengthValid = validateDescriptionLength(newValue);
            updateSubmitButtonState();
        });
    }

    /**
     * Validates the selected date.
     *
     * @param date The selected date.
     * @return True if the date is valid, otherwise false.
     */
    private boolean validateDate(LocalDate date) {
        try {
            if (date.isBefore(LocalDate.now())) {
                errorLabel.setText("Date cannot be before today");
                errorLabel.setStyle("-fx-text-fill: red;");
                return false;
            }
            errorLabel.setText("");
            return true;
        } catch (Exception e) {
            errorLabel.setText("Invalid date format");
            errorLabel.setStyle("-fx-text-fill: red;");
            updateSubmitButtonState();
            return false;
        }
    }

    /**
     * Validates the entered estimated duration.
     *
     * @param newValue The entered estimated duration.
     * @return True if the estimated duration is valid, otherwise false.
     */
    private boolean validateDuration(String newValue) {
        try {
            if (!newValue.matches("\\d+") || Integer.parseInt(newValue) < 0) {
                errorLabel.setText("Estimated duration must be non-negative number");
                return false;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        errorLabel.setText("");
        return true;
    }

    /**
     * Updates the state of the submit button based on field validations.
     */
    private void updateSubmitButtonState() {
        submitButton.setDisable(!(isDateValid && isDurationValid && isDescriptionLengthValid));
    }

    /**
     * Handles the form submission.
     * Retrieves the form values and submits the form.
     */
    public void submitForm() {
        // Validate fields
        if (descriptionTextField.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }
        // Get form values
        String description = descriptionTextField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        Priority priority = priorityChoiceBox.getValue();
        Integer estimatedDuration = Integer.parseInt(estimatedDurationTextField.getText());
        Boolean completed = completedCheckBox.isSelected();
        Task parentTask = parentTaskComboBox.getValue();
        BooleanTask booleanTask = new BooleanTaskBuilder()
                .description(description)
                .priority(priority)
                .dueDate(dueDate).estimatedDuration(estimatedDuration)
                .completed(completed)
                .parentTask(parentTask)
                .build();
        taskManager.addTask(booleanTask);
        booleanTask.updateParents();
        //close and refresh
        closeFormWindow();
    }
}
