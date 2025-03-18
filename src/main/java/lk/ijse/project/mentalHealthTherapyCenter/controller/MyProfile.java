package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MyProfile implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/SettingInMyProfile.png"));
        image.setImage(image1);
    }
    @FXML
    private ImageView image;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Label email;

    @FXML
    private Label fulllNane;

    @FXML
    private Label gender;

    @FXML
    private Label role;

    @FXML
    private Button setPic;

    @FXML
    private Label topicUserNameLabel;

    @FXML
    private Button update;

    @FXML
    private TextField userFullName;

    @FXML
    private TextField userMail;

    @FXML
    private Label userName;

    @FXML
    private PasswordField userPassWordField1;

    @FXML
    private PasswordField userPassWordField2;

    @FXML
    private TextField userPasswordText1;

    @FXML
    private TextField userPasswordText2;

    @FXML
    private ComboBox<?> userRole;

    @FXML
    private TextField userUserName;

    @FXML
    void setPicAction(ActionEvent event) {

    }

    @FXML
    void updateAction(ActionEvent event) {

    }

}
