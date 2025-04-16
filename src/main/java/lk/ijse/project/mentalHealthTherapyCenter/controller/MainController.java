package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.SessionHolder;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/HospitalIconMain.png"));
        image.setImage(image1);

        try {
           refreshPage();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Label dashBoardUserNAME;

    @FXML
    private Label dashboardTime;

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

    @Setter
    private String userName;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss  a");

    public void setUserRole(String role) {
        this.role = role;
        configureUI();
    }

    private void refreshPage(){
        updateDateTime();
        String s = SessionHolder.userName;
        dashBoardUserNAME.setText(s);
        navigateTo("/view/appointments.fxml");
        dashBoardFrom.setText("Appointment Details Form");
    }

    private void configureUI() {
        if ("admin".equals(role)) {
            adminVbox.setVisible(true);
        } else {
            adminVbox.setDisable(true);
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
        dashBoardFrom.setText("Patients Details Form");

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
        navigateTo("/view/TherapyPrograms.fxml");
        dashBoardFrom.setText("Therapy Programs Form");
    }

    @FXML
    void userAction(MouseEvent event) {
        try {
            loadAnchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/myProfile.fxml"));
            AnchorPane load = loader.load();
            MyProfile myProfileController = loader.getController();
            myProfileController.setUserName(userName);
            load.getStylesheets().add(getClass().getResource("/css/h.css").toExternalForm());
            load.prefWidthProperty().bind(loadAnchor.widthProperty());
            load.prefHeightProperty().bind(loadAnchor.heightProperty());
            loadAnchor.getChildren().add(load);
            dashBoardFrom.setText("Profile Settings");
         } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
         }
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
        Stage currentStage = (Stage) image.getScene().getWindow();
        currentStage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }
    private void updateDateTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    String currentDate = LocalDate.now().format(formatter);
                    dateDashBoard.setText(currentDate);
                    String currentTime = LocalTime.now().format(timeFormatter);
                    dashboardTime.setText(currentTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
