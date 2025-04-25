package lk.ijse.project.mentalHealthTherapyCenter.controller.Login;

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
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.PasswordUtil;
import lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses.SessionHolder;
import lk.ijse.project.mentalHealthTherapyCenter.controller.MainController;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;
import lombok.Setter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLogin implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/user.png"));
        image.setImage(adminIMage);
        refreshPage();
        SessionHolder.currentRole = role;
    }

    @FXML
    private AnchorPane anchorPane;

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

    @Setter
    private String role;

    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        String username = userName.getText();
        String password = passwordPWField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter your username and password", ButtonType.OK).show();
            return;
        }

        String role1 = SessionHolder.currentRole;
        boolean userFromDB = userBO.findUser(username);
        String passFromDB = userBO.findPassWord(username,role1);

        if (userFromDB && PasswordUtil.matches(password, passFromDB)) {
            SessionHolder.userName = username;
            navigateToMainPage("/view/MainLayout.fxml", "user", username);
        } else {
            new Alert(Alert.AlertType.ERROR, "Login Failed..", ButtonType.OK).show();
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

    @FXML
    void forgetPassAction(MouseEvent event) throws IOException {
        loadNewPage("/view/forgetPassword.fxml","user");
        SessionHolder.currentRole = role;
    }
    private void refreshPage(){
        passwordPWField.setVisible(true);
        passwordTextField.setVisible(false);
    }
    private  void  loadNewPage(String fxmlPath,String role) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        ForgetPassword fg = loader.getController();
        fg.setRole(role);
        SessionHolder.currentRole = role;
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Change Password - Serenity Mental Health Therapy Center");
        stage.show();
    }
    private void navigateToMainPage(String fxmlPath,String role,String userName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        MainController controller = loader.getController();
        controller.setUserRole(role);
        controller.setUserName(userName);
        Stage currentStage = (Stage) image.getScene().getWindow();
        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("/css/h.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("The Serenity Mental Health Therapy Center");
        currentStage.close();
        stage.show();
    }
}
