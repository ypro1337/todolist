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

/**
 * Represents a complex task in the to-do list application.
 */
@Getter
@Setter
@XmlRootElement(name = "complexTask")
public class ComplexTask extends AbstractTask implements ChildTask, ParentTask {

    @XmlElements({@XmlElement})
    private List<ChildTask> subTasks;
    private ParentTask parentTask;

    @XmlElement
    private Double progress;

    /**
     * Default constructor for ComplexTask.
     */
    public ComplexTask() {}

    /**
     * Constructs a ComplexTask with the given description and priority.
     *
     * @param description The description of the task.
     * @param priority    The priority of the task.
     */
    public ComplexTask(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>();
        updateEstimatedDuration();
        updateProgress();
    }

    /**
     * Constructs a ComplexTask with the given description, priority, and subtasks.
     *
     * @param description The description of the task.
     * @param priority    The priority of the task.
     * @param subTasks    The list of subtasks.
     */
    public ComplexTask(String description, Priority priority, List<ChildTask> subTasks) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>(subTasks);
        updateEstimatedDuration();
        updateProgress();
    }

    /**
     * Constructs a ComplexTask with the given description, priority, and subtasks.
     *
     * @param description The description of the task.
     * @param priority    The priority of the task.
     * @param subTasks    The subtasks.
     */
    public ComplexTask(String description, Priority priority, ChildTask... subTasks) {
        this.description = description;
        this.priority = priority;
        this.subTasks = new ArrayList<>(Arrays.asList(subTasks));
        updateEstimatedDuration();
        updateProgress();
    }

    /**
     * Checks if all subtasks are completed.
     *
     * @return True if all subtasks are completed, otherwise false.
     */
    @Override
    public Boolean isCompleted() {
        return subTasks.stream().allMatch(ChildTask::isCompleted);
    }

    /**
     * Sets the completion state of all subtasks.
     *
     * @param completed The completion state to set for all subtasks.
     */
    @Override
    public void setCompleted(Boolean completed) {
        subTasks.forEach(subTask -> subTask.setCompleted(completed));
    }

    // TODO: Implement update methods for parent tasks

    /**
     * Updates the due date of the complex task based on its subtasks.
     */
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

    /**
     * Updates the estimated duration of the complex task based on its subtasks.
     */
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

    /**
     * Updates the progress of the complex task based on its subtasks.
     */
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

    /**
     * Returns the string representation of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return getDescription();
    }

    /**
     * Returns the list of child tasks.
     *
     * @return The list of child tasks.
     */
    @Override
    public List<ChildTask> getChildren() {
        return this.subTasks;
    }

    /**
     * Updates the parent task.
     *
     * @param parentTask The parent task to update.
     */
    @Override
    public void updateParent(ParentTask parentTask) {
        parentTask.updateDueDate();
        parentTask.updateProgress();
        parentTask.updateEstimatedDuration();
    }

    public void addSubTask(ChildTask childTask) {
        this.subTasks.add(childTask);
    }
}

