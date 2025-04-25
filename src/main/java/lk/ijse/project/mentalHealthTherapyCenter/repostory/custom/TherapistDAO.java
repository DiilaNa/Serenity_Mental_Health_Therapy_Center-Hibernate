package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorStatsDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface TherapistDAO extends CrudDAO<Therapist,String> {
    List<DoctorStatsDTO> getDoctorStatistics();
}
