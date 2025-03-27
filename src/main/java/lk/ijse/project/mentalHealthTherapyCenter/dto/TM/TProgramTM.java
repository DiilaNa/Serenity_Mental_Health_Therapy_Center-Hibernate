package lk.ijse.project.mentalHealthTherapyCenter.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TProgramTM {
    private String therapyID;
    private String therapyName;
    private String therapyDescription;
    private Double therapyFee;
}
