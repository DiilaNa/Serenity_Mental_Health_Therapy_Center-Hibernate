package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);
    @Override
    public boolean saveTherapist(DoctorDTO doctorDTO) {
        return true;
    }

    @Override
    public boolean updateTherapist(DoctorDTO doctorDTO) {
        return true;
    }
}
