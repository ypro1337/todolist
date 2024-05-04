package com.univ.rouen.todolist.gui.controllers;

import com.univ.rouen.todolist.task.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class    TreeTableController implements Initializable {

    @FXML
    private TreeTableView<ModifiableTask> taskTreeTableView;
    @FXML
    private TreeTableColumn<ModifiableTask,String> description;
    @FXML
    private TreeTableColumn<ModifiableTask, LocalDate>  dueDate;
    @FXML
    private TreeTableColumn<ModifiableTask, Priority>  priority;
    @FXML
    private TreeTableColumn<ModifiableTask,Integer>  estimatedDuration;
    @FXML
    private TreeTableColumn<ModifiableTask,Double>  progress;

    /**
     * show prompt message
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

        // Description column setup
        description.setCellValueFactory(taskStringCellDataFeatures -> {
            ModifiableTask task =  taskStringCellDataFeatures.getValue().getValue();
            return new SimpleStringProperty(task.getDescription());
        });
        description.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        description.setOnEditCommit(TaskStringCellEditEvent -> {
            String newVal = TaskStringCellEditEvent.getNewValue();
            String oldVal = TaskStringCellEditEvent.getOldValue();
            ModifiableTask currentTask =TaskStringCellEditEvent.getTreeTablePosition().getTreeItem().getValue();
            currentTask.setDescription(newVal);
            showPrompt("Description has been modified from '" + oldVal + "' to '" + newVal + "'", TaskStringCellEditEvent.getTreeTableView());
        });

        // Priority column setup
        priority.setCellValueFactory(taskPriorityCellDataFeatures -> {
            ModifiableTask currentTask = taskPriorityCellDataFeatures.getValue().getValue();
            return new SimpleObjectProperty<>(currentTask.getPriority());
        });

        // Due Date column setup
        dueDate.setCellValueFactory(taskLocalDateCellDataFeatures -> new SimpleObjectProperty<>(taskLocalDateCellDataFeatures.getValue().getValue().getDueDate()));
        dueDate.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new LocalDateStringConverter()));
        dueDate.setOnEditCommit((TreeTableColumn.CellEditEvent<ModifiableTask, LocalDate> event) -> {
            LocalDate newVal = event.getNewValue();
            LocalDate oldVal = event.getOldValue();
            ModifiableTask currentTask = event.getTreeTablePosition().getTreeItem().getValue();
            currentTask.setDueDate(newVal);
            showPrompt("Description has been modified from '" + oldVal + "' to '" + newVal + "'", event.getTreeTableView());
        });

        // Estimated Duration column setup
        estimatedDuration.setCellValueFactory(taskIntegerCellDataFeatures -> new SimpleObjectProperty<>(taskIntegerCellDataFeatures.getValue().getValue().getEstimatedDuration()));
        estimatedDuration.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new IntegerStringConverter()));
        estimatedDuration.setOnEditCommit((TreeTableColumn.CellEditEvent<ModifiableTask, Integer> event) -> {
            Integer oldValue =event.getOldValue();
            Integer newValue= event.getNewValue();
            ModifiableTask currentTask = event.getTreeTablePosition().getTreeItem().getValue();
            currentTask.setEstimatedDuration(newValue);
            showPrompt("Description has been modified from '" + oldValue + "' to '" + newValue + "'", event.getTreeTableView());
        });

        // Progress column setup
        progress.setCellValueFactory(taskIntegerCellDataFeatures -> new SimpleObjectProperty<>( taskIntegerCellDataFeatures.getValue().getValue().getProgress()));
        progress.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn(new DoubleStringConverter()));

        // TreeTableView setup
        taskTreeTableView.setEditable(true);
        BooleanTask boolTask = new BooleanTask("Child ModifiableTask",Priority.SECONDARY,LocalDate.MAX,20,false);
        TreeItem<ModifiableTask> rootItem = new TreeItem<>(new ComplexTask("Root ModifiableTask",Priority.NORMAL,boolTask));
        taskTreeTableView.setRoot(rootItem);

    }
}
