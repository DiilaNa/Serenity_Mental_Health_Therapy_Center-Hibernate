package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProgramDetailsID implements Serializable {
    private String patientID;
    private String therapyProgramID;

}
