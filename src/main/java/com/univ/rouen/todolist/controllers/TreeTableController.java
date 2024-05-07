package com.univ.rouen.todolist.controllers;

import com.univ.rouen.todolist.model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;


/**
 * Controller class for managing the TreeTableView displaying tasks.
 */
public class TreeTableController implements Initializable {

    @FXML
    private TreeTableView<Task> taskTreeTableView;
    @FXML
    private TreeTableColumn<Task,String> description;
    @FXML
    private TreeTableColumn<Task, LocalDate>  dueDate;
    @FXML
    private TreeTableColumn<Task, Priority>  priority;
    @FXML
    private TreeTableColumn<Task,Integer>  estimatedDuration;
    @FXML
    private TreeTableColumn<Task,Double>  progress;
    @FXML
    private Button deleteButton;

    private TaskManager taskManager;

    /**
     * Displays a prompt message.
     *
     * @param message The message to display.
     * @param node    The node relative to which the message is displayed.
     */
    private void showPrompt(String message, Node node) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true);
        tooltip.setShowDelay(Duration.millis(100)); // Adjust the delay as needed
        tooltip.setHideDelay(Duration.seconds(2)); // Set hide delay to 2 seconds
        tooltip.show(node, node.getScene().getWindow().getX(), node.getScene().getWindow().getY() + node.getScene().getWindow().getHeight());
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up the columns of the TreeTableView and defines cell factories for editing.
     * Populates the TreeTableView with sample data.
     *
     * @param url           The location used to resolve relative paths for the root object, or {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.taskManager = TaskManager.getInstance();
        setupTreeTableView();
        TreeItem<Task> rootItem = taskManager.convertTaskListToTree();
        taskTreeTableView.setRoot(rootItem);
        taskTreeTableView.setShowRoot(false);
    }

    /**
     * Sets up the columns of the TreeTableView and defines cell factories for editing.
     */
    private void setupTreeTableView() {
        // Description column setup
        description.setCellValueFactory(taskStringCellDataFeatures -> {
            Task task =  taskStringCellDataFeatures.getValue().getValue();
            return new SimpleStringProperty(task.getDescription());
        });
        description.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        description.setOnEditCommit(TaskStringCellEditEvent -> {
            String newVal = TaskStringCellEditEvent.getNewValue();
            String oldVal = TaskStringCellEditEvent.getOldValue();
            Task currentTask =TaskStringCellEditEvent.getTreeTablePosition().getTreeItem().getValue();
            currentTask.setDescription(newVal);
            showPrompt("Description has been modified from '" + oldVal + "' to '" + newVal + "'", TaskStringCellEditEvent.getTreeTableView());
        });

        // Priority column setup
        priority.setCellValueFactory(taskPriorityCellDataFeatures -> {
            Task currentTask = taskPriorityCellDataFeatures.getValue().getValue();
            return new SimpleObjectProperty<>(currentTask.getPriority());
        });
        priority.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new StringConverter<Priority>() {
            @Override
            public String toString(Priority object) {
                return object == null ? null : object.toString();
            }

            @Override
            public Priority fromString(String string) {
                switch (string.toUpperCase()) {
                    case "URGENT":
                        return Priority.URGENT;
                    case "NORMAL":
                        return Priority.NORMAL;
                    case "SECONDARY":
                        return Priority.SECONDARY;
                    default:
                        return null;
                }
            }
        }));
        priority.setOnEditCommit((TreeTableColumn.CellEditEvent<Task,Priority> event) -> {
            Priority oldVal = event.getOldValue();
            Priority newVal = event.getNewValue();
            if (newVal != null) {
                Task currentTask = event.getTreeTablePosition().getTreeItem().getValue();
                currentTask.setPriority(newVal);
                showPrompt("Priority has been modified from '" + oldVal + "' to '" + newVal + "'", event.getTreeTableView());
            }
        });

        // Due Date column setup
        dueDate.setCellValueFactory(taskLocalDateCellDataFeatures -> new SimpleObjectProperty<>(taskLocalDateCellDataFeatures.getValue().getValue().getDueDate()));
        dueDate.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new LocalDateStringConverter()));
        dueDate.setOnEditCommit((TreeTableColumn.CellEditEvent<Task, LocalDate> event) -> {
            try {
                LocalDate newVal = event.getNewValue();
                LocalDate oldVal = event.getOldValue();

                Task currentTask = event.getTreeTablePosition().getTreeItem().getValue();
                if (!this.taskManager.getParentTasks().contains(currentTask)) {
                    currentTask.setDueDate(newVal);
                    showPrompt("Due Date has been modified from '" + oldVal + "' to '" + newVal + "'", event.getTreeTableView());
                } else {
                    showPrompt("Cannot Modify Due Date!", event.getTreeTableView());
                }
            } catch (DateTimeParseException e) {
                showPrompt("Try a valid Date!", event.getTreeTableView());
            }
        });

        // Estimated Duration column setup
        estimatedDuration.setCellValueFactory(taskIntegerCellDataFeatures -> new SimpleObjectProperty<>(taskIntegerCellDataFeatures.getValue().getValue().getEstimatedDuration()));
        estimatedDuration.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new IntegerStringConverter()));
        estimatedDuration.setOnEditCommit((TreeTableColumn.CellEditEvent<Task, Integer> event) -> {
            Integer oldValue = event.getOldValue();
            Integer newValue = event.getNewValue();
            Task currentTask = event.getTreeTablePosition().getTreeItem().getValue();
            currentTask.setEstimatedDuration(newValue);
            showPrompt("Estimated Duration has been modified from '" + oldValue + "' to '" + newValue + "'", event.getTreeTableView());
        });

        // Progress column setup
        progress.setCellValueFactory(taskDoubleCellDataFeatures -> {
            Task task = taskDoubleCellDataFeatures.getValue().getValue();
            if (taskManager.getParentTasks().contains(task)) {
                if (((ComplexTask) task).getProgress() != null) {
                    Double progressPercentage = ((ComplexTask) task).getProgress();
                    return new SimpleObjectProperty<>(progressPercentage);
                } else {
                    return new SimpleObjectProperty<>(0.0);
                }
            } else {
                if (task.isCompleted() == null)
                    return new SimpleObjectProperty<>(0.0);
                Double progressPercentage = task.isCompleted() ? 100.0 : 0.0;
                return new SimpleObjectProperty<>(progressPercentage);
            }
        });
        progress.setCellFactory(column -> new TextFieldTreeTableCell<>(new StringConverter<Double>() {

            @Override
            public String toString(Double object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Double fromString(String string) {
                try {
                    // Try to parse the input text as a Double
                    return Double.parseDouble(string);
                } catch (NumberFormatException e) {
                    // Return null if parsing fails
                    return null;
                }
            }
        }) {
            @Override
            public void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(item) + "%");
                }
            }
        });
        progress.setOnEditCommit((TreeTableColumn.CellEditEvent<Task, Double> event) -> {
            try {
                Double newValue = event.getNewValue();
                Double oldValue = event.getOldValue();
                Task currentTask = event.getTreeTablePosition().getTreeItem().getValue();
                if (!this.taskManager.getParentTasks().contains(currentTask)) {
                    if (newValue == 0.0) {
                        currentTask.setCompleted(false);
                    } else if (newValue == 100.0) {
                        currentTask.setCompleted(true);
                    } else {
                        showPrompt("Invalid input for progress. Please enter either  0.0 or 100.0.", event.getTreeTableView());
                    }
                    refreshTreeTableView();
                }
            } catch (NumberFormatException e) {
                showPrompt("Invalid input. Please enter a numeric value.", event.getTreeTableView());
            }
        });

        taskTreeTableView.setEditable(true);
    }

    @FXML
    private void deleteSelectedRecord() {
        Task selectedTask = taskTreeTableView.getSelectionModel().getSelectedItem().getValue();
        taskManager.removeTask(selectedTask);
        refreshTreeTableView();
    }

    @FXML
    private void refreshTreeTableView() {
        this.initialize(null, null);
    }

    @FXML
    private void buttonHoverEntered() {
        deleteButton.setStyle("-fx-background-color: #cc0000;"); // Darker red on hover
    }

    @FXML
    private void buttonHoverExited() {
        deleteButton.setStyle("-fx-background-color: #ff0000;"); // Original red color
    }
}
