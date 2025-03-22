package lk.ijse.project.mentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String doctorID;
    private String doctorName;
    private String programID;
    private String programName;
    private String doctorQualifications;
    private String doctorAvailability;
    private String doctorPhone;
    private String doctorEmail;
}
