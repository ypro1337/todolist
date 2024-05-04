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
@XmlRootElement(name = "booleanTask")
public class BooleanTask extends AbstractTask implements ChildTask{

    @XmlAttribute
    private Boolean completed;
    private  ParentTask parentTask;

    @Override
    public Integer getEstimatedDuration() {
        return this.estimatedDuration;
    }
    @Override
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * @return Completion state
     */
    @Override
    public Boolean isCompleted() {
        return this.completed;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public Priority getPriority() {
        return this.priority;
    }

    public BooleanTask(){}
    public BooleanTask(String description, Priority priority, LocalDate deadline, Integer estimatedDuration, Boolean completed) {
        this.description = description;
        this.priority = priority;
        this.dueDate = deadline;
        this.estimatedDuration = estimatedDuration;
        this.completed = completed;
    }

    /**
     * @param completed sets the completion state
     */
    @Override
    public void setCompleted(Boolean completed) {
        this.completed=completed;
    }
    @Override
    public Double getProgress() {
        return completed ? 100.0 : 0 ;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * @param parentTask task to update
     */
    @Override
    public void updateParent(ParentTask parentTask) {
        parentTask.updateDueDate();
        parentTask.updateProgress();
        parentTask.updateEstimatedDuration();
    }
}


