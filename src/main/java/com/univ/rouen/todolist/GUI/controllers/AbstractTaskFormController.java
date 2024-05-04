package com.univ.rouen.todolist.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Abstract base controller class for task forms.
 * Provides common functionality and UI components for task form controllers.
 */
public abstract class AbstractTaskFormController {

    @FXML
    protected Label errorLabel;

    @FXML
    protected Button submitButton;

    @FXML
    protected VBox root;

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




}

