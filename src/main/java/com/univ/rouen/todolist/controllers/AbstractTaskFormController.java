package com.univ.rouen.todolist.controllers;

import com.univ.rouen.todolist.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Abstract base controller class for model forms.
 * Provides common functionality and UI components for model form controllers.
 */
public abstract class AbstractTaskFormController {

    @FXML
    protected Label errorLabel;

    @FXML
    protected Button submitButton;

    @FXML
    protected VBox root;

    @FXML
    protected ComboBox<Task> parentTaskComboBox;

    /**
     * Displays a warning alert with the given message.
     *
     * @param message The message to display in the alert.
     */
    protected void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Validates the length of the entered description.
     *
     * @param newValue The entered description.
     * @return True if the description length is valid, otherwise false.
     */
    protected boolean validateDescriptionLength(String newValue) {
        if (newValue.length() > 20) {
            errorLabel.setText("Description length cannot exceed 20 characters");
            errorLabel.setStyle("-fx-text-fill: red;");
            return false;
        }
        errorLabel.setText("");
        return true;
    }

    /**
     * Closes the form window.
     */
    protected void closeFormWindow() {
        // Get the stage (window) of any JavaFX control in the scene
        Stage stage = (Stage) submitButton.getScene().getWindow();
        // Close the stage
        stage.close();
    }
}
