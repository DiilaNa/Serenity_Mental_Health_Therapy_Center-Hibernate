package lk.ijse.project.mentalHealthTherapyCenter.dto.TM;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TherapistTM {
    private String doctorID;
    private String doctorName;
    private String programID;
    private String programName;
    private String doctorQualifications;
    private String doctorAvailability;
    private String doctorPhone;
    private String doctorEmail;
}
