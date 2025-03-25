package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.NotFoundException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist therapist) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(therapist);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Therapist therapist)  {
        Session session = FactoryConfiguration.getInstance().getSession();
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Therapist> query = session.createQuery("from Therapist ", Therapist.class);
        List<Therapist> therapists = query.list();
        return therapists;
    }

    @Override
    public boolean deleteByPk(String pk)  {
        Session session = FactoryConfiguration.getInstance().getSession();
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
    public List<Therapist> findByDocID(List<String> docIDs)  {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Therapist> therapists = session.createQuery("FROM Therapist WHERE doctorID IN:ids", Therapist.class)
                .setParameter("ids",docIDs)
                .getResultList();
        session.close();
        return therapists;
    }


    @Override
    public Optional<Therapist> findByPK(String pk) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Therapist therapist = session.get(Therapist.class, pk);
        session.close();
        if (therapist == null) {
            return Optional.empty();
        }
        return Optional.of(therapist);

    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();

        String lastPk = session
                .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }
}
