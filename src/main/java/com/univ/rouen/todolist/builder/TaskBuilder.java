package com.univ.rouen.todolist.builder;

import com.univ.rouen.todolist.task.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating instances of Task subclasses.
 */
public class TaskBuilder {
    private String description;
    private LocalDate deadline;
    private Priority priority;
    private Integer estimatedDuration;
    private Integer progress;
    private Boolean completed;
    private List<ChildTask> subTasks;

    public TaskBuilder() {
        this.subTasks = new ArrayList<>();
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task.
     * @return This TaskBuilder instance for method chaining.
     */
    public TaskBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the deadline of the task.
     *
     * @param deadline The deadline of the task.
     * @return This TaskBuilder instance for method chaining.
     */
    public TaskBuilder withDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority of the task.
     * @return This TaskBuilder instance for method chaining.
     */
    public TaskBuilder withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Sets the estimated duration of the task.
     *
     * @param estimatedDuration The estimated duration of the task.
     * @return This TaskBuilder instance for method chaining.
     */
    public TaskBuilder withEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
        return this;
    }

    /**
     * Sets the progress of the task.
     *
     * @param progress The progress of the task.
     * @return This TaskBuilder instance for method chaining.
     */
    public TaskBuilder withProgress(Integer progress) {
        this.progress = progress;
        return this;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completed The completion status of the task.
     * @return This TaskBuilder instance for method chaining.
     */
    public TaskBuilder withCompleted(Boolean completed) {
        this.completed = completed;
        return this;
    }

    /**
     * Adds a subtask to the list of subtasks.
     *
     * @param subTask The subtask to be added.
     * @return This TaskBuilder instance for method chaining.
     */
    public TaskBuilder withSubTask(ChildTask subTask) {
        this.subTasks.add(subTask);
        return this;
    }


    /**
     * Builds and returns a ComplexTask instance based on the configured properties.
     *
     * @return A new ComplexTask instance.
     * @throws IllegalStateException if no subtasks are added to the complex task.
     */
    public ComplexTask buildComplexTask() {
        if (subTasks.isEmpty()) {
            throw new IllegalStateException("A ComplexTask must have at least one subtask.");
        }
        return new ComplexTask(description, priority, subTasks);
    }

    /**
     * Builds and returns a BooleanTask instance based on the configured properties.
     *
     * @return A new BooleanTask instance.
     */
    public BooleanTask buildBooleanTask() {
        return new BooleanTask(description, priority, deadline, estimatedDuration, completed);
    }
}

