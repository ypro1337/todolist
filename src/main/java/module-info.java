module com.univ.rouen.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.xml.bind;
    requires java.activation;
    requires lombok;
    opens com.univ.rouen.todolist.gui.controllers to javafx.fxml;
    opens com.univ.rouen.todolist.task to java.xml.bind;
    exports com.univ.rouen.todolist.gui;
    exports com.univ.rouen.todolist.gui.controllers;
    opens com.univ.rouen.todolist.gui to javafx.fxml;
    opens com.univ.rouen.todolist.builder to java.xml.bind;

}