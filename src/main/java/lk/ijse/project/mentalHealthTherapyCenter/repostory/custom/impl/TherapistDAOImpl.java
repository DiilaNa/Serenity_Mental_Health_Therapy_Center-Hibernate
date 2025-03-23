package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistDAOImpl implements TherapistDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(Therapist therapist) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist1 = session.get(Therapist.class,therapist.getDoctorID());
            if(therapist1 != null){
                throw new SQLException("Therapist already exists");
            }
            session.persist(therapist);
            transaction.commit();
            return true;
        }catch(SQLException e){
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Therapist therapist)  {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(therapist);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Therapist> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean deleteByPk(String pk)  {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = session.get(Therapist.class,pk);
            if(therapist == null){
                throw new NotFoundException("Therapist not found");
            }
            session.remove(therapist);
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
    public Optional<Therapist> findByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }
}
