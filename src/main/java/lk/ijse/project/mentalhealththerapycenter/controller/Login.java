package lk.ijse.project.mentalhealththerapycenter.controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    private ComboBox<String> selectUser;

    @FXML
    private TextField userName;

    @FXML
    void clickhereAction(MouseEvent event) throws IOException {
        loadPage("/view/register.fxml");
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
    }

    @FXML
    void selectUserAction(ActionEvent event) {

    }
    @FXML
    void showPasswordcheckBox(ActionEvent event) {
        if (showPasswordcheckBox.isSelected()) {
            passwordPWField.setVisible(false);
            passwordTextField.setVisible(true);
        }else {
            passwordPWField.setVisible(true);
            passwordTextField.setVisible(false);
        }
    }
    private void loadPage(String fxmlPath) throws IOException {
        Stage stage = (Stage) clickhere.getScene().getWindow(); // Get current stage
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));

        stage.setScene(scene);
        stage.setTitle("Mental Hospital");
        stage.show();
    }
    private void refreshPage(){
        passwordPWField.setVisible(true);
        passwordTextField.setVisible(false);
        selectUser.setItems(FXCollections.observableArrayList("admin","user"));
    }

}
