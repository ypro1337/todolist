package com.univ.rouen.todolist.task;

import java.time.LocalDate;

/**
 * An interface representing a task that allows setting various properties.
 */
public interface SetterTask {

    /**
     * Sets the description of the task.
     *
     * @param description The description to set.
     */
    void setDescription(String description);

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority to set.
     */
    void setPriority(Priority priority);

    /**
     * Sets the completion state of the task.
     *
     * @param completed The completion state to set.
     */
    void setCompleted(Boolean completed);

    /**
     * Sets the due date of the task.
     *
     * @param dueDate The due date to set.
     */
    void setDueDate(LocalDate dueDate);

    /**
     * Sets the estimated duration of the task.
     *
     * @param estimatedDuration The estimated duration to set.
     */
    void setEstimatedDuration(Integer estimatedDuration);
}
