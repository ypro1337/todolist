package com.univ.rouen.todolist.task;

import java.util.List;

public interface ParentTask {
     List<Task> getChildren();
     ComplexTask getTask();
     void updateProgress();
     void updateEstimatedDuration();
      void updateDueDate();
}
