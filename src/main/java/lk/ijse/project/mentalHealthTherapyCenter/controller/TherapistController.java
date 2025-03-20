
package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TherapistController  implements Initializable {

    @FXML
    private Button addPrograms;

    @FXML
    private Button delete;

    @FXML
    private ComboBox<?> docAvailableCombo;

    @FXML
    private TextField docContact;

    @FXML
    private Label docIDlabel;

    @FXML
    private TextField docMail;

    @FXML
    private TextField docName;

    @FXML
    private ComboBox<?> docQualificationsCombo;

    @FXML
    private ImageView image;

    @FXML
    private ListView<?> programmsListView;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> tableAvailable;

    @FXML
    private TableColumn<?, ?> tableContact;

    @FXML
    private TableColumn<?, ?> tableId;

    @FXML
    private TableColumn<?, ?> tableMail;

    @FXML
    private TableColumn<?, ?> tableName;

    @FXML
    private TableColumn<?, ?> tableProgramID;

    @FXML
    private TableColumn<?, ?> tableProgramName;

    @FXML
    private TableColumn<?, ?> tableQualifications;

    @FXML
    private Button update;

    @FXML
    void TableAction(MouseEvent event) {

    }

    @FXML
    void addProgramsAction(MouseEvent event) throws IOException {
        loadNewPage("/view/SelectPrograms.fxml");
    }

    @FXML
    void deleteBtnAction(ActionEvent event) {

    }

    @FXML
    void resetBtnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/doctor.png"));
        image.setImage(image1);
    }
    private  void  loadNewPage(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Therapy Programs - Serenity Mental Health Therapy Center");
        stage.show();

    }
}

