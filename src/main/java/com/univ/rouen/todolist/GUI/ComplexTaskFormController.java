package com.univ.rouen.todolist.GUI;

import com.univ.rouen.todolist.task.Priority;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ComplexTaskFormController extends AbstractTaskFormController implements Initializable {



    @FXML
    private TextField descriptionTextField;
    @FXML
    private ChoiceBox<Priority> priorityChoiceBox;
    @FXML
    private ComboBox<String> parentTaskComboBox;


    @FXML
    private TreeView<String> taskTreeView;

    private boolean isDescriptionLengthValid = true;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priorityChoiceBox.getItems().addAll(Priority.values());
        priorityChoiceBox.setValue(Priority.NORMAL); // Default value
        // Populate parent task combo box (for hierarchy selection)
        parentTaskComboBox.getItems().addAll("Parent Task 1", "Parent Task 2", "Parent Task 3"); // Add your logic to get parent tasks
        submitButton.setDisable(true);


        descriptionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            isDescriptionLengthValid = validateDescriptionLength(newValue);
            updateSubmitButtonState();
        });
    }
    public void submitForm() {
        // Validate fields
        if (descriptionTextField.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        // Get form values
        String description = descriptionTextField.getText();
        Priority priority = priorityChoiceBox.getValue();
        String parentTask = parentTaskComboBox.getValue();


        // Submit form (you can implement your logic here)
        // For demonstration, printing the form values
        System.out.println("Description: " + description);
        //System.out.println("Due Date: " + dueDate);
        System.out.println("Priority: " + priority);
        //System.out.println("Estimated Duration: " + estimatedDuration);
        System.out.println("Parent Task: " + parentTask);
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
        submitButton.setDisable(!(isDescriptionLengthValid));
    }


}