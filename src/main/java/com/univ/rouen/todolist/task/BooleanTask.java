package com.univ.rouen.todolist.task;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Represents a boolean task in the to-do list application.
 */
@Getter
@Setter
@XmlRootElement(name = "BooleanTask")
public class BooleanTask extends AbstractTask implements ChildTask{

    @XmlAttribute
    private Boolean completed;
    private  ParentTask parentTask;


    /**
     * Constructs a new BooleanTask object with default values.
     */
    public BooleanTask(){}

    /**
     * Constructs a new BooleanTask object with the specified parameters.
     *
     * @param description      The description of the task.
     * @param priority         The priority of the task.
     * @param dueDate          The due date of the task.
     * @param estimatedDuration The estimated duration of the task.
     * @param completed        The completion state of the task.
     */
    public BooleanTask(String description, Priority priority, LocalDate dueDate, Integer estimatedDuration, Boolean completed) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.estimatedDuration = estimatedDuration;
        this.completed = completed;
    }

    /**
     * Calculates the progress of the task.
     *
     * @return The progress of the task.
     */
    @Override
    public Double getProgress() {
        return completed ? 100.0 : 0 ;
    }

    /**
     * @return Completion state
     */
    @Override
    public Boolean isCompleted() {
        return this.completed;
    }

    /**
     * Updates the parent task (the changeable Values)
     *
     * @param parentTask The task to update.
     */
    @Override
    public void updateParent(ParentTask parentTask) {
        parentTask.updateDueDate();
        parentTask.updateProgress();
        parentTask.updateEstimatedDuration();
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return getDescription();
    }

}


