module lk.ijse.project.mentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires net.sf.jasperreports.core;
    requires java.mail;

    opens lk.ijse.project.mentalhealththerapycenter to javafx.fxml;
    exports lk.ijse.project.mentalhealththerapycenter;
    exports lk.ijse.project.mentalhealththerapycenter.controller;
    opens lk.ijse.project.mentalhealththerapycenter.controller to javafx.fxml;
    /*tm eka javafx base ekata open krnna*/
}