package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.ViewSessionTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ViewSessionDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAppointments implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableSessionID.setCellValueFactory(new PropertyValueFactory<>("sessionID"));
        tableSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        tableSessionNotes.setCellValueFactory(new PropertyValueFactory<>("sessionNotes"));
        tableSessionTime.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
        tableDocID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        tableProgramID.setCellValueFactory(new PropertyValueFactory<>("programID"));
        tablePatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        tablePaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        tablePaymentAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        tablePaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        tableSessionStatus.setCellValueFactory(new PropertyValueFactory<>("appointmentStatus"));

        try{
            refreshPage();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to Load Page SQL ERROR").showAndWait();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to Load Page ClassNotFound").showAndWait();
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private ComboBox<String> ComboDocId;

    @FXML
    private ComboBox<String> ComboProgramID;

    @FXML
    private TableView<ViewSessionTM> Table;

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
    private TableColumn<String,ViewSessionTM> tableDocID;

    @FXML
    private TableColumn<String,ViewSessionTM> tablePatientName;

    @FXML
    private TableColumn<Double,ViewSessionTM> tablePaymentAmount;

    @FXML
    private TableColumn<String,ViewSessionTM> tablePaymentID;

    @FXML
    private TableColumn<String,ViewSessionTM> tablePaymentMethod;

    @FXML
    private TableColumn<String,ViewSessionTM> tableProgramID;

    @FXML
    private TableColumn<Date,ViewSessionTM> tableSessionDate;

    @FXML
    private TableColumn<String,ViewSessionTM> tableSessionID;

    @FXML
    private TableColumn<String,ViewSessionTM> tableSessionNotes;

    @FXML
    private TableColumn<String,ViewSessionTM> tableSessionStatus;

    @FXML
    private TableColumn<String,ViewSessionTM > tableSessionTime;

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

    AppointmentBO appointmentBO =  BOFactory.getInstance().getBO(BOType.APPOINTMENT);

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
    private void loadTable() throws Exception {
        List<ViewSessionDTO> viewSessionDTOS =  appointmentBO.getAllAppointments();
        ObservableList<ViewSessionTM> viewSessionTMS = FXCollections.observableArrayList();
        for (ViewSessionDTO viewSessionDTO : viewSessionDTOS) {

            ViewSessionTM viewSessionTM = new ViewSessionTM(
                    viewSessionDTO.getSessionID(),
                    viewSessionDTO.getSessionDate(),
                    viewSessionDTO.getSessionNotes(),
                    viewSessionDTO.getSessionTime(),
                    viewSessionDTO.getDoctorID(),
                    viewSessionDTO.getPrograms(),
                    viewSessionDTO.getPatientName(),
                    viewSessionDTO.getPaymentID(),
                    viewSessionDTO.getPaymentAmount(),
                    viewSessionDTO.getPaymentMethod(),
                    viewSessionDTO.getAppointmentStatus()
            );
            viewSessionTMS.add(viewSessionTM);
        }
        Table.setItems(viewSessionTMS);
    }

    private void refreshPage() throws Exception {
        loadTable();

    }

}
