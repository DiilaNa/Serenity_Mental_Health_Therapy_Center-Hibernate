package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        private String programID;
        @Column(length = 100)
        private String programName;
        @Column(length = 100)
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
        private List<appointments> sessionds;


        @ManyToOne
        private TPrograms tPrograms;
}
