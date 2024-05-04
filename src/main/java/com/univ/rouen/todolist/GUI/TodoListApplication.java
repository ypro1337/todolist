package com.univ.rouen.todolist.gui;

import com.univ.rouen.todolist.analyzer.Analyzer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TodoListApplication extends Application {

    @Override
    public void init () throws Exception
    {
        super.init ();

        Parameters parameters = getParameters ();

        Map<String, String> namedParameters = parameters.getNamed ();
        List<String> rawArguments = parameters.getRaw ();
        List<String> unnamedParameters = parameters.getUnnamed ();

        System.out.println ("\nnamedParameters -");
        for (Map.Entry<String, String> entry : namedParameters.entrySet ())
            System.out.println (entry.getKey () + " : " + entry.getValue ());

        System.out.println ("\nrawArguments -");
        for (String raw : rawArguments)
            System.out.println (raw);

        System.out.println ("\nunnamedParameters -");
        for (String unnamed : unnamedParameters)
            System.out.println (unnamed);
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TodoListApplication.class.getResource("/com/univ/rouen/todolist/views/TaskLanding.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 670, 800);
        stage.setTitle("Todo List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println(args.length);
        //Analyzer.main(args);
        launch();
    }
}