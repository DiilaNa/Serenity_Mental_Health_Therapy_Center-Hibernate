package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PatientDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean save(Patient patient) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
       return true;
    }

    @Override
    public ArrayList<Patient> getAll() throws Exception {
            ArrayList<Patient> patients = new ArrayList<>();
            return patients;

    }

    @Override
    public boolean delete(String pk) throws SQLException, ClassNotFoundException {
        return true;
    }
}
