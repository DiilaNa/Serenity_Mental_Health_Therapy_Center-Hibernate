
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
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.ProgramNDocTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ProgramNDocDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
    private TableView<ProgramNDocTM> table;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableAvailable;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableContact;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableId;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableMail;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableName;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableProgramID;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableProgramName;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableQualifications;

    @FXML
    private Button update;

    private static String ProgramID;

    public void setDetails(String programID, String programName) {
        ProgramID = programID;
        if (programID != null && programName != null) {
            programmsListView.getItems().add(programID + " - " + programName);
        }
    }

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    @FXML
    void TableAction(MouseEvent event) {
        ProgramNDocTM selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            docIDlabel.setText(selectedPatient.getDoctorID());
            docName.setText(selectedPatient.getDoctorName());
            // Load data into ListView
            ObservableList<String> programDetails = FXCollections.observableArrayList();
            programDetails.add(selectedPatient.getTherapyID() + " - " + selectedPatient.getTherapyName());
            programmsListView.setItems(programDetails); // Correct way to load ListView
            docQualificationsCombo.setValue(selectedPatient.getDoctorQualifications());
            docAvailableCombo.setValue(selectedPatient.getDoctorAvailability());
            docContact.setText(selectedPatient.getDoctorPhone());
            docMail.setText(selectedPatient.getDoctorEmail());
        }
    }

    @FXML
    void addProgramsAction(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelectPrograms.fxml"));
        Scene scene = new Scene(loader.load());
        SelectProgramsController selectProgramsController = loader.getController();
        selectProgramsController.setTherapistController(this);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Therapy Programs - Serenity Mental Health Therapy Center");
        stage.show();
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
        String DocQualifications = docQualificationsCombo.getSelectionModel().getSelectedItem();
        String DocAvailability = docAvailableCombo.getSelectionModel().getSelectedItem();
        String DocPhone = docContact.getText();
        String DocMail = docMail.getText();

        String listPrograms = programmsListView.getSelectionModel().getSelectedItem();

        if (listPrograms == null || listPrograms.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a program from the list.", ButtonType.OK).show();
            return; // Return early if no program is selected
        }

        if (listPrograms.contains("-")) {
            ProgramID = listPrograms.split("-")[0];
            System.out.println("Extracted Program ID: " + ProgramID);
        } else {
            System.out.println("Invalid program format: " + listPrograms);
            new Alert(Alert.AlertType.ERROR, "Invalid program format.", ButtonType.OK).show();
            return;
        }


    /*    String namePattern = "^[a-zA-Z ]+$";
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
        }*/
            DoctorDTO doctorDTO = new DoctorDTO(
                    DoctorID,
                    DocName,
                    ProgramID,
                    DocQualifications,
                    DocAvailability,
                    DocPhone,
                    DocMail
            );
            boolean isSaved = therapistBO.saveTherapist(doctorDTO);
            if (isSaved) {
                refreshPage();
                System.out.println("Saved Successfully");
                new Alert(Alert.AlertType.INFORMATION,"Therapist Saved",ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Saving Failed",ButtonType.OK).show();
            }
    }

    @FXML
    void updateBtnAction(ActionEvent event) {
        String DoctorID = docIDlabel.getText();
        String DocName = docName.getText();
        String DocQualifications = docQualificationsCombo.getSelectionModel().getSelectedItem();
        String DocAvailability = docAvailableCombo.getSelectionModel().getSelectedItem();
        String DocPhone = docContact.getText();
        String DocMail = docMail.getText();

        String listPrograms = programmsListView.getSelectionModel().getSelectedItem();

        if (listPrograms == null || listPrograms.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a program from the list.", ButtonType.OK).show();
            return; // Return early if no program is selected
        }

        ProgramID = listPrograms.split("-")[0];  // Program ID from selected item
        String ProgramName = listPrograms.split("-")[1]; // Program Name from selected item

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
                    ProgramID,
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
        tableProgramID.setCellValueFactory(new PropertyValueFactory<>("therapyID"));
        tableProgramName.setCellValueFactory(new PropertyValueFactory<>("therapyName"));
        tableQualifications.setCellValueFactory(new PropertyValueFactory<>("doctorQualifications"));
        tableAvailable.setCellValueFactory(new PropertyValueFactory<>("doctorAvailability"));
        tableContact.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));
        tableMail.setCellValueFactory(new PropertyValueFactory<>("doctorEmail"));

        try{
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Failed to load Page", ButtonType.OK).show();
        }
    }

    private void loadTable(){
        List<ProgramNDocDTO> programNDocDTOS =  therapistBO.getALLTherapist();
        if (programNDocDTOS == null || programNDocDTOS.isEmpty()) {
            System.out.println("No data available");
        }
        ObservableList<ProgramNDocTM> programNDocTMS = FXCollections.observableArrayList();
        for (ProgramNDocDTO programNDocDTO : programNDocDTOS) {
            ProgramNDocTM programNDocTM = new ProgramNDocTM(
                    programNDocDTO.getDoctorID(),
                    programNDocDTO.getDoctorName(),
                    programNDocDTO.getTherapyID(),
                    programNDocDTO.getTherapyName(),
                    programNDocDTO.getDoctorQualifications(),
                    programNDocDTO.getDoctorAvailability(),
                    programNDocDTO.getDoctorPhone(),
                    programNDocDTO.getDoctorEmail()
            );
            programNDocTMS.add(programNDocTM);
        }
        table.setItems(programNDocTMS);
    }
    private void refreshPage(){
        docIDlabel.setText(therapistBO.getNextTherapyID());

        docAvailableCombo.setItems(FXCollections.observableArrayList("Available","Not Available"));
        docQualificationsCombo.setItems(FXCollections.observableArrayList("Bsc","Msc","Phd"));
        loadTable();
        docName.clear();
        docContact.clear();
        docMail.clear();
    }
}

