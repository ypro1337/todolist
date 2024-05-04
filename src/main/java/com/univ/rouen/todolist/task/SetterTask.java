package com.univ.rouen.todolist.task;

import java.time.LocalDate;

public interface SetterTask {
    void setDescription(String description);
    void setPriority(Priority priority);
    void setCompleted(Boolean completed);
    void setDueDate(LocalDate dueDate);
    void setEstimatedDuration(Integer estimatedDuration);
}
