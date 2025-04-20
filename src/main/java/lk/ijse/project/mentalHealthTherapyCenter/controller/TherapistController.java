
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
import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.TherapistTM;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TherapistController  implements Initializable {

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
    private TableColumn<TherapistTM, String> tableQualifications;

    @FXML
    private Button update;

    @FXML
    private Button viewActivities;

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    @FXML
    void viewActivitiesBtnAction(ActionEvent event) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Connection connection = session.doReturningWork(con -> con);

            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/JasperReports/TherapistStatistics.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @FXML
    void TableAction(MouseEvent event) {
        TherapistTM selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            docIDlabel.setText(selectedPatient.getDoctorID());
            docName.setText(selectedPatient.getDoctorName());
            docQualificationsCombo.setValue(selectedPatient.getDoctorQualifications());
            docAvailableCombo.setValue(selectedPatient.getDoctorAvailability());
            docContact.setText(selectedPatient.getDoctorPhone());
            docMail.setText(selectedPatient.getDoctorEmail());
        }
    }


    @FXML
    void deleteBtnAction(ActionEvent event) throws Exception {
        String patientID = docIDlabel.getText();
        boolean isDeleted = therapistBO.deleteTherapist(patientID);
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Deletion Failed").show();
        }
    }

    @FXML
    void resetBtnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void saveBtnAction(ActionEvent event) throws Exception {
        String DoctorID = docIDlabel.getText();
        String DocName = docName.getText();
        String DocQualifications = docQualificationsCombo.getSelectionModel().getSelectedItem();
        String DocAvailability = docAvailableCombo.getSelectionModel().getSelectedItem();
        String DocPhone = docContact.getText();
        String DocMail = docMail.getText();

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
            DoctorDTO doctorDTO = new DoctorDTO(
                    DoctorID,
                    DocName,
                    DocQualifications,
                    DocAvailability,
                    DocPhone,
                    DocMail
            );
            boolean isSaved = therapistBO.saveTherapist(doctorDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Therapist Saved",ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Saving Failed",ButtonType.OK).show();
            }
    }

    @FXML
    void updateBtnAction(ActionEvent event) throws Exception {
        String DoctorID = docIDlabel.getText();
        String DocName = docName.getText();
        String DocQualifications = docQualificationsCombo.getSelectionModel().getSelectedItem();
        String DocAvailability = docAvailableCombo.getSelectionModel().getSelectedItem();
        String DocPhone = docContact.getText();
        String DocMail = docMail.getText();

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
        DoctorDTO doctorDTO = new DoctorDTO(
                DoctorID,
                DocName,
                DocQualifications,
                DocAvailability,
                DocPhone,
                DocMail
        );
        boolean isSaved = therapistBO.updateTherapist(doctorDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Therapist Saved",ButtonType.OK).show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Saving Failed",ButtonType.OK).show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/doctor.png"));
        image.setImage(image1);

        tableId.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
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

    private void loadTable() throws Exception {
        List<DoctorDTO> doctorDTOS =  therapistBO.getALLDoctors();
        if (doctorDTOS == null || doctorDTOS.isEmpty()) {
            System.out.println("No data available");
        }
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();
        for (DoctorDTO doctorDTO : doctorDTOS) {
            TherapistTM therapistTM = new TherapistTM(
                    doctorDTO.getDoctorID(),
                    doctorDTO.getDoctorName(),
                    doctorDTO.getDoctorQualifications(),
                    doctorDTO.getDoctorAvailability(),
                    doctorDTO.getDoctorPhone(),
                    doctorDTO.getDoctorEmail()
            );
            therapistTMS.add(therapistTM);
        }
        table.setItems(therapistTMS);
    }
    private void refreshPage() throws Exception {
        loadTable();
        docIDlabel.setText(therapistBO.getNextTherapyID());
        docAvailableCombo.getItems().clear();
        docQualificationsCombo.getItems().clear();
        docAvailableCombo.setItems(FXCollections.observableArrayList("Available","Not Available"));
        docQualificationsCombo.setItems(FXCollections.observableArrayList("Bsc","Msc","Phd"));
        docName.clear();
        docContact.clear();
        docMail.clear();
    }
}

