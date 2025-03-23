package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "therapy_Programs")
public class TPrograms {
    @Id
    private String therapyID;
    private String therapyName;
    @Column(length = 100)
    private String therapyDescription;
    private Double therapyFee;

    @OneToMany(mappedBy = "tPrograms" ,cascade = CascadeType.ALL)
    private List<Therapist> therapists;

    @ManyToMany
    @JoinTable(
            name = "program_details",
            joinColumns = @JoinColumn(name = "therapyProgram_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patient;
}
