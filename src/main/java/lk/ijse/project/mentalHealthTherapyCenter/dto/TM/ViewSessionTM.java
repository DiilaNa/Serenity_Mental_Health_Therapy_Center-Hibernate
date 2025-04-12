package lk.ijse.project.mentalHealthTherapyCenter.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViewSessionTM {
    private String sessionID;
    private Date sessionDate;
    private String sessionNotes;
    private String sessionTime;
    private String doctorID;
    private String programID;
    private String patientName;
    private String paymentID;
    private Double paymentAmount;
    private String paymentMethod;
    private String appointmentStatus;

}
