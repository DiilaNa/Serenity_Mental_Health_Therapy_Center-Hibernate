package lk.ijse.project.mentalHealthTherapyCenter.controller.popups;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientsInEveryProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.PatientsInEveryProgramTM;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.PatientBO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientsEnrolledInEveryPrograms implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        tableAddress.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));
        tableContact.setCellValueFactory(new PropertyValueFactory<>("patientContact"));

        try {
            loadTable();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TableView<PatientsInEveryProgramTM> Table;

    @FXML
    private TableColumn<String,PatientsInEveryProgramTM> tableAddress;

    @FXML
    private TableColumn<String,PatientsInEveryProgramTM> tableContact;

    @FXML
    private TableColumn<String,PatientsInEveryProgramTM> tableID;

    @FXML
    private TableColumn<String,PatientsInEveryProgramTM> tableName;

    PatientBO patientBO = BOFactory.getInstance().getBO(BOType.PATIENT);

    private void loadTable() throws RuntimeException {
        List<PatientsInEveryProgramDTO> patientsInEveryProgramDTOS = patientBO.getPatientsInEveryProgram();

        ObservableList<PatientsInEveryProgramTM> patientsInEveryProgramTMS = FXCollections.observableArrayList();

        for (PatientsInEveryProgramDTO patients : patientsInEveryProgramDTOS) {
            PatientsInEveryProgramTM patientsInEveryProgramTM = new PatientsInEveryProgramTM(
                    patients.getPatientId(),
                    patients.getPatientName(),
                    patients.getPatientAddress(),
                    patients.getPatientContact()
            );
            patientsInEveryProgramTMS.add(patientsInEveryProgramTM);
        }

        Table.setItems(patientsInEveryProgramTMS);
    }

}
