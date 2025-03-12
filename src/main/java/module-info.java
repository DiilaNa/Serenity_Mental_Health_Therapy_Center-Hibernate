module lk.ijse.project.mentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens lk.ijse.project.mentalhealththerapycenter to javafx.fxml;
    exports lk.ijse.project.mentalhealththerapycenter;
}