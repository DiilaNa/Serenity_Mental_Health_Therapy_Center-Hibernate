package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;

import java.util.List;

public interface PatientDAO extends CrudDAO<Patient,String> {
    List<Patient> searchPatientName(String searchByName);
    String search(String patientName);

}
