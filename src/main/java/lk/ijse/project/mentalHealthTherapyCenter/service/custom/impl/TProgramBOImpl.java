package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TProgramDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TProgramBO;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.DuplicateException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TProgramBOImpl implements TProgramBO {
    TProgramDAO tProgramDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPY_PROGRAMS);
    @Override
    public boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO) {
        try {
            return tProgramDAO.save(new TPrograms());
        } catch (SQLException e) {
            throw new RuntimeException("Error saving therapy program", e);
        } catch (DuplicateException e) {
            throw new RuntimeException("Therapy program already exists");
        }
    }

    @Override
    public boolean updateTPrograms(TherapyProgramDTO therapyProgramDTO) {
        return false;
    }

    @Override
    public boolean deleteTProgram(String therapyProgramID) {
        return false;
    }

    @Override
    public List<TherapyProgramDTO> getALLTPrograms() throws Exception {
        List<TPrograms> programList = tProgramDAO.getAll();
        List<TherapyProgramDTO> therapyProgramDTOS = new ArrayList<>();
        for (TPrograms tPrograms : programList) {
            therapyProgramDTOS.add(new TherapyProgramDTO(
                    tPrograms.getTherapyID(),
                    tPrograms.getTherapyName(),
                    tPrograms.getTherapyDescription(),
                    tPrograms.getTherapyFee()
            ));
        }
        return therapyProgramDTOS;

    }
}
