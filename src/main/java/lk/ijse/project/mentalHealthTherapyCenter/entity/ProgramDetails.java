package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "program_details")
public class ProgramDetails implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programDetailsID;

    @ManyToOne
    @JoinColumn(name = "patientID")
    private  Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapyProgramID")
    private TPrograms tPrograms;

    @Column(name = "sessionID")
    private String sessionID;

}

