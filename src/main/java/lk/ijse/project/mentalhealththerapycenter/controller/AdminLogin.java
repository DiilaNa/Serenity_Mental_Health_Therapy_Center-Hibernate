package lk.ijse.project.mentalhealththerapycenter.controller;

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
    void adminLoginAction(ActionEvent event) {
        if (adminUserName.getText().isEmpty() || adminPasswordPwField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter your username and password", ButtonType.OK).show();
        }else if (adminPasswordPwField.getText().equals(adminPasswordTextField.getText())) {
            new Alert(Alert.AlertType.ERROR, " Username and Passwords can not be same", ButtonType.OK).show();
        }else{
            String username = adminUserName.getText();
            String password = adminPasswordPwField.getText();

            /*send them to see if they exixts already*/
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
    private void refreshPage(){
        adminPasswordPwField.setVisible(true);
        adminPasswordTextField.setVisible(false);
    }

}
