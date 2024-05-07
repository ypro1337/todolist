package com.univ.rouen.todolist.dataSource;

import com.univ.rouen.todolist.model.BooleanTask;
import com.univ.rouen.todolist.model.ComplexTask;
import com.univ.rouen.todolist.model.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Implementation of the DataSource interface for reading and writing tasks to a file using JAXB.
 */
public class FileDataSource implements DataSource {

    private static final String DTD_FILE_PATH = "/com/univ/rouen/todolist/views/todolist.dtd";

    /**
     * Writes a list of tasks to an XML file using JAXB.
     *
     * @param tasks    The list of tasks to write.
     * @param fileName The filename specifying the location to write the tasks.
     */
    @Override
    public void write(List<Task> tasks, String fileName) {
        try {
            TodoListWrapper todoListWrapper = new TodoListWrapper(tasks);

            JAXBContext context = JAXBContext.newInstance(TodoListWrapper.class, ComplexTask.class, BooleanTask.class);
            Marshaller marshaller = context.createMarshaller();

            // Set properties for pretty printing
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, DTD_FILE_PATH);

            // Marshal the TodoListWrapper
            marshaller.marshal(todoListWrapper, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from an XML file using JAXB.
     *
     * @param fileName The filename specifying the location to read the tasks from.
     * @return The list of tasks read from the XML file.
     */
    @Override
    public List<Task> read(String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TodoListWrapper.class, ComplexTask.class, BooleanTask.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File(fileName);

            TodoListWrapper todoListWrapper = (TodoListWrapper) unmarshaller.unmarshal(xmlFile);
            return todoListWrapper.getTasks();

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }


}
