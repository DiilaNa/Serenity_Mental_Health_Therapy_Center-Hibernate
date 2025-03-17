package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PatientController {

    @FXML
    private Button delete;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> tablePAddress;

    @FXML
    private TableColumn<?, ?> tablePContact;

    @FXML
    private TableColumn<?, ?> tablePDob;

    @FXML
    private TableColumn<?, ?> tablePEmail;

    @FXML
    private TableColumn<?, ?> tablePGender;

    @FXML
    private TableColumn<?, ?> tablePId;

    @FXML
    private TableColumn<?, ?> tablePName;

    @FXML
    private TableColumn<?, ?> tablePNic;

    @FXML
    private Button update;

    @FXML
    void deleteAction(ActionEvent event) {

    }

    @FXML
    void tableAction(MouseEvent event) {

    }

    @FXML
    void updateAction(ActionEvent event) {

    }

}
