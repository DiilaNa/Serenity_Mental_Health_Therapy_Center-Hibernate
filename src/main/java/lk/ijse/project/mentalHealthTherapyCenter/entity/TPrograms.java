package lk.ijse.project.mentalHealthTherapyCenter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPrograms {
    private String therapyID;
    private String therapyName;
    private String therapyDescription;
    private Double therapyFee;
}
