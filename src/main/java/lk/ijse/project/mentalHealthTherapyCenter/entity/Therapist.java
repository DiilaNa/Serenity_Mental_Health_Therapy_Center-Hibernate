package lk.ijse.project.mentalHealthTherapyCenter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Therapist {
        private String doctorID;
        private String doctorName;
        private String programID;
        private String programName;
        private String doctorQualifications;
        private String doctorAvailability;
        private String doctorPhone;
        private String doctorEmail;
}
