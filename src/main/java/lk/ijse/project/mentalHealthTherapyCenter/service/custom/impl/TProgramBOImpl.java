package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ProgramDto;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TProgramDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TProgramBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TProgramBOImpl implements TProgramBO {
    TProgramDAO tProgramDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPY_PROGRAMS);
    @Override
    public boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TPrograms tPrograms = new TPrograms();
            tPrograms.setProgramID(therapyProgramDTO.getTherapyID());
            tPrograms.setProgramName(therapyProgramDTO.getTherapyName());
            tPrograms.setProgramDescription(therapyProgramDTO.getTherapyDescription());
            tPrograms.setProgramFee(therapyProgramDTO.getTherapyFee());

            boolean isSaved = tProgramDAO.save(tPrograms,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving therapy programs", e);
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateTPrograms(TherapyProgramDTO therapyProgramDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TPrograms tPrograms = new TPrograms();
            tPrograms.setProgramID(therapyProgramDTO.getTherapyID());
            tPrograms.setProgramName(therapyProgramDTO.getTherapyName());
            tPrograms.setProgramDescription(therapyProgramDTO.getTherapyDescription());
            tPrograms.setProgramFee(therapyProgramDTO.getTherapyFee());

            boolean isUpdated =  tProgramDAO.update(tPrograms,session);
            if (isUpdated) {
                transaction.commit();
                return true;
            }else{
                transaction.rollback();
                return false;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error while saving therapy programs", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error while saving therapy programs");
        }finally {
            session.close();
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

    @Override /*this method retrives data for the popup in appointments*/
    public List<ProgramDto> getALLTPrograms() throws Exception {
        List<TPrograms> programList = tProgramDAO.getAll();
        List<ProgramDto> programDtos = new ArrayList<>();
        for (TPrograms tPrograms : programList) {
            programDtos.add(new ProgramDto(
                    tPrograms.getProgramID(),
                    tPrograms.getProgramName(),
                    tPrograms.getProgramDescription(),
                    tPrograms.getProgramFee()
            ));
        }
        return programDtos;

    }

    @Override
    public String getNextProgramID() {
        Optional<String> lastPkOptional = tProgramDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("PR", "")) + 1;  // Extract number and increment
            return String.format("PR%03d", nextId);
        } else {
            return "PR001";  // Default if no records exist
        }
    }

    @Override /*loads program table*/
    public List<ProgramDto> getALL() throws Exception {
        List<TPrograms> tPrograms = tProgramDAO.getAll();
        List<ProgramDto> dtos = new ArrayList<>();

        for (TPrograms programs : tPrograms) {
            ProgramDto dto = new ProgramDto(
                    programs.getProgramID(),
                    programs.getProgramName(),
                    programs.getProgramDescription(),
                    programs.getProgramFee()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
