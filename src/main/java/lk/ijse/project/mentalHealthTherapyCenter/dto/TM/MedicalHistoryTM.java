package lk.ijse.project.mentalHealthTherapyCenter.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalHistoryTM {
    String patientID;
    String patientName;
    String programs;
    String doctorNAME;
    String sessionID;
    String sessionDATE;
    String sessionTIME;
}
