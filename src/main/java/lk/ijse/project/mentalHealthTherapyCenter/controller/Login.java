package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/LoginAdminPic.png"));
        admin.setImage(adminIMage);
        Image userImage = new Image(getClass().getResourceAsStream("/images/LoginUserPic.png"));
        user.setImage(userImage);
    }

    @FXML
    private ImageView admin;

    @FXML
    private ImageView user;

    @FXML
    private Label text;

    @FXML
    void adminAction(MouseEvent event) throws IOException {
        loadPage("/view/adminLogin.fxml");
    }

    @FXML
    void userAction(MouseEvent event) throws IOException {
        loadPage("/view/userLogin.fxml");
    }

    private void loadPage(String fxmlPath) throws IOException {
        Stage stage = (Stage) admin.getScene().getWindow(); // Get current stage
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        stage.setScene(scene);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }
}
