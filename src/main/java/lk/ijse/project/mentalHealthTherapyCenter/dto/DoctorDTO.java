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
    private String doctorNIC;
    private String doctorAddress;
    private String doctorEmail;
    private String doctorPhone;
    private String doctorQualifications;
    private String doctorBirthAvailability;


}
