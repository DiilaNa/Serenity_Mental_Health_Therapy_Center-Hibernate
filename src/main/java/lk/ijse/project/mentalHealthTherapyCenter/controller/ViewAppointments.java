package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.project.mentalHealthTherapyCenter.controller.popups.SelectProgramsController;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.ViewSessionTM;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ViewSessionDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
    private TextField txtPaymentAmount;

    @FXML
    private TextField txtSessionNotes;

    @FXML
    private Button addProgramsBTN;

    @FXML
    private ListView<String> listView;

    private Set<String> programIDs = new HashSet<>();

    public void setDetails(String programID, String programName) {
        programID = programID;
        if (programID != null && programName != null) {
            listView.getItems().add(programID);
        }
    }

    AppointmentBO appointmentBO =  BOFactory.getInstance().getBO(BOType.APPOINTMENT);

    @FXML
    void cancelBTNAction(ActionEvent event) {

    }

    @FXML
    void rescheduleBTNAction(ActionEvent event) {
        String patientName = comboPatientName.getValue();
        String paymentMethod = comboPaymentMethod.getValue();

    }

    @FXML
    void resetBTNAction(ActionEvent event) throws Exception {
        labelPaymentID.setVisible(false);
        labelSessionID.setVisible(false);
        textSessionDate.clear();
        textSessionTime.clear();
        txtPaymentAmount.clear();
        txtSessionNotes.clear();
        comboPatientName.getItems().clear();
        comboPaymentMethod.setItems(FXCollections.observableArrayList("Card Payment","Cash Payment"));
        ComboDocId.getItems().clear();
        listView.getItems().clear();
    }

    @FXML
    void tableAction(MouseEvent event) {
        ViewSessionTM selectedItem = Table.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            labelPaymentID.setVisible(true);
            labelSessionID.setVisible(true);
            labelSessionID.setText(selectedItem.getSessionID());
            textSessionDate.setText(selectedItem.getSessionDate());
            txtSessionNotes.setText(selectedItem.getSessionNotes());
            textSessionTime.setText(selectedItem.getSessionTime());
            ComboDocId.setValue(selectedItem.getDoctorID());
            listView.setItems( FXCollections.observableArrayList(selectedItem.getProgramID()));
            comboPatientName.setValue(selectedItem.getPatientName());
            labelPaymentID.setText(String.valueOf(selectedItem.getPaymentID()));
            txtPaymentAmount.setText(String.valueOf(selectedItem.getPaymentAmount()));
            comboPaymentMethod.setValue(selectedItem.getPaymentMethod());
        }
    }

    @FXML
    void addProgramsAction(MouseEvent event) throws IOException { loadNewPage("/view/SelectPrograms.fxml");}

    private void loadTable(){
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
        labelPaymentID.setVisible(false);
        labelSessionID.setVisible(false);
        textSessionDate.clear();
        textSessionTime.clear();
        txtPaymentAmount.clear();
        txtSessionNotes.clear();
        comboPatientName.setItems(FXCollections.observableArrayList(appointmentBO.loadPatientNames()));
        comboPaymentMethod.setItems(FXCollections.observableArrayList("Card Payment","Cash Payment"));
        ComboDocId.setItems(FXCollections.observableArrayList(appointmentBO.loadDoctorIds()));
    }
    private void loadNewPage(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        if (fxmlPath.equals("/view/SelectPrograms.fxml")) {
            SelectProgramsController selectProgramsController = loader.getController();
            selectProgramsController.setViewAppointments(this);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Program Details - Serenity Mental Health Therapy Center");
        stage.show();
    }
}
