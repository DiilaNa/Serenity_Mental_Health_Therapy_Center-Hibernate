package lk.ijse.project.mentalHealthTherapyCenter.dto;

import lombok.*;

import java.util.Date;



@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViewSessionDTO {
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
