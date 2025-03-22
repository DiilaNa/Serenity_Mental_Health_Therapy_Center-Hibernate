package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);

    @Override
    public ArrayList<DoctorDTO> getALLTherapist() {
        return null;
    }

    @Override
    public boolean saveTherapist(DoctorDTO doctorDTO) {
        return true;
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
