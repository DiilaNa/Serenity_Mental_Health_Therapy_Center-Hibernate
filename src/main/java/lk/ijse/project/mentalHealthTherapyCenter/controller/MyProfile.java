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
import javafx.scene.shape.Circle;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class MyProfile implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/SettingInMyProfile.png"));
        image.setImage(image1);

        if (UserName != null) { // Check if the username is set before accessing it
            topicUserNameLabel.setText(UserName);
        } else {
            System.out.println("UserName is null in MyProfile");
        }
    }
    @FXML
    private ImageView image;

    @FXML
    private Label email;

    @FXML
    private Label fulllNane;


    @FXML
    private Label role;

    @FXML
    private Button setPic;

    @FXML
    private Label topicUserNameLabel;

    @FXML
    private Circle picCircle;

    private String UserName;

    public void setUserName(String userName) {
        this.UserName = userName;

        if (topicUserNameLabel != null) {
            topicUserNameLabel.setText(userName);
        }else {
            System.out.println(UserName+" is null");
        }
    }

    private void loadText(){

    }

    @FXML
    void setPicAction(ActionEvent event) {

    }


}
