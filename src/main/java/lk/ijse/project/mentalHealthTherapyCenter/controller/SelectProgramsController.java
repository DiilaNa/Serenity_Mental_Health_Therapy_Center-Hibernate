package lk.ijse.project.mentalHealthTherapyCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class SelectProgramsController {

    @FXML
    private TableView<?> Table;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button select;

    @FXML
    private TableColumn<?, ?> tableFee;

    @FXML
    private TableColumn<?, ?> tableIID;

    @FXML
    private TableColumn<?, ?> tableName;

    @FXML
    private TableColumn<?, ?> tableProgramDetails;

    @FXML
    void selectBtnAction(ActionEvent event) {

    }

    @FXML
    void tableAction(MouseEvent event) {

    }

}
