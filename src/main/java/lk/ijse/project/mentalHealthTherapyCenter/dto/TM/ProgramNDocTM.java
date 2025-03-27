package lk.ijse.project.mentalHealthTherapyCenter.dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramNDocTM { /*join query - loads data to therapy programs*/
    private String programID;
    private String programName;
    private String programDetails;
    private Double programFee;
    private String doctorID;
    private String doctorName;
    private String doctorAvailability;

}
