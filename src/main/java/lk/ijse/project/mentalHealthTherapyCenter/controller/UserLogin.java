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
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLogin implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/user.png"));
        image.setImage(adminIMage);
        refreshPage();
    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Hyperlink clickhere;

    @FXML
    private CheckBox showPasswordcheckBox;

    @FXML
    private Button login;

    @FXML
    private PasswordField passwordPWField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField userName;

    @FXML
    private ImageView image;

    @FXML
    void clickhereAction(MouseEvent event) throws IOException {
        loadPage("/view/userRegister.fxml");
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        if (userName.getText().isEmpty() || passwordPWField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter your username and password", ButtonType.OK).show();
        }else if (passwordPWField.getText().equals(passwordTextField.getText())) {
            new Alert(Alert.AlertType.ERROR, " Username and Passwords can not be same", ButtonType.OK).show();
        }else{
            String username = userName.getText();
            String password = passwordPWField.getText();

            /*send them to see if they exixts already*/
        }
    }

    @FXML
    void showPasswordcheckBox(ActionEvent event) {
        if (showPasswordcheckBox.isSelected()) {
            passwordPWField.setVisible(false);
            passwordTextField.setVisible(true);
            passwordTextField.setText(passwordPWField.getText());
        }else {
            passwordPWField.setVisible(true);
            passwordTextField.setVisible(false);
            passwordPWField.setText(passwordTextField.getText());
        }
    }
    private void loadPage(String fxmlPath) throws IOException {
        Stage stage = (Stage) clickhere.getScene().getWindow(); // Get current stage
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        stage.setScene(scene);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }
    private void refreshPage(){
        passwordPWField.setVisible(true);
        passwordTextField.setVisible(false);
    }

}
