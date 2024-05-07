package com.univ.rouen.todolist.analyzer;

import com.univ.rouen.todolist.dataSource.TodoListWrapper;
import com.univ.rouen.todolist.model.BooleanTask;
import com.univ.rouen.todolist.model.ComplexTask;
import com.univ.rouen.todolist.model.Task;
import javax.xml.bind.*;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.LinkedTransferQueue;

public class TaskAnalyzer {

    private static List<Task> listTasks;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java TaskAnalyzer <inputXmlFilePath>");
            return;
        }

        String inputXmlFilePath = args[0];

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TodoListWrapper.class, ComplexTask.class, BooleanTask.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File(inputXmlFilePath);

            TodoListWrapper todoListWrapper = (TodoListWrapper) unmarshaller.unmarshal(xmlFile);

            List<Task> tasks = todoListWrapper.getTasks();
            listTasks = new ArrayList<>();
            for (Task task : tasks){
                listTasks.add(task);
                parcourTask(task);
            }

            Collections.sort(listTasks, Comparator.comparing(Task::getDueDate));
            int count = 0;
            for (Task task : listTasks) {
                if (count >= 5) break;
                System.out.println("Task " + (count + 1) + ":");
                System.out.println("Description: " + task.getDescription());
                System.out.println("Due Date: " + task.getDueDate());
                if(task.getEstimatedDuration() != null)
                    System.out.println("getEstimatedDuration: " + task.getDueDate());
                if(task.isCompleted() != null)
                    System.out.println("Due Date: " + task.getDueDate());
                System.out.println("---------------------");
                count++;
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static void parcourTask(Task task) {
        if (task instanceof ComplexTask) {
            List<Task> subTasks = ((ComplexTask) task).getSubTasks();
            for (Task subTask : subTasks) {
                listTasks.add(subTask);
                if (subTask instanceof ComplexTask) {
                    parcourTask(subTask);
                    //tasks.add((ComplexTask) subTask); ParentTask
                }
            }
        }
    }
}
