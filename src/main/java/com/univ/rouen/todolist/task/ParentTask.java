package com.univ.rouen.todolist.task;

import java.util.List;

public interface ParentTask extends Task {
     List<ChildTask> getChildren();

     void updateProgress();
     void updateEstimatedDuration();
      void updateDueDate();
}
