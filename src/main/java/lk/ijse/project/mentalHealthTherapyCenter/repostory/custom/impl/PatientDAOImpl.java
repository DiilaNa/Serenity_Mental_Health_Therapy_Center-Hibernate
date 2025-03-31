package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PatientDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean save(Patient patient, Session session) throws SQLException {
        try {
            session.persist(patient);
            session.flush();
            return true;
        }catch (Exception e) {
            throw new RuntimeException("save failed in PatientDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(Patient patient, Session session) throws SQLException, ClassNotFoundException {
        try {
            session.merge(patient);
            return true;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<Patient> getAll() throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        Query<Patient> query = session.createQuery("from Patient ", Patient.class);
        return query.list();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Patient patient = session.get(Patient.class,pk);
            if(patient == null){
                throw new NotFoundException("Patient not found");
            }
            session.remove(patient);
            transaction.commit();
            return true;
        } catch (NotFoundException e) {
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Patient> findByPK(String pk,Session session) {
        Patient patient = session.get(Patient.class, pk);
        session.close();
        if (patient == null) {
            return Optional.empty();
        }
        return Optional.of(patient);
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPk = session
                .createQuery("SELECT t.id FROM Patient t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }

    @Override
    public List<Patient> searchPatientName(String searchByName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Patient> query = session.createQuery("from Patient p where p.patientName like :name", Patient.class);
        query.setParameter("name", "%" + searchByName + "%");  // Use LIKE with wildcards for partial matching

        return query.list();
    }
}
