package com.univ.rouen.todolist.task;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import java.time.LocalDate;

/**
 * Abstract class representing a task in the to-do list application.
 */
@Getter
@Setter
public abstract class AbstractTask implements ChildTask {

    /**
     * The description of the task.
     */
    @XmlAttribute
    protected String description;

    /**
     * The due date of the task.
     */
    @XmlAttribute
    protected LocalDate dueDate;

    /**
     * The priority of the task.
     */
    @XmlAttribute
    protected Priority priority;

    /**
     * The estimated duration of the task.
     */
    @XmlAttribute
    protected Integer estimatedDuration;
}
