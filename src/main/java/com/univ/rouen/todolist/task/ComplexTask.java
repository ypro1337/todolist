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
public class ComplexTask extends AbstractTask implements ChildTask,ParentTask{
    @XmlElements({@XmlElement}) 
    private List<ChildTask> subTasks;
    private  ParentTask parentTask;

    @XmlElement
    private Double progress;

    public ComplexTask() {}

    public ComplexTask(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>();
        updateEstimatedDuration();
        updateProgress();
    }

    public ComplexTask(String description, Priority priority, List<ChildTask> subTasks) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>(subTasks);
        updateEstimatedDuration();
        updateProgress();
    }

    public ComplexTask(String description, Priority priority,ChildTask... subTasks) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>(Arrays.stream(subTasks).toList());
        updateEstimatedDuration();
        updateProgress();
    }


    /**
     * @return
     */
    @Override
    public Boolean isCompleted() {
        // TODO check if all subTaks Complete
        return false;
    }

    /**
     * @param completed
     */
    @Override
    public void setCompleted(Boolean completed) {
        //TODO make all SubTasks Complete
    }

    //TODO implement update classes for parent
    @Override
    public void updateDueDate() {
        // Min LocalDate is today for todoList
        LocalDate maxDueDate = LocalDate.now();
        for (ChildTask task : subTasks) {
            //Take the maximum of the two
            maxDueDate= maxDueDate.isBefore(task.getDueDate()) ? task.getDueDate() : maxDueDate   ;
        }
        this.dueDate = maxDueDate;

        if(parentTask!=null)
        {
            updateParent(parentTask);
        }
    }
     @Override
     public void updateEstimatedDuration() {
        Integer totalDuration = 0;
        for (ChildTask task : subTasks) {
            totalDuration += task.getEstimatedDuration();
        }
        this.estimatedDuration = totalDuration;
         if(parentTask!=null)
         {
             updateParent(parentTask);
         }
    }
    @Override
    public void updateProgress() {
        Double totalProgress = 0.0;
        double subTaskCount = subTasks.size();
        if (subTaskCount > 0) {
            for (ChildTask task : subTasks) {

                totalProgress += task.isCompleted() ? 100.0 : 0.0;
            }
            this.progress = totalProgress / subTaskCount;
        } else {
            this.progress = 0.0;
        }
        if(parentTask!=null)
        {
            updateParent(parentTask);

        }
    }


    @Override
    public String toString() {
        return getDescription();
    }


    /**
     * @return
     */
    @Override
    public List<ChildTask> getChildren() {
        return this.subTasks;
    }


    /**
     * @param parentTask
     */
    @Override
    public void updateParent(ParentTask parentTask) {
        parentTask.updateDueDate();
        parentTask.updateProgress();
        parentTask.updateEstimatedDuration();
    }
}
