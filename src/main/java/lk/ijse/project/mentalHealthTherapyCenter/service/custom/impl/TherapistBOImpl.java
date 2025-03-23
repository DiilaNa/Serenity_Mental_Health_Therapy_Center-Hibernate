package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ProgramNDocDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.QueryDAO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);

    @Override
    public List<ProgramNDocDTO> getALLTherapist() {
        List<Therapist> therapists = queryDAO.getALLTherapists(); // Fetch employees from DB
        List<ProgramNDocDTO> dtos = new ArrayList<>();

        for (Therapist therapist : therapists) {
            String therapyPID = therapist.getTPrograms().getTherapyID();
            String therapyPName = therapist.getTPrograms().getTherapyName();
            ProgramNDocDTO dto = new ProgramNDocDTO(
                    therapist.getDoctorID(),
                    therapist.getDoctorName(),
                    therapyPID,
                    therapyPName,
                    therapist.getDoctorQualifications(),
                    therapist.getDoctorAvailability(),
                    therapist.getDoctorPhone(),
                    therapist.getDoctorEmail()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public boolean saveTherapist(DoctorDTO doctorDTO) {
        try {
            Therapist therapist = new Therapist();
            therapist.setDoctorID(doctorDTO.getDoctorID());
            therapist.setDoctorName(doctorDTO.getDoctorName());
            therapist.setDoctorQualifications(doctorDTO.getDoctorQualifications());
            therapist.setDoctorAvailability(doctorDTO.getDoctorAvailability());
            therapist.setDoctorPhone(doctorDTO.getDoctorPhone());
            therapist.setDoctorEmail(doctorDTO.getDoctorEmail());

            return therapistDAO.save(therapist);
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        }

    }

    @Override
    public boolean updateTherapist(DoctorDTO doctorDTO) {
        try {
            Therapist therapist = new Therapist();
            therapist.setDoctorID(doctorDTO.getDoctorID());
            therapist.setDoctorName(doctorDTO.getDoctorName());
            therapist.setDoctorQualifications(doctorDTO.getDoctorQualifications());
            therapist.setDoctorAvailability(doctorDTO.getDoctorAvailability());
            therapist.setDoctorPhone(doctorDTO.getDoctorPhone());
            therapist.setDoctorEmail(doctorDTO.getDoctorEmail());

            return therapistDAO.update(therapist);
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Class not found Error");
        }
    }

    @Override
    public boolean deleteTherapist(String DoctorID) {
        try {
            return therapistDAO.deleteByPk(DoctorID);
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error");
        }
    }
}
