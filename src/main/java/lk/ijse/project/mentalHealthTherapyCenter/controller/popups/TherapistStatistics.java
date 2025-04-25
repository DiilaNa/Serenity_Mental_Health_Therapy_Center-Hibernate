package lk.ijse.project.mentalHealthTherapyCenter.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorStatsDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.service.BOType;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistStatistics implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image1 = new Image(getClass().getResourceAsStream("/images/TherapyPrograms.png"));
        image.setImage(image1);
        // Force axis settings
        xAxis.setLabel("Doctor Name");
        yAxis.setLabel("Session Count");

        try{
            loadChart();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private BarChart<String, Number> doctorBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @FXML
    private ImageView image;

    TherapistBO therapistBO = BOFactory.getInstance().getBO(BOType.THERAPIST);

    private void loadChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        List<DoctorStatsDTO> statsList = therapistBO.loadDoctorStatistics();

        // Clear previous data
        doctorBarChart.getData().clear();

        for (DoctorStatsDTO dto : statsList) {
            series.getData().add(new XYChart.Data<>(dto.getDoctorName(), dto.getSessionCount()));
        }

        doctorBarChart.getData().add(series);

        // Adjust spacing
        if (statsList.size() == 1) {
            doctorBarChart.setCategoryGap(300); // Larger gap for single item
        } else {
            doctorBarChart.setCategoryGap(20);
        }
        doctorBarChart.setBarGap(5);
    }
}
