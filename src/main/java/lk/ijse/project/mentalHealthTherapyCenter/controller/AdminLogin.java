package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLogin implements Initializable {

    @FXML
    private CheckBox adminCheckBox;

    @FXML
    private Hyperlink adminClickHere;

    @FXML
    private Button adminLogin;

    @FXML
    private PasswordField adminPasswordPwField;

    @FXML
    private TextField adminPasswordTextField;

    @FXML
    private TextField adminUserName;

    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/user.png"));
        image.setImage(adminIMage);
        refreshPage();
    }

    @FXML
    void adminCheckBoxAction(ActionEvent event) {
        if (adminCheckBox.isSelected()) {
            adminPasswordPwField.setVisible(false);
            adminPasswordTextField.setVisible(true);
            adminPasswordTextField.setText(adminPasswordPwField.getText());
        }else {
            adminPasswordPwField.setVisible(true);
            adminPasswordTextField.setVisible(false);
            adminPasswordPwField.setText(adminPasswordTextField.getText());
        }
    }

    @FXML
    void adminLoginAction(ActionEvent event) throws IOException {
        if (adminUserName.getText().isEmpty() || adminPasswordPwField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter your username and password", ButtonType.OK).show();
        }else{
            String username = adminUserName.getText();
            String password = adminPasswordPwField.getText();

            /*send them to see if they exixts already*/
            navigateToMainPage("/view/MainLayout.fxml","admin");
        }
    }

    @FXML
    void clickHereAction(MouseEvent event) throws IOException {
        loadPage("/view/userRegister.fxml");
    }
    private void loadPage(String fxmlPath) throws IOException {
        Stage stage = (Stage) adminClickHere.getScene().getWindow(); // Get current stage
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));

        stage.setScene(scene);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }
    private void navigateToMainPage(String fxmlPath,String role) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            MainController controller = loader.getController();
            controller.setUserRole(role);

            Stage currentStage = (Stage) adminClickHere.getScene().getWindow();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("The Serenity Mental Health Therapy Center");
            currentStage.close();
            stage.show();
    }
    private void refreshPage(){
        adminPasswordPwField.setVisible(true);
        adminPasswordTextField.setVisible(false);
    }

}
