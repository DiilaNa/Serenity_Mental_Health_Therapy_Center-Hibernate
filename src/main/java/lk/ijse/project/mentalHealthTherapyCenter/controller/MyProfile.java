package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.SessionHolder;
import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyProfile implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/SettingInMyProfile.png"));
        image.setImage(image1);
        try {
            loadText();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
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

    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    public void setUserName(String userName) {
        this.UserName = userName;

        if (topicUserNameLabel != null) {
            topicUserNameLabel.setText(userName);
            SessionHolder.userName = userName;
        }else {
            System.out.println(UserName+" is null");
        }
    }

    private void loadText(){
        String searchUserName = SessionHolder.userName;
        System.out.println(searchUserName);
        List<UserDTO> users = userBO.getUserDetails(searchUserName);
        for (UserDTO userDTO : users) {
            fulllNane.setText(userDTO.getUserFullName());
            email.setText(userDTO.getUserEmail());
            role.setText(userDTO.getUserRole());
        }
    }

    @FXML
    void setPicAction(ActionEvent event) {

    }
}
