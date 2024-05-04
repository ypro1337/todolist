package com.univ.rouen.todolist.task;

import java.time.LocalDate;

/**
 * Represents a task in the to-do list application.
 */
public interface Task {
     String getDescription();
     LocalDate getDueDate();
     Boolean isCompleted();
     Priority getPriority();
     Integer getEstimatedDuration();
     Double getProgress();
}
