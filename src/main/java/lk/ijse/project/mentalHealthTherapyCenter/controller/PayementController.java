package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PayementController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/Paymentmoney.png"));
        image.setImage(image1);
    }

    @FXML
    private ImageView image;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> tablePayAmount;

    @FXML
    private TableColumn<?, ?> tablePayDate;

    @FXML
    private TableColumn<?, ?> tablePayID;

    @FXML
    private TableColumn<?, ?> tablePayMethod;

    @FXML
    private TableColumn<?, ?> tablePayTime;

    @FXML
    private TableColumn<?, ?> tablePayerName;

    @FXML
    void tableAction(MouseEvent event) {

    }

}
