package com.univ.rouen.todolist.model;

import com.univ.rouen.todolist.dataSource.FileDataSource;
import javafx.scene.control.TreeItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages tasks and provides functionality to manipulate tasks.
 */
@Getter
@Setter
public class TaskManager {
    private static TaskManager instance;
    private List<Task> tasks;
    private List<Task> parentTasks;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private TaskManager() {
        this.tasks = new ArrayList<>();
        this.parentTasks = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of TaskManager.
     *
     * @return The singleton instance of TaskManager.
     */
    public static TaskManager getInstance() {
        if (instance == null) {
            synchronized (TaskManager.class) {
                if (instance == null) {
                    instance = new TaskManager();
                }
            }
        }
        return instance;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        Task parentTask = task.getParentTask();
        if (parentTask == null) {
            tasks.add(task);
        } else {
            ComplexTask complexTask = (ComplexTask) parentTask;
            complexTask.addSubTask(task);
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to remove.
     */
    public void removeTask(Task task) {
        if (parentTasks.contains(task)) {
            parentTasks.remove(task);
            ComplexTask parentTask = (ComplexTask) task;
            parentTask.getSubTasks().forEach(this::removeTask);
        }
        tasks.remove(task);
    }

    /**
     * Uses DataSource to read from XML file
     *
     * @param xmlFileName The name of the XML file.
     */
    public void getTasksFromFile(String xmlFileName) {
        FileDataSource dataSource = new FileDataSource();
        this.tasks = dataSource.read(xmlFileName);
        this.parentTasks = new ArrayList<>();
        for(Task task : tasks )
            fillParentsTask(task);
    }

    /**
     * Uses DataSource to write to XML file
     *
     * @param xmlFileName The name of the XML file.
     */
    public void saveTasksToFile(String xmlFileName) {
        FileDataSource dataSource = new FileDataSource();
        dataSource.write(this.tasks, xmlFileName);
    }

    /**
     * Converts the task list to a TreeItem structure for display in a TreeView.
     *
     * @return The root TreeItem representing the task list.
     */
    public TreeItem<Task> convertTaskListToTree() {
        TreeItem<Task> rootItem = new TreeItem<>();
        Map<Task, TreeItem<Task>> taskMap = new HashMap<>();

        for (Task task : this.getTasks()) {
            TreeItem<Task> taskItem = new TreeItem<>(task);
            taskMap.put(task, taskItem);
            if (task.getParentTask() == null) {
                rootItem.getChildren().add(taskItem);
            }
            if (parentTasks.contains(task)) {
                buildTaskTreeRecursively(task, taskItem, taskMap);
            }
        }

        return rootItem;
    }

    /**
     * Recursively builds the tree structure of tasks.
     *
     * @param parentTask The parent task.
     * @param parentItem The parent TreeItem.
     * @param taskMap    A map of tasks to their corresponding TreeItems.
     */
    private void buildTaskTreeRecursively(Task parentTask, TreeItem<Task> parentItem, Map<Task, TreeItem<Task>> taskMap) {
        ComplexTask complexTask = (ComplexTask) parentTask;
        for (Task task : complexTask.getSubTasks()) {
            TreeItem<Task> childItem = new TreeItem<>(task);
            parentItem.getChildren().add(childItem);
            taskMap.put(task, childItem);
            if (parentTasks.contains(task)) {
                buildTaskTreeRecursively(task, childItem, taskMap);
            }
        }
    }


    public  void fillParentsTask(Task task) {
        if (task instanceof ComplexTask) {
            parentTasks.add((ComplexTask) task);
            List<Task> subTasks = ((ComplexTask) task).getSubTasks();
            for (Task subTask : subTasks) {
                if (subTask instanceof ComplexTask) {
                    subTask.setParentTask(task);
                    fillParentsTask(subTask);
                }
            }
        }
    }
}
