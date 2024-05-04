package com.univ.rouen.todolist.builder;

import com.univ.rouen.todolist.task.ChildTask;
import com.univ.rouen.todolist.task.ComplexTask;
import com.univ.rouen.todolist.task.Priority;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComplexTaskBuilder {
    private final String description;
    private LocalDate dueDate;
    private Priority priority;
    private final List<ChildTask> subTasks = new ArrayList<>();

    public ComplexTaskBuilder( String description) {
        this.description = description;
    }

    public ComplexTaskBuilder withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public ComplexTaskBuilder withSubTask(ChildTask subTask) {
        this.subTasks.add(subTask);
        return this;
    }

    public ComplexTask build() {
        // Validate required fields
        if (description == null || description.isEmpty()) {
            throw new IllegalStateException("Description cannot be null or empty");
        }

        // Set default due date if not provided
        if (dueDate == null) {
            dueDate = LocalDate.now();
        }

        // Set default priority if not provided
        if (priority == null) {
            priority = Priority.NORMAL;
        }

        // Build ComplexTask instance
        ComplexTask complexTask = new ComplexTask(description, priority);
        subTasks.forEach(complexTask::addSubTask);
        return complexTask;
    }
}

