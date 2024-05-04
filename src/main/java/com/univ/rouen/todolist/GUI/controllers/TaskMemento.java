package com.univ.rouen.todolist.gui.controllers;

import com.univ.rouen.todolist.task.ChildTask;
import javafx.scene.control.TreeItem;
import lombok.Getter;

@Getter
public class TaskMemento {
    private final TreeItem<ChildTask> state;

    public TaskMemento(TreeItem<ChildTask> state) {
        this.state = state;
    }

}