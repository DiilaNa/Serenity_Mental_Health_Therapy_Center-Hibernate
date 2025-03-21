package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.BOType;

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
    private ComboBox<String> patientGender;

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
    private ComboBox<String> paymentMethod;

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

    AppointmentBO appointmentBO = BOFactory.getInstance().getBO(BOType.APPOINTMENT);

    @FXML
    void addAppointmentBTNAction(ActionEvent event) {
        String patientId = patientID.getText();
        String patientNAME = patientName.getText();
        String birthDate = patientDOB.getText();
        String patientNic = patientNIC.getText();
        String patientGENDER = patientGender.getSelectionModel().getSelectedItem();
        String patientADDRESS = patientAddress.getText();
        String patientPHONE = patientTelNO.getText();
        String patientEmail = patientEMAIL.getText();

        String sessionId = sessionID.getText();
        String sessionTIME =  sessionTime.getText();
        String sessionNOTES = sessionNotes.getText();
        String sessionDATE = sessionDate.getEditor().getText();

        String paymentId = paymentID.getText();
        Double payAmount = Double.parseDouble(payAMOUNT.getText());
        String paymentMETHOD = paymentMethod.getSelectionModel().getSelectedItem();
        String paymentDate = LocalDate.now().format(formatter);
        String paymentTime = LocalTime.now().format(timeFormatter);

        String listDoctors = doctorListView.getSelectionModel().getSelectedItem().toString();
        String listPrograms = programmsListView.getSelectionModel().getSelectedItem().toString();

        String namePattern = "^[a-zA-Z ]+$";
        String addressPattern = "^[a-zA-Z0-9, -]+$";
        String mailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String PhoneNoPattern = "^\\+?[1-9]\\d{0,2}[-.\\s]?\\d{1,4}[-.\\s]?\\d{3,4}[-.\\s]?\\d{3,4}$";
        String datePattern = "^(0[1-9]|[12]\\d|3[01])[-/.](0[1-9]|1[0-2])[-/.](19|20)\\d\\d$";
        String timePattern = "^(?:[01]\\d|2[0-3]):[0-5]\\d(:[0-5]\\d)?$";

        boolean isValidName = patientNAME.matches(namePattern);
        boolean isValidAddress = patientADDRESS.matches(addressPattern);
        boolean isValidMail = patientEmail.matches(mailPattern);
        boolean isValidPhoneNO = patientPHONE.matches(PhoneNoPattern);
        boolean isValidDate = birthDate.matches(datePattern);
        boolean isValidTime = sessionTIME.matches(timePattern);

        if (!isValidName) {
            patientName.setStyle(patientName.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidAddress) {
            patientAddress.setStyle(patientAddress.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidMail) {
            patientEMAIL.setStyle(patientEMAIL.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhoneNO) {
            patientTelNO.setStyle(patientTelNO.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidDate) {
            patientDOB.setStyle(patientDOB.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidTime) {
            sessionTime.setStyle(sessionTime.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName && isValidAddress && isValidMail && isValidPhoneNO && isValidDate && isValidTime) {
            boolean isSaved = appointmentBO.addAppointment();
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment added", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed! Appointment not added", ButtonType.OK).show();
            }
        }
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
