package com.univ.rouen.todolist.task;

import java.time.LocalDate;

/**
 * Represents a task in the to-do list application.
 */
public interface Task {
    //void accept(TaskVisitor visitor);

    public String getDescription();
    public LocalDate getDueDate();
    public Boolean isCompleted();
    public Priority getPriority();
    public Integer getEstimatedDuration();
    public double getProgress();
}
