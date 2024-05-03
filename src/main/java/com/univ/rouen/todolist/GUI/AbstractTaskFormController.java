package com.univ.rouen.todolist.GUI;

import com.univ.rouen.todolist.task.Priority;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public abstract class AbstractTaskFormController {


    @FXML
    protected Label errorLabel;
    @FXML
    protected Button submitButton;
    @FXML
    protected VBox root;

    protected void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}

