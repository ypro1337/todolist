package com.univ.rouen.todolist.dataSource;

import com.univ.rouen.todolist.model.Task;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Wrapper class for marshalling and unmarshalling a list of tasks to and from XML.
 */
@XmlRootElement(name = "TodoList")
@XmlAccessorType(XmlAccessType.FIELD)
public class TodoListWrapper {

    @XmlElements({@XmlElement(name = "booleanTask"),@XmlElement(name = "complexTask")})
    private List<Task> tasks;

    /**
     * Default constructor.
     */
    public TodoListWrapper() {
    }

    /**
     * Parameterized constructor.
     *
     * @param tasks The list of tasks to be wrapped.
     */
    public TodoListWrapper(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
