package com.univ.rouen.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for the Todo List application.
 */
public class TodoListApplication extends Application {

    /**
     * Starts the JavaFX application.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TodoListApplication.class.getResource("/com/univ/rouen/todolist/views/TaskLanding.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Todo List");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method, which launches the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}