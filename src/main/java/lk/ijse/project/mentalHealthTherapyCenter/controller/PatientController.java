package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/patient.png"));
        image.setImage(image1);
    }

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
    private ImageView image;

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
