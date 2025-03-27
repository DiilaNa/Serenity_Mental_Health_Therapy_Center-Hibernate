package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "therapist")
public class Therapist implements SuperEntity {
        @Id
        private String doctorID;
        private String doctorName;
        private String doctorQualifications;
        private String doctorAvailability;
        private String doctorPhone;
        private String doctorEmail;

        @ManyToOne
        @JoinColumn(name = "doctor_id")
        private Appointments appointments;
}
