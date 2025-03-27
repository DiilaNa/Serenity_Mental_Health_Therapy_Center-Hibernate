package lk.ijse.project.mentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramNDocDTO { /*data loads to therapy program page table - join query*/
    private String programID;
    private String programName;
    private String programDetails;
    private Double programFee;
    private String doctorID;
    private String doctorName;
    private String doctorAvailability;
}
