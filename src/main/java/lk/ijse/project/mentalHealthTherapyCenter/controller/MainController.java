package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/HospitalIconMain.png"));
        image.setImage(image1);
        navigateTo("/view/appointments.fxml");
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
    private Button therapist;

    @FXML
    private Button therapyPrograms;

    @FXML
    private Button user;

    @FXML
    private VBox vbox;

    private String role;

    public void setUserRole(String role) {
        this.role = role;
        configureUI();
    }

    private void configureUI() {
        if ("admin".equals(role)) {
            adminVbox.setVisible(true);
        } else {
            adminVbox.setVisible(false);
        }
    }

    @FXML
    void appointmentsAction(MouseEvent event) {
        navigateTo("/view/appointments.fxml");
        dashBoardFrom.setText("Appointment Details Form");
    }

    @FXML
    void doctorDetailsAction(MouseEvent event) {
        navigateTo("/view/Therapist.fxml");
        dashBoardFrom.setText("Therapist Details Form");
    }

    @FXML
    void patientsAction(MouseEvent event) {
        navigateTo("/view/patient.fxml");
        dashBoardFrom.setText("Patient Details Form");

    }

    @FXML
    void paymentsAction(MouseEvent event) {
        navigateTo("/view/payment.fxml");
        dashBoardFrom.setText("Payment Details Form");
    }

    @FXML
    void signOutButtonAction(MouseEvent event) throws IOException {
        loadPage("/view/login.fxml");
    }

    @FXML
    void therapyProgramAction(MouseEvent event) {

    }

    @FXML
    void userAction(MouseEvent event) {
       navigateTo("/view/myProfile.fxml");
       dashBoardFrom.setText("User Details Form");

    }
    public void navigateTo(String fxmlPath) {
        try {
            loadAnchor.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.getStylesheets().add(getClass().getResource("/css/h.css").toExternalForm());
            load.prefWidthProperty().bind(loadAnchor.widthProperty());
            load.prefHeightProperty().bind(loadAnchor.heightProperty());
            loadAnchor.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
    private void loadPage(String fxmlPath) throws IOException {
        Stage currentStage = (Stage) image.getScene().getWindow(); // Get current stage
        currentStage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }

}
