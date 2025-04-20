package lk.ijse.project.mentalHealthTherapyCenter.controller.Login;

import javafx.collections.FXCollections;
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
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.PasswordUtil;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.SessionHolder;
import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserRegister implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/addUser.png"));
        image.setImage(adminIMage);
        refreshPage();
    }
    @FXML
    private Label userId;

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
    private TextField userFUllName;

    @FXML
    private TextField userName;

    @FXML
    private ComboBox<String> userRole;

    @FXML
    private ImageView image;

    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

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
    void signupAction(ActionEvent event) throws IOException {
        String userID = userId.getText();
        String fullName = userFUllName.getText();
        String email = userEmail.getText();
        String role = userRole.getValue();
        String userNAME = userName.getText();
        String password = passwordPWField.isVisible() ? passwordPWField.getText() : passwordTextField.getText();
        String confirmPassword = passwordConfirmPWField.isVisible() ? passwordConfirmPWField.getText() : passwordConfirmTextField.getText();

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match", ButtonType.OK).show();
            return;
        }


        String mailPattern =  "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        boolean isValidMailPattern = email.matches(mailPattern);

        String passwordPattern =  "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$";
        boolean isValidPasswordPattern = password.matches(passwordPattern);

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
            userEmail.setStyle(userEmail.getStyle() + "-fx-border-color: red;");
        }
        if (userID.isEmpty() ||fullName.isEmpty() || email.isEmpty() || role.isEmpty() || userNAME.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required!", ButtonType.OK).show();
            return;
        }

        /*Encrypt Password*/
        String hashPassword = PasswordUtil.hashPassword(password);


        if (isValidMailPattern && isValidPasswordPattern) {
            UserDTO userDTO = new UserDTO(
                    userID,
                    fullName,
                    email,
                    role,
                    userNAME,
                    hashPassword
            );

            boolean isSaved = userBO.saveUser(userDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, " SignUp SuccessFull", ButtonType.OK).show();
                if (role.equals("USER")) {
                    loadPage("/view/userLogin.fxml");
                    SessionHolder.currentRole = role;
                }else{
                    loadPage("/view/adminLogin.fxml");
                    SessionHolder.currentRole = role;
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "SignUp Failed", ButtonType.OK).show();
            }
        }
    }
    private void refreshPage() {
        userId.setText(userBO.getNextID());
        passwordPWField.setVisible(true);
        passwordConfirmPWField.setVisible(true);
        passwordTextField.setVisible(false);
        passwordConfirmTextField.setVisible(false);
        userRole.setItems(FXCollections.observableArrayList("USER", "ADMIN"));
    }
    private void loadPage(String fxmlPath) throws IOException {
        Stage stage = (Stage) clickhere.getScene().getWindow(); // Get current stage
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        stage.show();
    }
}
