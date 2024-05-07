package com.univ.rouen.todolist.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ChildTask {

    /**
     * The parent of the Task
     */
    protected Task parentTask;
    /**
     * Updates the parent of the model.
     */
    public void updateParents() {
        if (parentTask != null)
            parentTask.updateParents();
    }

}
