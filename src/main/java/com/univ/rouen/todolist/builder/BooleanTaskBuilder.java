package com.univ.rouen.todolist.builder;

import com.univ.rouen.todolist.task.BooleanTask;
import com.univ.rouen.todolist.task.Priority;

import java.time.LocalDate;

public class BooleanTaskBuilder {
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private Integer estimatedDuration;
    private Boolean completed;

    public BooleanTaskBuilder(String description) {
        this.description = description;
    }

    public BooleanTaskBuilder withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public BooleanTaskBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
    public BooleanTaskBuilder withEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
        return this;
    }
    public BooleanTaskBuilder withCompletion(Boolean completion) {
        this.completed = completion;
        return this;
    }

    public BooleanTask build() {
        // Validate required fields
        if (description == null || description.isEmpty()) {
            throw new IllegalStateException("Description cannot be null or empty");
        }

        // Set default priority if not provided
        if (priority == null) {
            priority = Priority.NORMAL;
        }
        // Set default due date if not provided
        if (dueDate == null) {
            dueDate = LocalDate.now();
        }
        // Set default due date if not provided
        if (estimatedDuration == null) {
            estimatedDuration = 1;
        }
        // Set default due date if not provided
        if (completed == null) {
            completed = false;
        }


        // Build BooleanTask instance
        return new BooleanTask(description, priority,dueDate,estimatedDuration,completed);
    }
}
