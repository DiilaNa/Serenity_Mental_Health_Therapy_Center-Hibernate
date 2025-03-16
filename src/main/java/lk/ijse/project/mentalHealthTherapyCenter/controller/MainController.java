package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private VBox adminVbox;

    @FXML
    private Button appointments;

    @FXML
    private Label dashBoardFrom;

    @FXML
    private Label dateDashBoard;

    @FXML
    private AnchorPane header;

    @FXML
    private ImageView image;

    @FXML
    private AnchorPane loadAnchor;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button patients;

    @FXML
    private Button payments;

    @FXML
    private Button signOutButton;

    @FXML
    private Button theaphy;

    @FXML
    private Button theraphist;

    @FXML
    private Button user;

    @FXML
    private VBox vbox;

    @FXML
    void appointmentsAction(MouseEvent event) {

    }

    @FXML
    void doctorDetailsAction(MouseEvent event) {

    }

    @FXML
    void patientsAction(MouseEvent event) {

    }

    @FXML
    void paymentsAction(MouseEvent event) {

    }

    @FXML
    void signOutButtonAction(MouseEvent event) {

    }

    @FXML
    void theraphyProgramAction(MouseEvent event) {

    }

    @FXML
    void userAction(MouseEvent event) {

    }

}
