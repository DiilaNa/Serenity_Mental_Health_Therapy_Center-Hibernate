package lk.ijse.project.mentalHealthTherapyCenter.controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssignDoctorsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/doctor.png"));
        image.setImage(adminIMage);

        try{
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load", ButtonType.OK).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label docAvailable;

    @FXML
    private Circle circleD;

    @FXML
    private ComboBox<String> docComboBox;

    @FXML
    private Label docIdFromCombo;

    @FXML
    private Label docNameFromCombo;

    @FXML
    private Label docQualificationsFromCombo;

    @FXML
    private ImageView image;

    @FXML
    private Button select;

    @Setter
    private AppointmentsController appointmentsController;

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    private void refreshPage() throws Exception {
        loadComboBoxAction(null);
    }

    @FXML
    void loadComboBoxAction(ActionEvent event) throws Exception {
        try {
            List<DoctorDTO> doctors = therapistBO.getDocNames();

            if (doctors == null || doctors.isEmpty()) {
                return;
            }
            ObservableList<String> doctorNames = FXCollections.observableArrayList();
            for (DoctorDTO d : doctors) {
                doctorNames.add(d.getDoctorName());
            }
            if (docComboBox.getItems() == null || !docComboBox.getItems().equals(doctorNames)) {
                Platform.runLater(() -> docComboBox.setItems(doctorNames));
            }
            // Add listener to ComboBox to update labels when a doctor is selected
            docComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    // Find the corresponding DoctorDTO for the selected name
                    DoctorDTO selectedDoctor = findDoctorByName(doctors, newValue);

                    // Update the labels with the doctor information
                    docIdFromCombo.setText("Doctor ID: " + selectedDoctor.getDoctorID());
                    docNameFromCombo.setText("Doctor Name: " + selectedDoctor.getDoctorName());
                    docQualificationsFromCombo.setText("Qualifications: " + selectedDoctor.getDoctorQualifications());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DoctorDTO findDoctorByName(List<DoctorDTO> doctors, String name) {
        for (DoctorDTO doctor : doctors) {
            if (doctor.getDoctorName().equals(name)) {
                return doctor;
            }
        }
        return null; // Return null if doctor is not found
    }

    @FXML
    void selectBtnAction(ActionEvent event) {
        if (appointmentsController != null) {
            String ID = docIdFromCombo.getText();
            String Name = docNameFromCombo.getText();
            appointmentsController.setAddDoctors(ID, Name);
        }
    }

    @FXML
    void setDetailsCBoxAction(MouseEvent event) {

    }

}
