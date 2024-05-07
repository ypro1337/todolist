package com.univ.rouen.todolist.model;

import java.time.LocalDate;

/**
 * A builder class for creating instances of BooleanTask.
 */
public class BooleanTaskBuilder implements TaskBuilder {

    /**
     * The description of the task.
     */
    private String description;

    /**
     * The due date of the task.
     */
    private LocalDate dueDate;

    /**
     * The priority of the task.
     */
    private Priority priority;

    /**
     * The estimated duration of the task.
     */
    private Integer estimatedDuration;

    /**
     * The completion state of the task.
     */
    private Boolean completed;

    /**
     * The parent task of the task.
     */
    private Task parentTask;

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task.
     * @return This BooleanTaskBuilder instance.
     */
    public BooleanTaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority of the task.
     * @return This BooleanTaskBuilder instance.
     */
    public BooleanTaskBuilder priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Sets the parent task of the task.
     *
     * @param parentTask The parent task of the task.
     * @return This BooleanTaskBuilder instance.
     */
    public BooleanTaskBuilder parentTask(Task parentTask) {
        this.parentTask = parentTask;
        return this;
    }

    /**
     * Sets the due date of the task.
     *
     * @param dueDate The due date of the task.
     * @return This BooleanTaskBuilder instance.
     */
    public BooleanTaskBuilder dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    /**
     * Sets the estimated duration of the task.
     *
     * @param estimatedDuration The estimated duration of the task.
     * @return This BooleanTaskBuilder instance.
     */
    public BooleanTaskBuilder estimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
        return this;
    }

    /**
     * Sets the completion state of the task.
     *
     * @param completed The completion state of the task.
     * @return This BooleanTaskBuilder instance.
     */
    public BooleanTaskBuilder completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    /**
     * Builds a new BooleanTask instance based on the set properties.
     *
     * @return A new BooleanTask instance.
     */
    public BooleanTask build() {
        if (this.parentTask != null) {
            return new BooleanTask(description, priority, dueDate, estimatedDuration, completed, parentTask);
        }
        return new BooleanTask(description, priority, dueDate, estimatedDuration, completed);
    }
}
