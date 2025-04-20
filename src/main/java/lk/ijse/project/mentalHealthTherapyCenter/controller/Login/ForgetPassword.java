package lk.ijse.project.mentalHealthTherapyCenter.controller.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.MailUtil;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.PasswordUtil;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.SessionHolder;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPassword implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image passwordIcon = new Image(getClass().getResourceAsStream("/images/password.png"));
        image.setImage(passwordIcon);
        Image backImage = new Image(getClass().getResourceAsStream("/images/back-arrow.png"));
        back.setImage(backImage);

        passwordFieldOne.setVisible(true);
        passwordFieldTwo.setVisible(true);

        SessionHolder.currentRole = role;
    }

    @FXML
    private TextField Email;

    @FXML
    private ImageView back;

    @FXML
    private Button change;

    @FXML
    private TextField confirmPassword;

    @FXML
    private ImageView image;

    @FXML
    private TextField passWordText;

    @FXML
    private PasswordField passwordFieldOne;

    @FXML
    private PasswordField passwordFieldTwo;

    @FXML
    private CheckBox showPassWord;

    @FXML
    private TextField userName;

    @Setter
    private String role;

    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    @FXML
    void changeAction(ActionEvent event) {
        String UserName = userName.getText();
        String PassWord = passWordText.getText();
        String ConfirmPassword = confirmPassword.getText();
        String PasswordPWT1  = passwordFieldOne.getText();
        String PasswordPWT2  = passwordFieldTwo.getText();

        String email = Email.getText();

        String mailPattern =  "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        boolean isValidMailPattern = email.matches(mailPattern);

        String passwordPattern =  "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$";
        boolean isValidPasswordPattern = PassWord.matches(passwordPattern);

        if (!isValidPasswordPattern){
            new Alert(Alert.AlertType.INFORMATION, "Password Requirements:\n" +
                    "✔ Minimum 8 characters\n" +
                    "✔ At least one uppercase letter (A-Z)\n" +
                    "✔ At least one lowercase letter (a-z)\n" +
                    "✔ At least one digit (0-9)\n" +
                    "✔ At least one special character (@, #, $, %, etc.)",

                    ButtonType.OK).show();
        }

        if (!isValidMailPattern) {
            Email.setStyle(Email.getStyle() + "-fx-border-color: red;");
        }
        if (UserName.isEmpty() ||PassWord.isEmpty() || ConfirmPassword.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "All fields are required!", ButtonType.OK).show();
            return;
        }

        if (!PassWord.equals(ConfirmPassword) || !PasswordPWT1.equals(PasswordPWT2)) {
            new Alert(Alert.AlertType.ERROR, "Password Do not Match", ButtonType.OK).show();
            return;
        }

        /*Hashed New Password*/
        String pass = null;
        if (passwordFieldOne.getText().equals(confirmPassword.getText())) {
             pass = PasswordUtil.hashPassword(PassWord);
        }

        if (isValidMailPattern && isValidPasswordPattern) {
            boolean isSaved = userBO.updateUser(UserName, email, pass);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, " Password changed SuccessFully", ButtonType.OK).show();
                SessionHolder.currentRole = role;
                MailUtil.sendEmailWithGmail(email,UserName);
            }else {
                new Alert(Alert.AlertType.ERROR, "Operation Failed", ButtonType.OK).show();
            }
        }

    }

    @FXML
    void showPassWordAction(ActionEvent event) {
        if (showPassWord.isSelected()) {
            passwordFieldOne.setVisible(false);
            passwordFieldTwo.setVisible(false);
            passWordText.setVisible(true);
            confirmPassword.setVisible(true);
            passWordText.setText(passwordFieldOne.getText());
            confirmPassword.setText(passwordFieldTwo.getText());
        }else {
            passwordFieldOne.setVisible(true);
            passwordFieldTwo.setVisible(true);
            passWordText.setVisible(false);
            confirmPassword.setVisible(false);
            passwordFieldOne.setText(passWordText.getText());
            passwordFieldTwo.setText(confirmPassword.getText());
        }
    }
    @FXML
    void backIdAction(MouseEvent event) {

    }
}
