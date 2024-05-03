package com.univ.rouen.todolist.task;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "complexTask")
public class ComplexTask extends AbstractTask implements ModifiableTask,ParentTask{
    @XmlElements({@XmlElement}) 
    private List<Task> subTasks;
    private  ParentTask parentTask;

    @XmlElement
    private double progress;

    public ComplexTask() {}

    public ComplexTask(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>();
        this.estimatedDuration = 0;
        this.progress = 0;
    }

    public ComplexTask(String description, Priority priority, List<Task> subTasks) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>(subTasks);
        updateEstimatedDuration();
        updateProgress();
    }

    public ComplexTask(String description, Priority priority,Task... subTasks) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>(Arrays.stream(subTasks).toList());
        updateEstimatedDuration();
        updateProgress();
    }
    // Getters and setters

    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * @return
     */
    @Override
    public Boolean isCompleted() {
        return this.progress == 100 ;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public Priority getPriority() {
        return priority;
    }
    @Override
    public double getProgress() {
        return progress;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * @param completed
     */
    @Override
    public void setCompleted(Boolean completed) {

    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    @Override
    public void setDueDate(LocalDate dueDate){
    this.dueDate=dueDate;
    }
    @Override
    public void setEstimatedDuration(Integer estimatedDuration){
        this.estimatedDuration=estimatedDuration;
    }

    //TODO implement update classes for parent
    @Override
    public void updateDueDate() {
        // Min LocalDate is today for todoList
        LocalDate maxDueDate = LocalDate.now();
        for (Task task : subTasks) {
            //Take the maximum of the two
            maxDueDate= maxDueDate.isBefore(task.getDueDate()) ? task.getDueDate() : maxDueDate   ;
        }
        this.dueDate = maxDueDate;

        if(parentTask!=null)
        {
            update(parentTask);
        }
    }
     @Override
     public void updateEstimatedDuration() {

        Integer totalDuration = 0;
        for (Task task : subTasks) {
            totalDuration += task.getEstimatedDuration();
        }
        this.estimatedDuration = totalDuration;
         if(parentTask!=null)
         {
             update(parentTask);
         }
    }
    @Override
    public void updateProgress() {
        double totalProgress = 0;
        double subTaskCount = subTasks.size();
        if (subTaskCount > 0) {
            for (Task task : subTasks) {
                totalProgress += task.isCompleted() ? 100 : 0;
            }
            this.progress = totalProgress / subTaskCount;
        } else {
            this.progress = 0;
        }
        if(parentTask!=null)
        {
            update(parentTask);
        }
    }


    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * @param visitor
     */
    /*@Override
    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }*/

    /**
     * @return
     */
    @Override
    public List<Task> getChildren() {
        return this.subTasks;
    }

    /**
     * @return
     */
    @Override
    public ComplexTask getTask() {
        return this;
    }

    /**
     * @param parentTask
     */
    @Override
    public void update(ParentTask parentTask) {
        parentTask.updateDueDate();
        parentTask.updateProgress();
        parentTask.updateEstimatedDuration();
    }
}
