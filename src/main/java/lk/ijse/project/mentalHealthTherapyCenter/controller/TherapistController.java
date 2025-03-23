
package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.TherapistTM;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapistController  implements Initializable {

    @FXML
    private Button addPrograms;

    @FXML
    private Button delete;

    @FXML
    private ComboBox<String> docAvailableCombo;

    @FXML
    private TextField docContact;

    @FXML
    private Label docIDlabel;

    @FXML
    private TextField docMail;

    @FXML
    private TextField docName;

    @FXML
    private ComboBox<String> docQualificationsCombo;

    @FXML
    private ImageView image;

    @FXML
    private ListView<String> programmsListView;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private TableView<TherapistTM> table;

    @FXML
    private TableColumn<TherapistTM, String> tableAvailable;

    @FXML
    private TableColumn<TherapistTM, String> tableContact;

    @FXML
    private TableColumn<TherapistTM, String> tableId;

    @FXML
    private TableColumn<TherapistTM, String> tableMail;

    @FXML
    private TableColumn<TherapistTM, String> tableName;

    @FXML
    private TableColumn<TherapistTM, String> tableProgramID;

    @FXML
    private TableColumn<TherapistTM, String> tableProgramName;

    @FXML
    private TableColumn<TherapistTM, String> tableQualifications;

    @FXML
    private Button update;

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    @FXML
    void TableAction(MouseEvent event) {
        TherapistTM selectedPatient = table.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            docIDlabel.setText(selectedPatient.getDoctorID());
            docName.setText(selectedPatient.getDoctorName());
            // Load data into ListView
            ObservableList<String> programDetails = FXCollections.observableArrayList();
            programDetails.add(selectedPatient.getProgramID() + " - " + selectedPatient.getProgramName());

            programmsListView.setItems(programDetails); // Correct way to load ListView

            docQualificationsCombo.setValue(selectedPatient.getDoctorQualifications());
            docAvailableCombo.setValue(selectedPatient.getDoctorAvailability());
            docContact.setText(selectedPatient.getDoctorPhone());
            docMail.setText(selectedPatient.getDoctorEmail());

        }
    }

    @FXML
    void addProgramsAction(MouseEvent event) throws IOException {
        loadNewPage("/view/SelectPrograms.fxml");
    }

    @FXML
    void deleteBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String patientID = docIDlabel.getText();
        boolean isDeleted = therapistBO.deleteTherapist(patientID);
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Deletion Failed").show();
        }
    }

    @FXML
    void resetBtnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnAction(ActionEvent event) {
        String DoctorID = docIDlabel.getText();
        String DocName = docName.getText();
        String PatientId = null;
        String PatientName = null;
        String DocQualifications = docQualificationsCombo.getSelectionModel().getSelectedItem();
        String DocAvailability = docAvailableCombo.getSelectionModel().getSelectedItem();
        String DocPhone = docContact.getText();
        String DocMail = docMail.getText();

        String listPrograms = programmsListView.getSelectionModel().getSelectedItem().toString();
        if (listPrograms != null) {
            PatientId = listPrograms.split("-")[0];
            PatientName = listPrograms.split("-")[1];
        }

        String namePattern = "^[a-zA-Z ]+$";
        String mailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String PhoneNoPattern = "^\\+?[1-9]\\d{0,2}[-.\\s]?\\d{1,4}[-.\\s]?\\d{3,4}[-.\\s]?\\d{3,4}$";

        boolean isValidName = DocName.matches(namePattern);
        boolean isValidMail = DocMail.matches(mailPattern);
        boolean isValidPhoneNO = DocPhone.matches(PhoneNoPattern);

        if (!isValidName) {
            docName.setStyle(docName.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidMail) {
            docMail.setStyle(docMail.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhoneNO) {
            docContact.setStyle(docContact.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidName && !isValidMail && !isValidPhoneNO) {
            DoctorDTO doctorDTO = new DoctorDTO(
                    DoctorID,
                    DocName,
                    PatientId,
                    PatientName,
                    DocQualifications,
                    DocAvailability,
                    DocPhone,
                    DocMail
            );
            boolean isSaved = therapistBO.saveTherapist(doctorDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION,"Therapist Saved",ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Saving Failed",ButtonType.OK).show();
            }
        }
    }

    @FXML
    void updateBtnAction(ActionEvent event) {
        String DoctorID = docIDlabel.getText();
        String DocName = docName.getText();
        String PatientId = null;
        String PatientName = null;
        String DocQualifications = docQualificationsCombo.getSelectionModel().getSelectedItem().toString();
        String DocAvailability = docAvailableCombo.getSelectionModel().getSelectedItem().toString();
        String DocPhone = docContact.getText();
        String DocMail = docMail.getText();

        String listPrograms = programmsListView.getSelectionModel().getSelectedItem().toString();
        if (listPrograms != null) {
            PatientId = listPrograms.split("-")[0];
            PatientName = listPrograms.split("-")[1];
        }
        String namePattern = "^[a-zA-Z ]+$";
        String mailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String PhoneNoPattern = "^\\+?[1-9]\\d{0,2}[-.\\s]?\\d{1,4}[-.\\s]?\\d{3,4}[-.\\s]?\\d{3,4}$";

        boolean isValidName = DocName.matches(namePattern);
        boolean isValidMail = DocMail.matches(mailPattern);
        boolean isValidPhoneNO = DocPhone.matches(PhoneNoPattern);

        if (!isValidName) {
            docName.setStyle(docName.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidMail) {
            docMail.setStyle(docMail.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhoneNO) {
            docContact.setStyle(docContact.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidName && !isValidMail && !isValidPhoneNO) {
            DoctorDTO doctorDTO = new DoctorDTO(
                    DoctorID,
                    DocName,
                    PatientId,
                    PatientName,
                    DocQualifications,
                    DocAvailability,
                    DocPhone,
                    DocMail
            );
            boolean isSaved = therapistBO.updateTherapist(doctorDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION,"Therapist Updated",ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Updating Failed",ButtonType.OK).show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/doctor.png"));
        image.setImage(image1);

        tableId.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        tableProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        tableProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        tableQualifications.setCellValueFactory(new PropertyValueFactory<>("doctorQualifications"));
        tableAvailable.setCellValueFactory(new PropertyValueFactory<>("doctorAvailability"));
        tableContact.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));
        tableMail.setCellValueFactory(new PropertyValueFactory<>("doctorEmail"));

        try{
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Failed to load Page", ButtonType.OK).show();
        }
    }
    private  void  loadNewPage(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Therapy Programs - Serenity Mental Health Therapy Center");
        stage.show();
    }
    private void loadTable(){
        ArrayList<DoctorDTO> doctorDTOS =  therapistBO.getALLTherapist();
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();
        for (DoctorDTO doctorDTO : doctorDTOS) {
            TherapistTM therapistTM = new TherapistTM(
                    doctorDTO.getDoctorID(),
                    doctorDTO.getDoctorName(),
                    doctorDTO.getProgramID(),
                    doctorDTO.getProgramName(),
                    doctorDTO.getDoctorQualifications(),
                    doctorDTO.getDoctorAvailability(),
                    doctorDTO.getDoctorPhone(),
                    doctorDTO.getDoctorEmail()
            );
            therapistTMS.add(therapistTM);
        }
        table.setItems(therapistTMS);
    }
}

