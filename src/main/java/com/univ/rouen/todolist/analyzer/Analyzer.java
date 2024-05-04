package com.univ.rouen.todolist.analyzer;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Analyzer {

    public static void main(String[] args) {


        String taskListFile = args[0];

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(taskListFile));

            NodeList taskNodes = document.getElementsByTagName("BooleanTask");

            // Sort uncompleted tasks by due date
            NodeList uncompletedTasks = sortUncompletedTasks(taskNodes);

            // Print the list of 5 uncompleted tasks with the lowest due dates
            int count = 0;
            for (int i = 0; i < uncompletedTasks.getLength() && count < 5; i++) {
                Element task = (Element) uncompletedTasks.item(i);
                String completed = task.getAttribute("completed");
                if (completed.equals("false")) {
                    count++;
                    System.out.println("Task " + count + ":");
                    System.out.println("Description: " + task.getAttribute("description"));
                    System.out.println("Due Date: " + task.getAttribute("dueDate"));
                    System.out.println("---------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static NodeList sortUncompletedTasks(NodeList taskNodes) {
        List<Element> uncompletedTasks = new ArrayList<>();

        // Filter uncompleted tasks
        for (int i = 0; i < taskNodes.getLength(); i++) {
            Element task = (Element) taskNodes.item(i);
            String completed = task.getAttribute("completed");
            if (completed.equals("false")) {
                uncompletedTasks.add(task);
            }
        }

        // Sort uncompleted tasks by due date
        Collections.sort(uncompletedTasks, new Comparator<Element>() {
            @Override
            public int compare(Element task1, Element task2) {
                String dueDate1 = task1.getAttribute("dueDate");
                String dueDate2 = task2.getAttribute("dueDate");
                return dueDate1.compareTo(dueDate2);
            }
        });

        return nodeListFromList(uncompletedTasks);
    }

    private static NodeList nodeListFromList(List<Element> list) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("Root");
            document.appendChild(root);
            for (Element element : list) {
                Node importedNode = document.importNode(element, true);
                root.appendChild(importedNode);
            }
            return root.getChildNodes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
