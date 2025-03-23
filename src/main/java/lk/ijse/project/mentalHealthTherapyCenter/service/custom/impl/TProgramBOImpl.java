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
import java.util.Optional;

public class TProgramBOImpl implements TProgramBO {
    TProgramDAO tProgramDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPY_PROGRAMS);
    @Override
    public boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO) {
        try {
            TPrograms tPrograms = new TPrograms();
            tPrograms.setTherapyID(therapyProgramDTO.getTherapyID());
            tPrograms.setTherapyName(therapyProgramDTO.getTherapyName());
            tPrograms.setTherapyDescription(therapyProgramDTO.getTherapyDescription());
            tPrograms.setTherapyFee(therapyProgramDTO.getTherapyFee());

            return tProgramDAO.save(tPrograms);

        } catch (SQLException e) {
            throw new RuntimeException("Error saving therapy programs", e);
        } catch (DuplicateException e) {
            throw new RuntimeException("Therapy program already exists");
        }
    }

    @Override
    public boolean updateTPrograms(TherapyProgramDTO therapyProgramDTO) {
        try {
            TPrograms tPrograms = new TPrograms();
            tPrograms.setTherapyID(therapyProgramDTO.getTherapyID());
            tPrograms.setTherapyName(therapyProgramDTO.getTherapyName());
            tPrograms.setTherapyDescription(therapyProgramDTO.getTherapyDescription());
            tPrograms.setTherapyFee(therapyProgramDTO.getTherapyFee());

            return tProgramDAO.update(tPrograms);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error while saving therapy programs", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error while saving therapy programs");
        }
    }

    @Override
    public boolean deleteTProgram(String therapyProgramID) {
        try {
            return tProgramDAO.deleteByPk(therapyProgramID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error while saving therapy programs", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error while saving therapy programs");
        }
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

    @Override
    public String getNextProgramID() {
        Optional<String> lastPkOptional = tProgramDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("P", "")) + 1;  // Extract number and increment
            return String.format("P%03d", nextId);  // Format as "P001", "P002", etc.
        } else {
            return "P001";  // Default if no records exist
        }
    }
}
