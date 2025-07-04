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

public class AdminLogin implements Initializable {

    @FXML
    private CheckBox adminCheckBox;

    @FXML
    private Button adminLogin;

    @FXML
    private PasswordField adminPasswordPwField;

    @FXML
    private TextField adminPasswordTextField;

    @FXML
    private TextField adminUserName;

    @FXML
    private ImageView image;

    @FXML
    private Hyperlink forgetPass;

    private String role;

    UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    @FXML
    void forgetPassAction(MouseEvent event) throws IOException {
        loadNewPage("/view/forgetPassword.fxml" , "admin");
        SessionHolder.currentRole = role;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image adminIMage = new Image(getClass().getResourceAsStream("/images/user.png"));
        image.setImage(adminIMage);
        refreshPage();
        role = SessionHolder.currentRole;
    }

    @FXML
    void adminCheckBoxAction(ActionEvent event) {
        if (adminCheckBox.isSelected()) {
            adminPasswordPwField.setVisible(false);
            adminPasswordTextField.setVisible(true);
            adminPasswordTextField.setText(adminPasswordPwField.getText());
        }else {
            adminPasswordPwField.setVisible(true);
            adminPasswordTextField.setVisible(false);
            adminPasswordPwField.setText(adminPasswordTextField.getText());
        }
    }

    @FXML
    void adminLoginAction(ActionEvent event) throws IOException {
        String username = adminUserName.getText();
        SessionHolder.userName = username;
        String password = adminPasswordPwField.getText(); // Get the password from password field

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter your username and password", ButtonType.OK).show();
            return;
        }

        String role1 = SessionHolder.currentRole;
        // Retrieve user from DB
        boolean userFromDB = userBO.findUser(username);
        String passFromDB = userBO.findPassWord(username,role1);

        if (userFromDB && PasswordUtil.matches(password,passFromDB)){
            navigateToMainPage("/view/MainLayout.fxml", "admin", username);
        } else {
            new Alert(Alert.AlertType.ERROR, "Login Failed..", ButtonType.OK).show();
        }
    }
    private void navigateToMainPage(String fxmlPath,String role, String userName) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            MainController controller = loader.getController();
            controller.setUserRole(role);
            controller.setUserName(userName);
            Stage currentStage = (Stage) adminUserName.getScene().getWindow();
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/css/h.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("The Serenity Mental Health Therapy Center");
            currentStage.close();
            stage.show();
    }

    private void loadNewPage(String fxmlPath ,String role) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        SessionHolder.currentRole = role;
        scene.getStylesheets().add(getClass().getResource("/css/h.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Change Password - Serenity Mental Health Therapy Center");
        stage.show();
    }

    private void refreshPage(){
        adminPasswordPwField.setVisible(true);
        adminPasswordTextField.setVisible(false);
    }
}
