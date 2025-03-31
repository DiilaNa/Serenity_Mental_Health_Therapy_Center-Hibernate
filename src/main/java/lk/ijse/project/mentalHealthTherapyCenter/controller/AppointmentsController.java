package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.*;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AppointmentsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/appointmentIcon.png"));
        image.setImage(image1);
        updateDateTime();
        try{
            refreshPage();
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load the Page",ButtonType.CLOSE).show();
        }
    }


    @FXML
    private Button addPrograms;

    @FXML
    private ListView<String> programmsListView;

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

    @FXML
    private Label docLoadLabel;

    private String ProgramID;

    private  String DocID;

    private String availability;

    public void setDetails(String programID, String programName) {
         ProgramID = programID;
        if (programID != null && programName != null) {
            programmsListView.getItems().add(programID + " - " + programName);
        }
    }
    public void setAddDoctors(String docID, String docName,String availability) {
        DocID = docID;
        if (docID != null && docName != null) {
            docLoadLabel.setText(docID + " - " + docName + " - " + availability);
        }
    }

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
        String doctorIDFromLabel = docLoadLabel.getText(); /*get the full text in to a label called doctorIDFromLabel */
        String docID = null; /*this id is pass through sessionDTO*/

        String[] parts = doctorIDFromLabel.split(" - ");
        if (parts.length > 0) {
            docID = parts[0];  // First part is docID , get id from the full label
            System.out.println("Extracted Doc ID: " + docID);
        }

        String paymentId = paymentID.getText();
        Double payAmount = Double.valueOf(payAMOUNT.getText());
        String paymentMETHOD = paymentMethod.getSelectionModel().getSelectedItem();
        String paymentDate = LocalDate.now().format(formatter);
        String paymentTime = LocalTime.now().format(timeFormatter);

        // Enable multiple selection
        programmsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Handle mouse click event to print selected items
        ObservableList<String> selectedPrograms = programmsListView.getSelectionModel().getSelectedItems();
        System.out.println("Selected Programs: " + selectedPrograms);  // Print selected items
        Set<String> programIDs = new HashSet<>();
            // Now handle the program IDs extraction
            for (String program : selectedPrograms) {
                if (program.contains(" - ")) {
                    String programID = program.split(" - ")[0];  // Extract programID
                    programIDs.add(programID);  // Add to the list of program IDs
                } else {
                    System.out.println("Error: Invalid format for program item! " + program);
                }
            }

        System.out.println("Extracted Programs: " + programIDs);

     /*   String namePattern = "^[a-zA-Z ]+$";
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
            System.out.println("Invalid Phone Number");
        }
        if (!isValidDate) {
            patientDOB.setStyle(patientDOB.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidTime) {
            sessionTime.setStyle(sessionTime.getStyle() + ";-fx-border-color: red;");
        }
*/
        /*if (*//*isValidName && isValidAddress && isValidMail && isValidPhoneNO && isValidDate && isValidTime*//*) {*/
            PatientDTO patientDTO = new PatientDTO(
                patientId,
                patientNAME,
                birthDate,
                patientNic,
                patientGENDER,
                patientADDRESS,
                patientPHONE,
                patientEmail
            );
            ProgramDetailsDTO programDetailsDTO = new ProgramDetailsDTO(
                patientId,
                new ArrayList<>(programIDs)  /*List required as one patient can choose more than one programs*/

            );
        System.out.println(programDetailsDTO.getProgramId());
            SessionDTO sessionDTO = new SessionDTO(
                    sessionId,
                    patientId,
                    paymentId,
                    docID, /*don't need a list here,took from label splitting the first part*/
                    sessionTIME,
                    sessionNOTES,
                    sessionDATE
            );
            PaymentDTO paymentDTO = new PaymentDTO(
                    paymentId,
                    patientNAME,
                    payAmount,
                    paymentMETHOD,
                    paymentDate,
                    paymentTime
            );
            boolean isSaved = appointmentBO.addAppointment(patientDTO, programDetailsDTO,sessionDTO,paymentDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Appointment added", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed! Appointment not added", ButtonType.OK).show();
            }
      /*  }*/
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
        refreshPage();
    }

    @FXML
    void addProgramsAction(MouseEvent event) throws IOException {
       loadNewPage("/view/SelectPrograms.fxml");
    }

    private  void  loadNewPage(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load(); // Load the FXML

        // Now get the controller from the FXMLLoader
        if (fxmlPath.equals("/view/assignDocs.fxml")) {
            AssignDoctorsController assignDoctorsController = loader.getController();
            // Inject data or set additional properties for AssignDoctorsController
            assignDoctorsController.setAppointmentsController(this);
        } else if (fxmlPath.equals("/view/SelectPrograms.fxml")) {
            SelectProgramsController selectProgramsController = loader.getController();
            // Inject data or set additional properties for SelectProgramsController
            selectProgramsController.setAppointmentsController(this);
        }

        Scene scene = new Scene(root);
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
    private void refreshPage(){
        generateNextAppointmentID();
        generateNextPatientID();
        generateNextPaymentID();
        patientGender.setItems(FXCollections.observableArrayList("Male", "Female"));
        paymentMethod.setItems(FXCollections.observableArrayList("Card Payment", "Cash Payment"));
        patientName.clear();
        patientAddress.clear();
        patientEMAIL.clear();
        patientTelNO.clear();
        patientDOB.clear();
        sessionTime.clear();
        sessionNotes.clear();
        payAMOUNT.clear();
        docLoadLabel.setDisable(true);
        programmsListView.refresh();

    }
    private void generateNextAppointmentID() {
        String nextAptID =appointmentBO.getNextSessionID();
        sessionID.setText(nextAptID);
    }
    private void generateNextPatientID() {
        String nextPatientId = appointmentBO.getNextPatientID();
        patientID.setText(nextPatientId);
    }
    private void generateNextPaymentID() {
        String nextPaymentID = appointmentBO.getNextPaymentID();
        paymentID.setText(nextPaymentID);
    }
}
