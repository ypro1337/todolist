package com.univ.rouen.todolist.task;

public interface ChildTask extends ModifiableTask {
    void updateParent(ParentTask parentTask);
}
