package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;

import java.util.List;

public interface TherapistDAO extends CrudDAO<Therapist,String> {
    List<Therapist> findByDocID(List<String> doctorIds);
}
