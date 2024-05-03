package com.univ.rouen.todolist.task;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import java.time.LocalDate;

/**
 *
 * Class That Implement The Template Method Pattern
 */
@Getter
@Setter
public abstract class AbstractTask implements ChildTask {
    @XmlAttribute
    protected String description;
    @XmlAttribute
    protected LocalDate dueDate;
    @XmlAttribute
    protected Priority priority;
    @XmlAttribute
    protected Integer estimatedDuration;


}
