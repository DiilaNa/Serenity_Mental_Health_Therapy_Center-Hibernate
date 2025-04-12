package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAppointments implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }

    @FXML
    private ComboBox<String> ComboDocId;

    @FXML
    private ComboBox<String> ComboProgramID;

    @FXML
    private TableView<?> Table;

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
    private TableColumn<?, ?> tableDocID;

    @FXML
    private TableColumn<?, ?> tablePatientName;

    @FXML
    private TableColumn<?, ?> tablePaymentAmount;

    @FXML
    private TableColumn<?, ?> tablePaymentID;

    @FXML
    private TableColumn<?, ?> tablePaymentMethod;

    @FXML
    private TableColumn<?, ?> tableProgramID;

    @FXML
    private TableColumn<?, ?> tableSessionDate;

    @FXML
    private TableColumn<?, ?> tableSessionID;

    @FXML
    private TableColumn<?, ?> tableSessionNotes;

    @FXML
    private TableColumn<?, ?> tableSessionStatus;

    @FXML
    private TableColumn<?, ?> tableSessionTime;

    @FXML
    private TextField textSessionDate;

    @FXML
    private TextField textSessionTime;

    @FXML
    private TextField txtAppointmentStatus;

    @FXML
    private TextField txtPaymentAmount;

    @FXML
    private TextField txtSessionNotes;

    @FXML
    void cancelBTNAction(ActionEvent event) {

    }

    @FXML
    void rescheduleBTNAction(ActionEvent event) {

    }

    @FXML
    void resetBTNAction(ActionEvent event) {

    }

    @FXML
    void tableAction(MouseEvent event) {

    }

}
