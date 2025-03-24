package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
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

        @ManyToMany
        @JoinTable(
                name = "session_details",
                joinColumns = @JoinColumn(name = "therapist_id"),
                inverseJoinColumns = @JoinColumn(name = "session_id")
        )
        private List<Appointments> sessionds;


        @ManyToOne
        private TPrograms tPrograms;
}
