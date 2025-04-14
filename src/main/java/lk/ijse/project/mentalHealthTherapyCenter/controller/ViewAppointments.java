package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.mentalHealthTherapyCenter.dto.PaymentDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ProgramDetailsDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.SessionDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.ViewSessionTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ViewSessionDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class ViewAppointments implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableSessionID.setCellValueFactory(new PropertyValueFactory<>("sessionID"));
        tableSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        tableSessionNotes.setCellValueFactory(new PropertyValueFactory<>("sessionNotes"));
        tableSessionTime.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
        tableDocID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        tableProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        tablePatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        tablePaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        tablePaymentAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        tablePaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        tableSessionStatus.setCellValueFactory(new PropertyValueFactory<>("appointmentStatus"));

        try{
            refreshPage();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to Load Page SQL ERROR").showAndWait();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to Load Page ClassNotFound").showAndWait();
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private ComboBox<String> ComboDocId;

    @FXML
    private TableView<ViewSessionTM> Table;

    @FXML
    private Button cancelBTN;

    @FXML
    private ComboBox<String> comboPatientName;

    @FXML
    private ComboBox<String> comboPaymentMethod;

    @FXML
    private ImageView image;

    @FXML
    private Label labelPaymentID;

    @FXML
    private Label labelSessionID;

    @FXML
    private Button rescheduleBTN;

    @FXML
    private Button resetBTN;

    @FXML
    private TableColumn<String,ViewSessionTM> tableDocID;

    @FXML
    private TableColumn<String,ViewSessionTM> tablePatientName;

    @FXML
    private TableColumn<Double,ViewSessionTM> tablePaymentAmount;

    @FXML
    private TableColumn<String,ViewSessionTM> tablePaymentID;

    @FXML
    private TableColumn<String,ViewSessionTM> tablePaymentMethod;

    @FXML
    private TableColumn<String,ViewSessionTM> tableProgramID;

    @FXML
    private TableColumn<Date,ViewSessionTM> tableSessionDate;

    @FXML
    private TableColumn<String,ViewSessionTM> tableSessionID;

    @FXML
    private TableColumn<String,ViewSessionTM> tableSessionNotes;

    @FXML
    private TableColumn<String,ViewSessionTM> tableSessionStatus;

    @FXML
    private TableColumn<String,ViewSessionTM > tableSessionTime;

    @FXML
    private TextField textSessionDate;

    @FXML
    private TextField textSessionTime;

    @FXML
    private TextField txtPaymentAmount;

    @FXML
    private TextField txtSessionNotes;

    private Set<String> programIDs = new HashSet<>();

    AppointmentBO appointmentBO =  BOFactory.getInstance().getBO(BOType.APPOINTMENT);

    @FXML
    void cancelBTNAction(ActionEvent event) throws Exception {
        String id = labelSessionID.getText();
        ViewSessionTM viewSessionTM = Table.getSelectionModel().getSelectedItem();

        if (viewSessionTM == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an appointment from the table first.").showAndWait();
            return;
        }

        String currentStatus = viewSessionTM.getAppointmentStatus();

        if ("Appointment Cancelled".equalsIgnoreCase(currentStatus)) {
            new Alert(Alert.AlertType.ERROR, "Appointment has already been cancelled.").showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this appointment?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isCancelled = appointmentBO.cancelAppointment(id);
            if (isCancelled) {
                refreshPage(); // update table if needed
                new Alert(Alert.AlertType.INFORMATION, "Appointment cancelled successfully!").showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to cancel appointment!").showAndWait();
            }
        }
    }

    @FXML
    void rescheduleBTNAction(ActionEvent event) throws Exception {
        String appointmentID = labelSessionID.getText();
        String appointmentDate = textSessionDate.getText();
        String appointmentNotes = txtSessionNotes.getText();
        String appointmentTime = textSessionTime.getText();
        String doctorID = ComboDocId.getValue();
        String patientName = comboPatientName.getValue();
        String paymentID = labelPaymentID.getText();
        Double paymentAmount = Double.valueOf(txtPaymentAmount.getText());
        String paymentMethod = comboPaymentMethod.getValue();
        String patientId = appointmentBO.searchPatientID(patientName);

        if (appointmentID.isEmpty()||appointmentDate.isEmpty() || doctorID.isEmpty() || patientId.isEmpty() || paymentID.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please enter appointment details").show();
        }

        ProgramDetailsDTO programDetailsDTO = new ProgramDetailsDTO(
                patientId,
                new ArrayList<>(programIDs)  /*List required as one patient can choose more than one programs*/

        );
        SessionDTO sessionDTO = new SessionDTO(
                appointmentID,
                patientId,
                doctorID,
                appointmentTime,
                appointmentNotes,
                appointmentDate
        );
        PaymentDTO paymentDTO = new PaymentDTO(
                paymentID,
                patientName,
                paymentAmount,
                paymentMethod

        );

        boolean isSaved = appointmentBO.updateAppointments(programDetailsDTO,sessionDTO,paymentDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Appointment added", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Failed! Appointment not added", ButtonType.OK).show();
        }

    }

    @FXML
    void resetBTNAction(ActionEvent event) throws Exception {
        labelPaymentID.setVisible(false);
        labelSessionID.setVisible(false);
        textSessionDate.clear();
        textSessionTime.clear();
        txtPaymentAmount.clear();
        txtSessionNotes.clear();
        comboPatientName.getItems().clear();
        comboPaymentMethod.setItems(FXCollections.observableArrayList("Card Payment","Cash Payment"));
        ComboDocId.getItems().clear();

    }

    @FXML
    void tableAction(MouseEvent event) {
        ViewSessionTM selectedItem = Table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            labelPaymentID.setVisible(true);
            labelSessionID.setVisible(true);
            labelSessionID.setText(selectedItem.getSessionID());
            textSessionDate.setText(selectedItem.getSessionDate());
            txtSessionNotes.setText(selectedItem.getSessionNotes());
            textSessionTime.setText(selectedItem.getSessionTime());
            ComboDocId.setValue(selectedItem.getDoctorID());
            comboPatientName.setValue(selectedItem.getPatientName());
            labelPaymentID.setText(String.valueOf(selectedItem.getPaymentID()));
            txtPaymentAmount.setText(String.valueOf(selectedItem.getPaymentAmount()));
            comboPaymentMethod.setValue(selectedItem.getPaymentMethod());
        }
    }
    private void loadTable(){
        List<ViewSessionDTO> viewSessionDTOS =  appointmentBO.getAllAppointments();
        ObservableList<ViewSessionTM> viewSessionTMS = FXCollections.observableArrayList();
        for (ViewSessionDTO viewSessionDTO : viewSessionDTOS) {

            ViewSessionTM viewSessionTM = new ViewSessionTM(
                    viewSessionDTO.getSessionID(),
                    viewSessionDTO.getSessionDate(),
                    viewSessionDTO.getSessionNotes(),
                    viewSessionDTO.getSessionTime(),
                    viewSessionDTO.getDoctorID(),
                    viewSessionDTO.getPrograms(),
                    viewSessionDTO.getPatientName(),
                    viewSessionDTO.getPaymentID(),
                    viewSessionDTO.getPaymentAmount(),
                    viewSessionDTO.getPaymentMethod(),
                    viewSessionDTO.getAppointmentStatus()
            );
            viewSessionTMS.add(viewSessionTM);
        }
        Table.setItems(viewSessionTMS);
    }

    private void refreshPage() throws Exception {
        loadTable();
        labelPaymentID.setVisible(false);
        labelSessionID.setVisible(false);
        textSessionDate.clear();
        textSessionTime.clear();
        txtPaymentAmount.clear();
        txtSessionNotes.clear();
        comboPatientName.setItems(FXCollections.observableArrayList(appointmentBO.loadPatientNames()));
        comboPaymentMethod.setItems(FXCollections.observableArrayList("Card Payment","Cash Payment"));
        ComboDocId.setItems(FXCollections.observableArrayList(appointmentBO.loadDoctorIds()));
    }
}
