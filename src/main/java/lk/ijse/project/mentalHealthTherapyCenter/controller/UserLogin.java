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
    private Hyperlink forgetPass;

    @FXML
    void clickhereAction(MouseEvent event) throws IOException {
        loadPage("/view/userRegister.fxml");
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        String username = userName.getText();
        String password = passwordPWField.getText();
        String passText = passwordTextField.getText();

        if (username.isEmpty() || password.isEmpty() || passText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter your username and password", ButtonType.OK).show();
            return;
        }
        if (!password.equals(passText)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.OK).show();
            return;
        }
            /*send them to see if they exixts already*/
            navigateToMainPage("/view/MainLayout.fxml","user");
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

    @FXML
    void forgetPassAction(MouseEvent event) throws IOException {
        loadNewPage("/view/forgetPassword.fxml");
    }
    private void refreshPage(){
        passwordPWField.setVisible(true);
        passwordTextField.setVisible(false);
    }
    private  void  loadNewPage(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Change Password - Serenity Mental Health Therapy Center");
        stage.show();

    }
    private void loadPage(String fxmlPath) throws IOException {
        Stage stage = (Stage) clickhere.getScene().getWindow(); // Get current stage
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }
    private void navigateToMainPage(String fxmlPath,String role) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());

        MainController controller = loader.getController();
        controller.setUserRole(role);

        Stage currentStage = (Stage) clickhere.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        currentStage.close();
        stage.show();
    }

}
