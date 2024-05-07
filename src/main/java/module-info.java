module com.univ.rouen.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.xml.bind;
    requires java.activation;
    requires lombok;


    opens com.univ.rouen.todolist.controllers to javafx.fxml;
    opens com.univ.rouen.todolist.model;
    exports com.univ.rouen.todolist.controllers;
    exports com.univ.rouen.todolist.dataSource;
    exports com.univ.rouen.todolist.model;

    opens com.univ.rouen.todolist.dataSource to java.xml.bind;
    exports com.univ.rouen.todolist;
    opens com.univ.rouen.todolist to javafx.fxml;

}