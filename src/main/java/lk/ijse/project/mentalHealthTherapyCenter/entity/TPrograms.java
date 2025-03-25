package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "therapy_Programs")
public class TPrograms implements SuperEntity {
    @Id
    private String therapyID;
    private String therapyName;
    @Column(length = 100)
    private String therapyDescription;
    private Double therapyFee;

    @OneToMany(mappedBy = "tPrograms" ,cascade = CascadeType.ALL)
    private List<Therapist> therapists;

    @OneToMany(mappedBy = "tPrograms",cascade = CascadeType.ALL)
    private List<ProgramDetails> programDetails;
}
