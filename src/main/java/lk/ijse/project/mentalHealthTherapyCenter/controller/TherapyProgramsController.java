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
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.TProgramTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TProgramBO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TherapyProgramsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/TherapyPrograms.png"));
        image.setImage(adminIMage);

        tableIID.setCellValueFactory(new PropertyValueFactory<>("therapyID"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("therapyName"));
        tableProgramDetails.setCellValueFactory(new PropertyValueFactory<>("therapyDescription"));
        tableFee.setCellValueFactory(new PropertyValueFactory<>("therapyFee"));

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
    private TableView<TProgramTM> Table;

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
    private TableColumn<TProgramTM, Double> tableFee;

    @FXML
    private TableColumn<TProgramTM, String> tableIID;

    @FXML
    private TableColumn<TProgramTM, String> tableName;

    @FXML
    private TableColumn<TProgramTM, String> tableProgramDetails;

    TProgramBO tProgramBO = BOFactory.getInstance().getBO(BOType.THERAPY_PROGRAMS);

    @FXML
    private Button update;

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

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
            therapyPID,
            therapyProgramName,
            therapyProgramDetails,
            therapyProgramFee
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
        TProgramTM selectedPatient = Table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            labelLoadID.setText(selectedPatient.getTherapyID());
            ProgramName.setText(selectedPatient.getTherapyName());
            ProgramDetails.setText(selectedPatient.getTherapyDescription());
            ProgramFee.setText(String.valueOf(selectedPatient.getTherapyFee()));
        }
    }

    @FXML
    void updateBtnAction(ActionEvent event) throws Exception {
        String therapyPID = labelLoadID.getText();
        String therapyProgramName = ProgramName.getText();
        String therapyProgramDetails = ProgramDetails.getText();
        Double therapyProgramFee = Double.parseDouble(ProgramFee.getText());

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                therapyPID,
                therapyProgramName,
                therapyProgramDetails,
                therapyProgramFee
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
        List<TherapyProgramDTO> therapyProgramDTOS =  tProgramBO.getALLTPrograms();
        ObservableList<TProgramTM> tProgramTMS = FXCollections.observableArrayList();
        for (TherapyProgramDTO therapyProgramDTO : therapyProgramDTOS) {
            TProgramTM tProgramTM = new TProgramTM(
                    therapyProgramDTO.getTherapyID(),
                    therapyProgramDTO.getTherapyName(),
                    therapyProgramDTO.getTherapyDescription(),
                    therapyProgramDTO.getTherapyFee()
            );
            tProgramTMS.add(tProgramTM);
        }
        Table.setItems(tProgramTMS);
    }
    private void refreshPage() throws Exception {
        loadTable();
        labelLoadID.setText(tProgramBO.getNextProgramID());
        ProgramName.clear();
        ProgramDetails.clear();
        ProgramFee.clear();
    }
}



