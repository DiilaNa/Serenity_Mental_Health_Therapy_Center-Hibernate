package lk.ijse.project.mentalHealthTherapyCenter.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.MedicalHistoryTM;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicalHistoryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(getClass().getResourceAsStream("/images/appointmentIcon.png"));
        Image.setImage(image);

        try{

        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load the Page", ButtonType.CLOSE).show();
        }
    }

    @FXML
    private ImageView Image;

    @FXML
    private TableView<MedicalHistoryTM> Table;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableAptID;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableDate;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableDocName;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tablePName;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tablePid;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableProID;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableProName;

    @FXML
    private TableColumn<String,MedicalHistoryTM> tableTime;






}
