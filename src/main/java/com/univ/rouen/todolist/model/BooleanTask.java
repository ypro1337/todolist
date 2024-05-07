package com.univ.rouen.todolist.model;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

/**
 * Represents a boolean model in the to-do list application.
 */
@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "booleanTask")
@XmlAccessorType(XmlAccessType.NONE)
public class BooleanTask extends Task {

    /**
     * Constructs a new BooleanTask with the given parameters.
     *
     * @param description      The description of the task.
     * @param priority         The priority of the task.
     * @param dueDate          The due date of the task.
     * @param estimatedDuration The estimated duration of the task.
     * @param completed        The completion state of the task.
     */
    public BooleanTask(String description, Priority priority, LocalDate dueDate, Integer estimatedDuration, Boolean completed) {
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.dueDate = dueDate;
        this.estimatedDuration = estimatedDuration;
    }

    /**
     * Constructs a new BooleanTask with the given parameters and a parent task.
     *
     * @param description      The description of the task.
     * @param priority         The priority of the task.
     * @param dueDate          The due date of the task.
     * @param estimatedDuration The estimated duration of the task.
     * @param completed        The completion state of the task.
     * @param parentTask       The parent task of the task.
     */
    public BooleanTask(String description, Priority priority, LocalDate dueDate, Integer estimatedDuration, Boolean completed, Task parentTask) {
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.dueDate = dueDate;
        this.estimatedDuration = estimatedDuration;
        this.parentTask = parentTask;
        updateParents();
    }

    /**
     * Sets the completion state of the task and updates the parent tasks.
     *
     * @param completed The completion state to set.
     */
    @Override
    public void setCompleted(Boolean completed) {
        super.setCompleted(completed);
        updateParents();
    }

    /**
     * Sets the due date of the task and updates the parent tasks.
     *
     * @param dueDate The due date to set.
     */
    @Override
    public void setDueDate(LocalDate dueDate) {
        super.setDueDate(dueDate);
        updateParents();
    }

    /**
     * Sets the estimated duration of the task and updates the parent tasks.
     *
     * @param estimatedDuration The estimated duration to set.
     */
    @Override
    public void setEstimatedDuration(Integer estimatedDuration) {
        super.setEstimatedDuration(estimatedDuration);
        updateParents();
    }

    /**
     * Sets the parent task of the task and updates the parent tasks.
     *
     * @param parentTask The parent task to set.
     */
    @Override
    public void setParentTask(Task parentTask) {
        super.setParentTask(parentTask);
        updateParents();
    }
}
