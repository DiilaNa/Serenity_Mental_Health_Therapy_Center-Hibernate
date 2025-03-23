package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

public interface TProgramBO extends SuperBO {
    boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO);
}
