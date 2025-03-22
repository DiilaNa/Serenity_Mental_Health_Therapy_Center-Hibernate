package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PatientDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.PatientBO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOType.PATIENT);
    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        return patientDAO.updatePatientInDatabase(new Patient(
                patientDTO.getPatientID(),
                patientDTO.getPatientName(),
                patientDTO.getPatientBirthDate(),
                patientDTO.getPatientNIC(),
                patientDTO.getPatientGender(),
                patientDTO.getPatientAddress(),
                patientDTO.getPatientPhone(),
                patientDTO.getPatientEmail()
        ));
    }
    @Override
    public ArrayList<PatientDTO> getALL() throws Exception {
        ArrayList<PatientDTO> patientDTOS = new ArrayList<>();
        ArrayList<Patient> patients = patientDAO.getAll();

        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(
                    patient.getPatientID(),
                    patient.getPatientName(),
                    patient.getPatientBirthDate(),
                    patient.getPatientNIC(),
                    patient.getPatientGender(),
                    patient.getPatientAddress(),
                    patient.getPatientPhone(),
                    patient.getPatientEmail()
            ));
        }
        return patientDTOS;
    }
}
