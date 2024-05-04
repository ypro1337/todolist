package com.univ.rouen.todolist.task;

public interface TaskVisitor {
    void visit(BooleanTask task);
    void visit(ComplexTask task);
}
