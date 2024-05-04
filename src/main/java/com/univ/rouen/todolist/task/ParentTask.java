package com.univ.rouen.todolist.task;

import java.util.List;

/**
 * An interface representing a parent task in the to-do list application.
 * This interface extends the Task interface.
 */
public interface ParentTask extends Task {

    /**
     * Gets the list of child tasks associated with this parent task.
     *
     * @return The list of child tasks.
     */
    List<ChildTask> getChildren();

    /**
     * Updates the progress of the parent task based on its child tasks.
     */
    void updateProgress();

    /**
     * Updates the estimated duration of the parent task based on its child tasks.
     */
    void updateEstimatedDuration();

    /**
     * Updates the due date of the parent task based on its child tasks.
     */
    void updateDueDate();
}