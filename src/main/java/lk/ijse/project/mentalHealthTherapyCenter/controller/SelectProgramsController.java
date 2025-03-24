package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.TProgramTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TProgramBO;
import lombok.Setter;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SelectProgramsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableIID.setCellValueFactory(new PropertyValueFactory<>("therapyID"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("therapyName"));
        tableProgramDetails.setCellValueFactory(new PropertyValueFactory<>("therapyDescription"));
        tableFee.setCellValueFactory(new PropertyValueFactory<>("therapyFee"));

        try {
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Load Page", ButtonType.OK).show();
        }
    }

    @FXML
    private TableView<TProgramTM> Table;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button select;

    @FXML
    private TableColumn<TProgramTM, Double> tableFee;

    @FXML
    private TableColumn<TProgramTM, String> tableIID;

    @FXML
    private TableColumn<TProgramTM, String> tableName;

    @FXML
    private TableColumn<TProgramTM, String> tableProgramDetails;

    TProgramBO tProgramBO = BOFactory.getInstance().getBO(BOType.THERAPY_PROGRAMS);

    @Setter
    private TherapistController therapistController;

    @Setter
    private AppointmentsController appointmentsController;

    @FXML
    void selectBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (therapistController != null) {
            String ID = idLabel.getText();
            String Name = nameLabel.getText();
            therapistController.setDetails(ID, Name); // Pass data to TherapistController
        }
        if (appointmentsController != null) {
            String ID = idLabel.getText();
            String Name = nameLabel.getText();
            appointmentsController.setDetails(ID, Name);
        }
    }

    @FXML
    void tableAction(MouseEvent event) {
        TProgramTM selectedPatient = Table.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            idLabel.setText(selectedPatient.getTherapyID());
            nameLabel.setText(selectedPatient.getTherapyName());
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
}
