package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.PaymentTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PayementController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/Paymentmoney.png"));
        image.setImage(image1);

        tablePayID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        tablePayerName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        tablePayAmount.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        tablePayMethod.setCellValueFactory(new PropertyValueFactory<>("patientBirthDate"));
        tablePayDate.setCellValueFactory(new PropertyValueFactory<>("patientNIC"));
        tablePayTime.setCellValueFactory(new PropertyValueFactory<>("patientGender"));

        try{
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error Failed to load Page", ButtonType.OK).show();
        }
    }

    @FXML
    private ImageView image;

    @FXML
    private TableView<PaymentTM> table;

    @FXML
    private TableColumn<PaymentTM, Double> tablePayAmount;

    @FXML
    private TableColumn<PaymentTM, String> tablePayDate;

    @FXML
    private TableColumn<PaymentTM, String> tablePayID;

    @FXML
    private TableColumn<PaymentTM,String> tablePayMethod;

    @FXML
    private TableColumn<PaymentTM, String> tablePayTime;

    @FXML
    private TableColumn<PaymentTM, String> tablePayerName;



    @FXML
    void tableAction(MouseEvent event) {

    }
    private  void  loadTable(){
    }

}
