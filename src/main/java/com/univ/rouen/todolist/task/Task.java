package com.univ.rouen.todolist.task;

import java.time.LocalDate;

/**
 * An interface representing a task in the to-do list application.
 */
public interface Task {

     /**
      * Gets the description of the task.
      *
      * @return The description of the task.
      */
     String getDescription();

     /**
      * Gets the due date of the task.
      *
      * @return The due date of the task.
      */
     LocalDate getDueDate();

     /**
      * Checks if the task is completed.
      *
      * @return True if the task is completed, otherwise false.
      */
     Boolean isCompleted();

     /**
      * Gets the priority of the task.
      *
      * @return The priority of the task.
      */
     Priority getPriority();

     /**
      * Gets the estimated duration of the task.
      *
      * @return The estimated duration of the task.
      */
     Integer getEstimatedDuration();

     /**
      * Gets the progress of the task.
      *
      * @return The progress of the task.
      */
     Double getProgress();
}
