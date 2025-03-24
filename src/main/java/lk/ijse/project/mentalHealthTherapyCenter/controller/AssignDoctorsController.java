package lk.ijse.project.mentalHealthTherapyCenter.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssignDoctorsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/doctor.png"));
        image.setImage(adminIMage);
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

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    @FXML
    void loadComboBoxAction(ActionEvent event) throws Exception {
        try {
            String docName = null;
            List<DoctorDTO> h = therapistBO.getDocNames();
            System.out.println(h);
            for (DoctorDTO d : h) {
                 docName = d.getDoctorName();
                String docID = d.getDoctorID();
                String qualifications = d.getDoctorQualifications();
                String available = d.getDoctorAvailability().toString();
            }
            docComboBox.setValue(docName);
            docNameFromCombo.setText(docName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void selectBtnAction(ActionEvent event) {

    }

    @FXML
    void setDetailsCBoxAction(MouseEvent event) {

    }

}
