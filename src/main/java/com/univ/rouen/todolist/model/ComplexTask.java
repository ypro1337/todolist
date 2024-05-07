package com.univ.rouen.todolist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complex model in the to-do list application.
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "complexTask")
@XmlAccessorType(XmlAccessType.NONE)
public class ComplexTask extends Task {

    @XmlElements({@XmlElement(name = "booleanSubTask"),@XmlElement(name = "complexSubTask")})
    private List<Task> subTasks;

    @XmlAttribute
    private Double progress;

    /**
     * Creates a new ComplexTask instance with the given description and priority.
     *
     * @param description The description of the complex task.
     * @param priority    The priority of the complex task.
     */
    public ComplexTask(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>();
    }

    /**
     * Creates a new ComplexTask instance with the given description, priority, and parent task.
     *
     * @param description The description of the complex task.
     * @param priority    The priority of the complex task.
     * @param parentTask  The parent task of the complex task.
     */
    public ComplexTask(String description, Priority priority, Task parentTask) {
        this.description = description;
        this.priority = priority;
        this.parentTask = parentTask;
        this.subTasks = new ArrayList<>();
        updateParents();
    }

    /**
     * Creates a new ComplexTask instance with the given description, priority, and list of subtasks.
     *
     * @param description The description of the complex task.
     * @param priority    The priority of the complex task.
     * @param tasks       The list of subtasks of the complex task.
     */
    public ComplexTask(String description, Priority priority, List<Task> tasks) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>();
        this.subTasks = tasks;
        setParentTasks();
    }

    /**
     * Creates a new ComplexTask instance with the given description, priority, parent task, and list of subtasks.
     *
     * @param description The description of the complex task.
     * @param priority    The priority of the complex task.
     * @param parentTask  The parent task of the complex task.
     * @param tasks       The list of subtasks of the complex task.
     */
    public ComplexTask(String description, Priority priority, Task parentTask, List<Task> tasks) {
        this.description = description;
        this.priority = priority;
        this.parentTask = parentTask;
        this.subTasks = new ArrayList<>();
        this.subTasks = tasks;
        updateParents();
    }

    /**
     * Checks if all subtasks are completed.
     *
     * @return True if all subtasks are completed, otherwise false.
     */
    @Override
    public Boolean isCompleted() {
        return this.completed;
    }

    /**
     * Sets the completion state of all subtasks.
     *
     * @param completed The completion state to set for all subtasks.
     */
    @Override
    public void setCompleted(Boolean completed) {
        subTasks.forEach(subTask -> subTask.setCompleted(completed));
    }

    /**
     * Updates the due date of the complex model based on its subtasks.
     */
    public void updateDueDate() {
        LocalDate maxDueDate = LocalDate.now();

        for (Task task : subTasks) {
            if (task.dueDate == null)
                continue;

            maxDueDate = maxDueDate.isBefore(task.getDueDate()) ? task.getDueDate() : maxDueDate;
        }
        this.setDueDate(maxDueDate);
    }

    /**
     * Updates the estimated duration of the complex model based on its subtasks.
     */
    public void updateEstimatedDuration() {
        Integer totalDuration = 0;
        for (Task task : subTasks) {
            if (task.getEstimatedDuration() == null)
                continue;
            totalDuration += task.getEstimatedDuration();
        }
        this.setEstimatedDuration(totalDuration);
    }

    /**
     * Updates the progress of the complex model based on its subtasks.
     */
    public void updateProgress() {
        Double totalProgress = 0.0;
        double subTaskCount = subTasks.size();
        if (subTaskCount > 0) {
            for (Task task : subTasks) {
                if (!TaskManager.getInstance().getParentTasks().contains(task)) {
                    totalProgress += task.isCompleted() ? 100.0 : 0;
                } else {
                    ComplexTask complexTask = (ComplexTask) task;
                    Double taskProgress = complexTask.getProgress();
                    if (taskProgress != null)
                        totalProgress += taskProgress;
                }
            }
            this.progress = totalProgress / subTaskCount;
        }
    }

    /**
     * Returns the string representation of the model.
     *
     * @return The description of the model.
     */
    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * Updates the model and its parent tasks.
     */
    @Override
    public void updateParents() {
        this.updateDueDate();
        this.updateProgress();
        this.updateEstimatedDuration();
        super.updateParents();
    }

    /**
     * Adds a subtask to the complex task.
     *
     * @param childTask The subtask to add.
     */
    public void addSubTask(Task childTask) {
        this.subTasks.add(childTask);
        updateParents();
    }

    /**
     * Sets its self as the parent task of its subtasks.
     *
     */
    public void setParentTasks() {
        for (Task subTask : subTasks) {
            subTask.setParentTask(this);
        }
    }
}
