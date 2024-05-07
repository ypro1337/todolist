package com.univ.rouen.todolist.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating ComplexTask objects.
 */
public class ComplexTaskBuilder implements TaskBuilder {
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Task parentTask;
    private List<Task> subTasks = new ArrayList<>();

    /**
     * Sets the description for the ComplexTask.
     *
     * @param description The description of the ComplexTask.
     * @return The ComplexTaskBuilder instance.
     */
    public ComplexTaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the parent task for the ComplexTask.
     *
     * @param parentTask The parent task of the ComplexTask.
     * @return The ComplexTaskBuilder instance.
     */
    public ComplexTaskBuilder parentTask(Task parentTask) {
        this.parentTask = parentTask;
        return this;
    }

    /**
     * Sets the priority for the ComplexTask.
     *
     * @param priority The priority of the ComplexTask.
     * @return The ComplexTaskBuilder instance.
     */
    public ComplexTaskBuilder priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Adds a sub-task to the ComplexTask.
     *
     * @param subTask The sub-task to be added to the ComplexTask.
     * @return The ComplexTaskBuilder instance.
     */
    public ComplexTaskBuilder subTask(Task subTask) {
        this.subTasks.add(subTask);
        return this;
    }

    /**
     * Builds and returns the ComplexTask object.
     *
     * @return The ComplexTask object built using the provided parameters.
     */
    public ComplexTask build() {
        if (parentTask != null) {
            return new ComplexTask(description, priority, parentTask);
        } else {
            return new ComplexTask(description, priority, subTasks);
        }
    }
}
