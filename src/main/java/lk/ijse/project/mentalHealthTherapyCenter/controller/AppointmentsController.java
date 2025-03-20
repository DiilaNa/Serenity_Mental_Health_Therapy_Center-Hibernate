package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/appointmentIcon.png"));
        image.setImage(image1);
        updateDateTime();

    }


    @FXML
    private Button addPrograms;

    @FXML
    private ListView<?> programmsListView;

    @FXML
    private Button addAppointmentBTN;

    @FXML
    private Button addDoctors;

    @FXML
    private AnchorPane appointmentPage;

    @FXML
    private Button cancelAppointmentBTN;

    @FXML
    private Label date;

    @FXML
    private ListView<?> doctorListView;

    @FXML
    private ImageView image;

    @FXML
    private TextField patientAddress;

    @FXML
    private TextField patientDOB;

    @FXML
    private TextField patientEMAIL;

    @FXML
    private ComboBox<?> patientGender;

    @FXML
    private Label patientID;

    @FXML
    private TextField patientNIC;

    @FXML
    private TextField patientName;

    @FXML
    private TextField patientTelNO;

    @FXML
    private TextField payAMOUNT;

    @FXML
    private Label paymentID;

    @FXML
    private ComboBox<?> paymentMethod;

    @FXML
    private Button reset;

    @FXML
    private DatePicker sessionDate;

    @FXML
    private Label sessionID;

    @FXML
    private TextField sessionNotes;

    @FXML
    private TextField sessionTime;

    @FXML
    private Label time;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-");

    @FXML
    void addAppointmentBTNAction(ActionEvent event) {
        String paymentDate = LocalDate.now().format(formatter);
        String paymentTime = LocalTime.now().format(timeFormatter);
    }

    @FXML
    void addDoctorsAction(MouseEvent event) throws IOException {
        loadNewPage("/view/assignDocs.fxml");
    }

    @FXML
    void cancelAppointmentBTNAction(ActionEvent event) {

    }

    @FXML
    void resetAction(ActionEvent event) {

    }

    @FXML
    void addProgramsAction(MouseEvent event) throws IOException {
        loadNewPage("/view/SelectPrograms.fxml");
    }

    private  void  loadNewPage(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Doctor Details - Serenity Mental Health Therapy Center");
        stage.show();
    }
    private void updateDateTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    String currentTime = LocalTime.now().format(timeFormatter);
                    time.setText(currentTime);
                    String currentDate = LocalDate.now().format(formatter);
                    date.setText(currentDate);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
