package lk.ijse.project.mentalHealthTherapyCenter.controller.Login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
        loadPage("/view/adminLogin.fxml","admin");
    }

    @FXML
    void userAction(MouseEvent event) throws IOException {
        loadPage("/view/userLogin.fxml","user");
    }

    private void loadPage(String fxmlPath,String role) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        if (role.equals("admin")) {
            AdminLogin controller = loader.getController();
            controller.setRole(role);
        } else if (role.equals("user")) {
            UserLogin controller = loader.getController();
            controller.setRole(role);
        }
        Stage stage = (Stage) admin.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }
}
