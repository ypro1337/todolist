package com.univ.rouen.todolist.GUI;

import com.univ.rouen.todolist.task.Priority;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BooleanTaskFormController extends AbstractTaskFormController implements Initializable {



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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priorityChoiceBox.getItems().addAll(Priority.values());
        priorityChoiceBox.setValue(Priority.NORMAL); // Default value

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

    private boolean validateDuration(String newValue) {
        try{
            if (!newValue.matches("\\d+") || Integer.parseInt(newValue) < 0) {
                errorLabel.setText("Estimated duration must be non-negative number");
                return false ;
            }
        }catch (NumberFormatException e){

        }
        errorLabel.setText("");
        return true;
    }

    private boolean validateDescriptionLength(String newValue) {
        if (newValue.length() > 20) {
            errorLabel.setText("Description length cannot exceed 20 characters");
            errorLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        errorLabel.setText("");
        return true;
    }

    private void updateSubmitButtonState() {
        submitButton.setDisable(!(isDateValid && isDurationValid && isDescriptionLengthValid));
    }
    public void submitForm() {
        // Validate fields


        // Get form values
        String description = descriptionTextField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        Priority priority = priorityChoiceBox.getValue();
        int estimatedDuration = Integer.parseInt(estimatedDurationTextField.getText());
        boolean completed = completedCheckBox.isSelected();

        // Submit form (you can implement your logic here)
        // For demonstration, printing the form values
        System.out.println("Description: " + description);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Priority: " + priority);
        System.out.println("Estimated Duration: " + estimatedDuration);
        System.out.println("Completed: " + completed);
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

}
