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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.project.mentalHealthTherapyCenter.dto.MedicalHistoryDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.MedicalHistoryTM;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.PatientBO;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MedicalHistoryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResourceAsStream("/images/appointmentIcon.png"));
        Image.setImage(image);

        tablePid.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        tablePName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        tableProID.setCellValueFactory(new PropertyValueFactory<>("programs"));
        tableDocName.setCellValueFactory(new PropertyValueFactory<>("doctorNAME"));
        tableAptID.setCellValueFactory(new PropertyValueFactory<>("sessionID"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("sessionDATE"));
        tableTime.setCellValueFactory(new PropertyValueFactory<>("sessionTIME"));

        try{
            loadTable();
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load the Page", ButtonType.CLOSE).show();
        }
    }

    @FXML
    private ImageView Image;

    @FXML
    private TableView<MedicalHistoryTM> Table;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableAptID;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableDate;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableDocName;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tablePName;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tablePid;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableProID;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableTime;

    PatientBO patientBO = BOFactory.getInstance().getBO(BOType.PATIENT);

    private void loadTable() throws SQLException, ClassNotFoundException {
        List<MedicalHistoryDTO> medicalHistoryDTOS =  patientBO.getPatientHistory();
        ObservableList<MedicalHistoryTM> medicalHistoryTMS = FXCollections.observableArrayList();
        for (MedicalHistoryDTO medicalHistoryDTO : medicalHistoryDTOS) {

            MedicalHistoryTM medicalHistoryTM = new MedicalHistoryTM(
                    medicalHistoryDTO.getPatientID(),
                    medicalHistoryDTO.getPatientName(),
                    medicalHistoryDTO.getPrograms(),
                    medicalHistoryDTO.getDoctorNAME(),
                    medicalHistoryDTO.getSessionID(),
                    medicalHistoryDTO.getSessionDATE(),
                    medicalHistoryDTO.getSessionTIME()
            );
            medicalHistoryTMS.add(medicalHistoryTM);
        }
        Table.setItems(medicalHistoryTMS);
    }
}
