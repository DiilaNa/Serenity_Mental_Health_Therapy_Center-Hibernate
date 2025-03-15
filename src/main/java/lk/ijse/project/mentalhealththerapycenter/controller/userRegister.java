package lk.ijse.project.mentalhealththerapycenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class userRegister implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/addUser.png"));
        image.setImage(adminIMage);
        refreshPage();
    }

    @FXML
    private Hyperlink clickhere;

    @FXML
    private PasswordField passwordConfirmPWField;

    @FXML
    private TextField passwordConfirmTextField;

    @FXML
    private PasswordField passwordPWField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private CheckBox showPasswordcheckBox;

    @FXML
    private Button signup;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userName;

    @FXML
    private ComboBox<String> userRole;

    @FXML
    private ImageView image;

    @FXML
    void clickhereAction(MouseEvent event) {

    }

    @FXML
    void showPasswordcheckBox(ActionEvent event) {
        if (showPasswordcheckBox.isSelected()) {
            passwordPWField.setVisible(false);
            passwordConfirmPWField.setVisible(false);
            passwordTextField.setVisible(true);
            passwordConfirmTextField.setVisible(true);
            passwordTextField.setText(passwordPWField.getText());
            passwordConfirmTextField.setText(passwordConfirmPWField.getText());
        }else {
            passwordPWField.setVisible(true);
            passwordConfirmPWField.setVisible(true);
            passwordTextField.setVisible(false);
            passwordConfirmTextField.setVisible(false);
            passwordPWField.setText(passwordTextField.getText());
            passwordConfirmPWField.setText(passwordConfirmTextField.getText());
        }
    }

    @FXML
    void signupAction(ActionEvent event) {

    }
    private void refreshPage() {
        passwordPWField.setVisible(true);
        passwordConfirmPWField.setVisible(true);
        passwordTextField.setVisible(false);
        passwordConfirmTextField.setVisible(false);

        userRole.setItems(FXCollections.observableArrayList("USER", "ADMIN"));

    }

}
