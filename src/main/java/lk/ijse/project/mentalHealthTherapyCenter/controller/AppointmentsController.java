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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.project.mentalHealthTherapyCenter.controller.popups.AssignDoctorsController;
import lk.ijse.project.mentalHealthTherapyCenter.controller.popups.SelectProgramsController;
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
    private Label patientAddress;

    @FXML
    private Label patientDOB;

    @FXML
    private Label patientEMAIL;

    @FXML
    private Label patientGender;

    @FXML
    private Label patientID;

    @FXML
    private Label patientNIC;

    @FXML
    private Label patientName;

    @FXML
    private Label patientTelNO;

    @FXML
    private Label paymentID;

    @FXML
    private Button registerPatient;

    @FXML
    private Button reset;

    @FXML
    private TextField search;



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
    private Label date;

    @FXML
    private ImageView image;

    @FXML
    private TextField payAMOUNT;

    @FXML
    private ComboBox<String> paymentMethod;

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

    @FXML
    private Button searchPatient;

    @FXML
    private Button printBillBTN;

    @FXML
    private Button viewAppointmentsBTN;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    private Set<String> programIDs = new HashSet<>();

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
    void printBillBTNAction(ActionEvent event) {

    }

    @FXML
    void viewAppointmentsBTNAction(ActionEvent event) throws IOException {loadNewPage("/view/viewAppointments.fxml");}

    @FXML
    void addAppointmentBTNAction(ActionEvent event) {
        String patientId = patientID.getText();
        String patientNAME = patientName.getText();
        String sessionId = sessionID.getText();
        String sessionTIME =  sessionTime.getText();
        String sessionNOTES = sessionNotes.getText();
        String sessionDATE = sessionDate.getEditor().getText();
        String doctorIDFromLabel = docLoadLabel.getText();
        String docID = null; /*this id is pass through sessionDTO*/

        String[] parts = doctorIDFromLabel.split(" - ");
        if (parts.length > 0) {
            docID = parts[0];  // First part is docID , get id from the full label
        }

        String paymentId = paymentID.getText();
        Double payAmount = Double.valueOf(payAMOUNT.getText());
        String paymentMETHOD = paymentMethod.getSelectionModel().getSelectedItem();
        String paymentDate = LocalDate.now().format(formatter);
        String paymentTime = LocalTime.now().format(timeFormatter);

        programmsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<String> selectedPrograms = programmsListView.getItems();
        programIDs.clear();

        for (String program : selectedPrograms) {
            if (program.contains(" - ")) {
                String programID = program.split(" - ")[0];
                programIDs.add(programID);
            } else {
                System.out.println("Error: Invalid format for program item! " + program);
            }
        }
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

            ProgramDetailsDTO programDetailsDTO = new ProgramDetailsDTO(
                patientId,
                new ArrayList<>(programIDs)  /*List required as one patient can choose more than one programs*/

            );
            SessionDTO sessionDTO = new SessionDTO(
                    sessionId,
                    patientId,
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

            boolean isSaved = appointmentBO.addAppointment(programDetailsDTO,sessionDTO,paymentDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Appointment added", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed! Appointment not added", ButtonType.OK).show();
            }
      /*  }*/
    }


    @FXML
    void registerPatientAction(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to Register a Patient", ButtonType.OK, ButtonType.CANCEL);
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);  // Default to CANCEL if no selection is made
        if (result == ButtonType.OK) {navigateTo("/view/patient.fxml");}
    }

    @FXML
    void searchPatientAction(MouseEvent event) {
        String searchBYName = search.getText();
        if (searchBYName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter a Name to Search", ButtonType.OK,ButtonType.CANCEL);
            return;
        }
        List<PatientDTO> isSearching = appointmentBO.searchPatientBYName(searchBYName);

        if (isSearching.isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.ERROR, "No Name Found as " + searchBYName, ButtonType.OK,ButtonType.CANCEL);
           alert.showAndWait().orElse(ButtonType.CANCEL);
           return;
        }
        for (PatientDTO patientDTO : isSearching) {
            patientID.setText(patientDTO.getPatientID());
            patientName.setText(patientDTO.getPatientName());
            patientDOB.setText(patientDTO.getPatientBirthDate());
            patientNIC.setText(patientDTO.getPatientNIC());
            patientGender.setText(patientDTO.getPatientGender());
            patientAddress.setText(patientDTO.getPatientAddress());
            patientEMAIL.setText(patientDTO.getPatientEmail());
        }
        vbox1.setVisible(true);
        vbox2.setVisible(true);
    }

    @FXML
    void addDoctorsAction(MouseEvent event) throws IOException {loadNewPage("/view/assignDocs.fxml");}

    @FXML
    void resetAction(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void addProgramsAction(MouseEvent event) throws IOException {loadNewPage("/view/SelectPrograms.fxml");}

    private  void  loadNewPage(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load(); // Load the FXML

        if (fxmlPath.equals("/view/assignDocs.fxml")) {
            AssignDoctorsController assignDoctorsController = loader.getController();
            assignDoctorsController.setAppointmentsController(this);
        } else if (fxmlPath.equals("/view/SelectPrograms.fxml")) {
            SelectProgramsController selectProgramsController = loader.getController();
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
        paymentMethod.setItems(FXCollections.observableArrayList("Card Payment", "Cash Payment"));
        sessionTime.clear();
        sessionNotes.clear();
        payAMOUNT.clear();
        docLoadLabel.setDisable(true);
        programmsListView.refresh();
        vbox1.setVisible(false);
        vbox2.setVisible(false);

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
    public void navigateTo(String fxmlPath) {
        try {
            appointmentPage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.getStylesheets().add(getClass().getResource("/css/h.css").toExternalForm());
            load.prefWidthProperty().bind(appointmentPage.widthProperty());
            load.prefHeightProperty().bind(appointmentPage.heightProperty());
            appointmentPage.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
