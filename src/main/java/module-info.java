module lk.ijse.project.mentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.project.mentalhealththerapycenter to javafx.fxml;
    exports lk.ijse.project.mentalhealththerapycenter;
}