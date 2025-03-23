package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PatientDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Patient patient) throws SQLException {
       Session session = factoryConfiguration.getSession();
       Transaction transaction = session.beginTransaction();
       try {
           Patient patient1 = session.get(Patient.class, patient.getPatientID());
           if (patient1 == null) {
               throw new SQLException("Patient does not exist");
           }
           session.persist(patient);
           transaction.commit();
           return true;
       }catch (Exception e) {
           transaction.rollback();
           return false;
       }finally {
           if (session != null) {
               session.close();
           }
       }
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Patient> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Patient> findByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }
}
