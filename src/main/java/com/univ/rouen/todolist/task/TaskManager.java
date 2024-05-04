package com.univ.rouen.todolist.task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<ParentTask> parentTasks;

    public TaskManager() {
        this.parentTasks = new ArrayList<>();
    }

    public void addParentTask(ParentTask parentTask) {
        parentTasks.add(parentTask);
    }

}