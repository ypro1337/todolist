package com.univ.rouen.todolist.model;

import com.univ.rouen.todolist.dataSource.LocalDateAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Abstract class representing a model in the to-do list application.
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.NONE)

public abstract class Task extends ChildTask {
    public Task() {

    }

    /**
     * The description of the model.
     */
    @XmlAttribute
    protected String description;

    /**
     * The due date of the model.
     */
    @XmlAttribute
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    protected LocalDate dueDate;

    /**
     * The priority of the model.
     */
    @XmlAttribute
    protected Priority priority;

    /**
     * The estimated duration of the model.
     */
    @XmlAttribute
    protected Integer estimatedDuration;

    /**
     * The completion state of the model
     */
    @XmlAttribute
    protected Boolean completed;



    /**
     * Returns the description of the model.
     *
     * @return The description of the model.
     */
    @XmlTransient
    public String getDescription() {
        return description;
    }

    /**
     * Returns the due date of the model.
     *
     * @return The due date of the model.
     */
    @XmlTransient
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns the completion state of the model.
     *
     * @return The completion state of the model.
     */
    @XmlTransient
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Returns the estimated duration of the model.
     *
     * @return The estimated duration of the model.
     */
    @XmlTransient
    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    /**
     * Returns the priority of the model.
     *
     * @return The priority of the model.
     */
    @XmlTransient
    public Priority getPriority() {
        return priority;
    }

    /**
     * Checks if the model is completed.
     *
     * @return True if the model is completed, false otherwise.
     */
    @XmlTransient
    public Boolean isCompleted(){
        return completed;
    }



    /**
     * Returns the string representation of the model.
     *
     * @return The string representation of the model.
     */
    @Override
    public String toString() {
        return this.description;
    }

}
