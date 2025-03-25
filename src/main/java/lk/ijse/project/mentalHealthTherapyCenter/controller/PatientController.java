package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.PatientTM;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.PatientBO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/patient.png"));
        image.setImage(image1);

        tablePId.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        tablePName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        tablePDob.setCellValueFactory(new PropertyValueFactory<>("patientBirthDate"));
        tablePNic.setCellValueFactory(new PropertyValueFactory<>("patientNIC"));
        tablePGender.setCellValueFactory(new PropertyValueFactory<>("patientGender"));
        tablePAddress.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));
        tablePContact.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));
        tablePEmail.setCellValueFactory(new PropertyValueFactory<>("patientEmail"));
        try {
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, " Failed ").show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button delete;

    @FXML
    private TableView<PatientTM> table;

    @FXML
    private TableColumn<PatientTM, String> tablePAddress;

    @FXML
    private TableColumn<PatientTM, String> tablePContact;

    @FXML
    private TableColumn<PatientTM, String> tablePDob;

    @FXML
    private TableColumn<PatientTM, String> tablePEmail;

    @FXML
    private TableColumn<PatientTM, String> tablePGender;

    @FXML
    private TableColumn<PatientTM, String> tablePId;

    @FXML
    private TableColumn<PatientTM, String> tablePName;

    @FXML
    private TableColumn<PatientTM, String> tablePNic;

    @FXML
    private Button update;

    @FXML
    private TextField PDateOfBirth;

    @FXML
    private TextField PName;

    @FXML
    private TextField PatientAddress;

    @FXML
    private TextField PatientContactNO;

    @FXML
    private TextField PatientEmail;

    @FXML
    private TextField PatientGender;

    @FXML
    private TextField PatientNic;

    @FXML
    private ImageView image;

    @FXML
    private Label loadPatientID;

    PatientBO patientBO = BOFactory.getInstance().getBO(BOType.PATIENT);

    @FXML
    void deleteAction(ActionEvent event) throws Exception {
        String patientID = loadPatientID.getText();

        if (patientID.isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, " Please select data from table",ButtonType.CLOSE).show();
            return;
        }
        boolean isDeleted = patientBO.deletePatient(patientID);
        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Deletion Failed",ButtonType.OK).show();
        }
    }

    @FXML
    void tableAction(MouseEvent event) {
        PatientTM selectedPatient = table.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            loadPatientID.setText(selectedPatient.getPatientID());
            PName.setText(selectedPatient.getPatientName());
            PDateOfBirth.setText(selectedPatient.getPatientBirthDate());
            PatientNic.setText(selectedPatient.getPatientNIC());
            PatientGender.setText(selectedPatient.getPatientGender());
            PatientAddress.setText(selectedPatient.getPatientAddress());
            PatientContactNO.setText(selectedPatient.getPatientPhone());
            PatientEmail.setText(selectedPatient.getPatientEmail());

        }

    }

    @FXML
    void updateAction(ActionEvent event) throws Exception {
        String patientID = loadPatientID.getText();
        String patientName = PName.getText();
        String patientBirthDate = PDateOfBirth.getText();
        String patientNIC = PatientNic.getText();
        String patientGender = PatientGender.getText();
        String patientAddress = PatientAddress.getText();
        String patientPhone = PatientContactNO.getText();
        String patientEmail = PatientEmail.getText();

        if (patientID.isEmpty() || patientName.trim().isEmpty() || patientBirthDate.isEmpty() || patientNIC.isEmpty() || patientGender.trim().isEmpty() || patientAddress.trim().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Please select data from table",ButtonType.CLOSE).show();
            return;
        }

       PatientDTO patientDTO = new PatientDTO(
               patientID,
               patientName,
               patientBirthDate,
               patientNIC,
               patientGender,
               patientAddress,
               patientPhone,
               patientEmail
       );

       boolean isUpdated = patientBO.updatePatient(patientDTO);
            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "PatientDAOImpl updated successfully", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "PatientDAOImpl updated Failed",ButtonType.OK).show();
            }
    }
    private void loadTableData() throws Exception {

        List<PatientDTO> patientDTOS = patientBO.getALL();
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for (PatientDTO patientDTO : patientDTOS) {
            PatientTM patientTM = new PatientTM(
                    patientDTO.getPatientID(),
                    patientDTO.getPatientName(),
                    patientDTO.getPatientBirthDate(),
                    patientDTO.getPatientNIC(),
                    patientDTO.getPatientGender(),
                    patientDTO.getPatientAddress(),
                    patientDTO.getPatientPhone(),
                    patientDTO.getPatientEmail()

            );
            patientTMS.add(patientTM);
        }

        table.setItems(patientTMS);
    }
    private void refreshPage() throws Exception {
        loadTableData();
        loadPatientID.setText("");
        PName.clear();
        PDateOfBirth.clear();
        PatientNic.clear();
        PatientGender.clear();
        PatientAddress.clear();
        PatientContactNO.clear();
        PatientEmail.clear();
    }

}
