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
import lk.ijse.project.mentalHealthTherapyCenter.dto.ProgramNDocDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.ProgramNDocTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TProgramBO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapyProgramsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/TherapyPrograms.png"));
        image.setImage(adminIMage);

        tableIID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        tableProgramDetails.setCellValueFactory(new PropertyValueFactory<>("programDetails"));
        tableFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
        tableDoc.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        tableDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        tableDocAvailability.setCellValueFactory(new PropertyValueFactory<>("doctorAvailability"));

        try{
            refreshPage();
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Load the Page", ButtonType.OK).show();
        }
    }

    @FXML
    private TextField ProgramDetails;

    @FXML
    private TextField ProgramFee;

    @FXML
    private TextField ProgramName;

    @FXML
    private TableView<ProgramNDocTM> Table;

    @FXML
    private Button delete;

    @FXML
    private ImageView image;

    @FXML
    private Label labelLoadID;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private TableColumn<ProgramNDocTM, Double> tableFee;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableIID;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableName;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableProgramDetails;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableDoc;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableDocAvailability;

    @FXML
    private TableColumn<ProgramNDocTM, String> tableDocName;

    TProgramBO tProgramBO = BOFactory.getInstance().getBO(BOType.THERAPY_PROGRAMS);

    @FXML
    private Button update;

    @FXML
    private ListView<String> doctorsListView;


    private static String DoctorID;

    public void setAddDoctors(String docID, String docName,String availability) {
        System.out.println("awaa");
        DoctorID = docID;
        if (docID != null && docName != null && availability != null) {
            doctorsListView.getItems().add(docID + " - " + docName + " - " + availability);
        }
    }


    @FXML
    void addDoctorsAction(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/assignDocs.fxml"));
        Scene scene = new Scene(loader.load());
        AssignDoctorsController assignDoctorsController = loader.getController();
        assignDoctorsController.setTherapyProgramsController(this);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Therapy Programs - Serenity Mental Health Therapy Center");
        stage.show();
    }

    @FXML
    void deleteBtnAction(ActionEvent event) throws Exception {
        String programID = labelLoadID.getText();
        boolean isDeleted = tProgramBO.deleteTProgram(programID);
        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Programs Deleted").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Programs Not Deleted").show();
        }

    }

    @FXML
    void resetBtnAction(ActionEvent event) throws Exception {
        refreshPage();
    }
    @FXML
    void saveBtnAction(ActionEvent event) throws Exception {
        String therapyPID = labelLoadID.getText();
        String therapyProgramName = ProgramName.getText();
        String therapyProgramDetails = ProgramDetails.getText();
        Double therapyProgramFee = Double.parseDouble(ProgramFee.getText());

        String listPrograms = doctorsListView.getSelectionModel().getSelectedItem();

        if (listPrograms == null || listPrograms.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a program from the list.", ButtonType.OK).show();
            return; // Return early if no program is selected
        }

        if (listPrograms.contains("-")) {
            DoctorID = listPrograms.split("-")[0];
            System.out.println("Extracted Program ID: " + DoctorID);
        } else {
            System.out.println("Invalid program format: " + listPrograms);
            new Alert(Alert.AlertType.ERROR, "Invalid program format.", ButtonType.OK).show();
            return;
        }

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
            therapyPID,
            therapyProgramName,
            therapyProgramDetails,
            therapyProgramFee,
                DoctorID
        );
        boolean isSaved = tProgramBO.saveTPrograms(therapyProgramDTO);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Therapy Program Saved").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Therapy Program Not Saved").show();
        }
    }

    @FXML
    void tableAction(MouseEvent event) {
        ProgramNDocTM selectedPatient = Table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            labelLoadID.setText(selectedPatient.getProgramID());
            ProgramName.setText(selectedPatient.getProgramName());
            ProgramDetails.setText(selectedPatient.getProgramDetails());
            ProgramFee.setText(String.valueOf(selectedPatient.getProgramFee()));
            ObservableList<String> programDetails = FXCollections.observableArrayList();
            programDetails.add(selectedPatient.getDoctorID() + " - " + selectedPatient.getDoctorName() + " - " + selectedPatient.getDoctorAvailability());
            doctorsListView.setItems(programDetails); // Correct way to load ListView
        }
    }

    @FXML
    void updateBtnAction(ActionEvent event) throws Exception {
        String therapyPID = labelLoadID.getText();
        String therapyProgramName = ProgramName.getText();
        String therapyProgramDetails = ProgramDetails.getText();
        Double therapyProgramFee = Double.parseDouble(ProgramFee.getText());

        String listPrograms = doctorsListView.getSelectionModel().getSelectedItem();

        if (listPrograms == null || listPrograms.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a program from the list.", ButtonType.OK).show();
            return; // Return early if no program is selected
        }

        DoctorID = listPrograms.split("-")[0];  // Program ID from selected item
        String ProgramName = listPrograms.split("-")[1]; // Program Name from selected item

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                therapyPID,
                therapyProgramName,
                therapyProgramDetails,
                therapyProgramFee,
                DoctorID
        );
        boolean isUpdated = tProgramBO.updateTPrograms(therapyProgramDTO);

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Therapy Programs updated successfully").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Therapy Programs updating Failed").show();
        }
    }

    private void loadTable() throws Exception {
        List<ProgramNDocDTO> programsAndDocs =  tProgramBO.getALL();
        ObservableList<ProgramNDocTM> programNDocTMS = FXCollections.observableArrayList();
        for (ProgramNDocDTO programNDocDTO : programsAndDocs) {

            ProgramNDocTM programNDocTM = new ProgramNDocTM(
                    programNDocDTO.getProgramID(),
                    programNDocDTO.getProgramName(),
                    programNDocDTO.getProgramDetails(),
                    programNDocDTO.getProgramFee(),
                    programNDocDTO.getDoctorID(),
                    programNDocDTO.getProgramName(),
                    programNDocDTO.getDoctorAvailability()
            );
            programNDocTMS.add(programNDocTM);
        }
        Table.setItems(programNDocTMS);
    }

    private void refreshPage() throws Exception {
        loadTable();
        labelLoadID.setText(tProgramBO.getNextProgramID());
        ProgramName.clear();
        ProgramDetails.clear();
        ProgramFee.clear();

    }
}



