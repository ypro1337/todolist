package com.univ.rouen.todolist.task;

/**
 * Interface representing a child task in the to-do list application.
 */
public interface ChildTask extends ModifiableTask {

    /**
     * Updates the parent task.
     *
     * @param parentTask The parent task to update.
     */
    void updateParent(ParentTask parentTask);
}