package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class ProgramDetailsID implements Serializable {
    private String therapyProgramID;
    private String patientID;

}
