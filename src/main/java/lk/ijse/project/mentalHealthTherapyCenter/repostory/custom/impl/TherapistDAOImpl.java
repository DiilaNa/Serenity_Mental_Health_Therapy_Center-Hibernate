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
    public boolean save(Therapist therapist, Session session){
        try {
            session.persist(therapist);
            session.flush();
            return true;
        }catch (Exception e) {
            throw new RuntimeException("Therapist saving failed in therapistDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(Therapist therapist, Session session) throws SQLException, ClassNotFoundException {
        try {
            System.out.println(therapist+"in daaaaaaaooooooooooooooooooooooooooooo");
            session.merge(therapist);
            System.out.println("therapist updated");
            return true;
        }catch (Exception e){
            throw new RuntimeException("Therapist update failed"+e.getMessage());
        }
    }
    @Override
    public List<Therapist> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Query<Therapist> query = session.createQuery("from Therapist ", Therapist.class);
            List<Therapist> therapists = query.list();
            return therapists;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
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
            session.close();
        }
    }
    @Override /*search in appointments*/
    public List<Therapist> findByDocID(List<String> docIDs,Session session)  {
        List<Therapist> therapists = session.createQuery("FROM Therapist WHERE doctorID IN (:ids)", Therapist.class)
                .setParameter("ids",docIDs)
                .getResultList();
        return therapists;
    }


    @Override /* search in  therapy programs bo*/
    public Optional<Therapist> findByPK(String pk,Session session) {
      return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            String lastPk = session
                    .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(lastPk);
        }catch (Exception e) {
            throw new RuntimeException("Therapist lastPK not found"+e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
