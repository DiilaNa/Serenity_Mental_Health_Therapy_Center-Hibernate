package lk.ijse.project.mentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalHistoryDTO {
    String patientID;
    String patientName;
    String programID;
    String programName;
    String doctorNAME;
    String sessionID;
    String sessionDATE;
    String sessionTIME;
}
