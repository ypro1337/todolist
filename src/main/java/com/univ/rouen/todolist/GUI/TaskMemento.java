package com.univ.rouen.todolist.GUI;

import com.univ.rouen.todolist.task.Task;
import javafx.scene.control.TreeItem;

public class TaskMemento {
    private final TreeItem<Task> state;

    public TaskMemento(TreeItem<Task> state) {
        this.state = state;
    }

    public TreeItem<Task> getState() {
        return state;
    }
}