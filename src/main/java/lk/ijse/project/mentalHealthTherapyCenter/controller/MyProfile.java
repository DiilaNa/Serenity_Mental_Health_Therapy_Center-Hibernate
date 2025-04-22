package lk.ijse.project.mentalHealthTherapyCenter.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.PasswordUtil;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.SessionHolder;
import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyProfile implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/SettingInMyProfile.png"));
        image.setImage(image1);
        try {
            loadBTN();
            refreshPage();
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
    private Label fullName;

    @FXML
    private Label role;

    @FXML
    private Button setPic;

    @FXML
    private Label topicUserNameLabel;

    @FXML
    private Circle picCircle;

    private String UserName;

    @FXML
    private PasswordField passwordConfirmPWField1;

    @FXML
    private Button addNewUser;

    @FXML
    private PasswordField passwordConfirmPWField2;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private TextField txtPassWord1;

    @FXML
    private TextField txtPassWord2;

    @FXML
    private TextField txtUserFUllName;

    @FXML
    private TextField txtUserMail;

    @FXML
    private TextField txtUserName;

    @FXML
    private Button updatePassword;

    @FXML
    private Button updateProfile;

    @FXML
    private Label userName;

    @FXML
    private Label IDLabel;

    @FXML
    private Button clear1;

    @FXML
    private Button clear2;

    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    @FXML
    void showPasswordCheckBox(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            passwordConfirmPWField1.setVisible(false);
            passwordConfirmPWField2.setVisible(false);
            txtPassWord1.setVisible(true);
            txtPassWord2.setVisible(true);
            txtPassWord1.setText(passwordConfirmPWField1.getText());
            txtPassWord2.setText(passwordConfirmPWField2.getText());
        }else {
            passwordConfirmPWField1.setVisible(true);
            passwordConfirmPWField2.setVisible(true);
            txtPassWord1.setVisible(false);
            txtPassWord2.setVisible(false);
            passwordConfirmPWField1.setText(txtPassWord1.getText());
            passwordConfirmPWField2.setText(txtPassWord2.getText());
        }
    }

    @FXML
    void updateDetailsAction(ActionEvent event) {
        String UserName = txtUserFUllName.getText();
        String email = txtUserMail.getText();

        String id = IDLabel.getText();

        UserDTO userDTO = new UserDTO();
            userDTO.setUserID(id);
            userDTO.setUserFullName(UserName);
            userDTO.setUserEmail(email);

            boolean isUpdated = userBO.update(userDTO);
           if (isUpdated) {
               refreshPage();
               new Alert(Alert.AlertType.INFORMATION, "User updated", ButtonType.OK).show();
           }else {
               new Alert(Alert.AlertType.ERROR, "User not updated", ButtonType.OK).show();
           }
    }

    @FXML
    void updatePassWordAction(ActionEvent event) {
        String UserName = txtUserName.getText();
        String id = IDLabel.getText();

        if (!txtPassWord1.getText().equals(txtPassWord2.getText())) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.OK).show();
        }
        String pass = txtPassWord1.getText();
        String passWord = PasswordUtil.hashPassword(pass);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(id);
        userDTO.setUserName(UserName);
        userDTO.setUserPassword(passWord);

        boolean isUpdated = userBO.updatePassWord(userDTO);

        if (isUpdated) {
            String uName = txtUserName.getText();
            SessionHolder.userName = uName;
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "User updated", ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.ERROR, "User not updated", ButtonType.OK).show();
        }
    }

    @FXML
    void setPicAction(ActionEvent event) {

    }

    @FXML
    void clearAction1(ActionEvent event) {
        txtUserFUllName.clear();
        txtUserMail.clear();
    }

    @FXML
    void clearAction2(ActionEvent event) {
        txtUserName.clear();
        txtPassWord1.clear();
        txtPassWord2.clear();
        passwordConfirmPWField1.clear();
        passwordConfirmPWField2.clear();
    }
    @FXML
    void addNewUserAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userRegister.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Doctor Details - Serenity Mental Health Therapy Center");
        scene.getStylesheets().add(getClass().getResource("/css/h.css").toExternalForm());
        stage.show();
    }
    private void refreshPage(){
        loadText();
        txtPassWord1.setVisible(false);
        txtPassWord2.setVisible(false);
        passwordConfirmPWField1.setVisible(true);
        passwordConfirmPWField2.setVisible(true);
        txtUserName.clear();
        txtUserFUllName.clear();
        txtUserMail.clear();
        txtPassWord1.clear();
        txtPassWord2.clear();
        passwordConfirmPWField1.clear();
        passwordConfirmPWField2.clear();
    }

    private void loadText(){
        String searchUserName = SessionHolder.userName;
        topicUserNameLabel.setText(searchUserName);
        List<UserDTO> users = userBO.getUserDetails(searchUserName);
        for (UserDTO userDTO : users) {
            fullName.setText(userDTO.getUserFullName());
            email.setText(userDTO.getUserEmail());
            role.setText(userDTO.getUserRole());
            userName.setText(userDTO.getUserName());
            IDLabel.setText(userDTO.getUserID());
        }
    }

    public void setUserName(String userName) {
        this.UserName = userName;

        if (topicUserNameLabel != null) {
            topicUserNameLabel.setText(userName);
            SessionHolder.userName = userName;
        }else {
            System.out.println(UserName+" is null");
        }
    }

    private void loadBTN(){
        String role = SessionHolder.currentRole;
        if (role == null || role.equalsIgnoreCase("Admin")) {
            addNewUser.setDisable(false);
        }else {
            addNewUser.setDisable(true);
        }
    }
}
