package com.univ.rouen.todolist.dataSource;

import com.univ.rouen.todolist.model.Task;

import java.util.List;

/**
 * Interface for data sources to read and write tasks.
 */
public interface DataSource {
    /**
     * Writes a list of tasks to a data source specified by the filename.
     *
     * @param tasks    The list of tasks to write.
     * @param filename The filename specifying the data source.
     */
    void write(List<Task> tasks, String filename);

    /**
     * Reads tasks from a data source specified by the filename.
     *
     * @param filename The filename specifying the data source.
     * @return The list of tasks read from the data source.
     */
    List<Task> read(String filename);
}
