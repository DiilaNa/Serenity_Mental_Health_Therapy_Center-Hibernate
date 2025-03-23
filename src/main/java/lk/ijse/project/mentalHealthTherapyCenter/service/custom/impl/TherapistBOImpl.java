package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);

    @Override
    public List<DoctorDTO> getALLTherapist() {
        return null;
    }

    @Override
    public boolean saveTherapist(DoctorDTO doctorDTO) {
        try {
            Therapist therapist = new Therapist();
            therapist.setDoctorID(doctorDTO.getDoctorID());
            therapist.setDoctorName(doctorDTO.getDoctorName());
            therapist.setProgramID(doctorDTO.getProgramID());
            therapist.setProgramName(doctorDTO.getProgramName());
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
        return true;
    }

    @Override
    public boolean deleteTherapist(String DoctorID) {
        return false;
    }
}
