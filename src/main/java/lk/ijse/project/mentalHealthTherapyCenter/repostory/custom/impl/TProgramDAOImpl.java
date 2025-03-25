package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TProgramDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.DuplicateException;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TProgramDAOImpl implements TProgramDAO {

    @Override
    public boolean save(TPrograms tPrograms) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(tPrograms);
            session.flush();
            transaction.commit();
            System.out.println("save successful therapy Programs");
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(TPrograms tPrograms) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(tPrograms);
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
    public List<TPrograms> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<TPrograms> query = session.createQuery("from TPrograms ", TPrograms.class);
        return query.list();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session =FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TPrograms tPrograms = session.get(TPrograms.class, pk);
            if (tPrograms == null) {
                throw new NotFoundException("Program not found");
            }
            session.remove(tPrograms);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<TPrograms> findByPK(String pk) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Searching for Therapy Program with ID: " + pk);

        TPrograms tPrograms = session.get(TPrograms.class, pk);

        if (tPrograms == null) {
            System.out.println("Therapy Program not found!");
        } else {
            System.out.println("Therapy Program found: " + tPrograms.getTherapyID());
        }

        transaction.commit();
        session.close();

        return Optional.ofNullable(tPrograms);

    }

    @Override
    public List<TPrograms> findByIds(List<String> therapyProgramIDs) {
        if (therapyProgramIDs == null || therapyProgramIDs.isEmpty()) {
            System.out.println("therapyProgramIDs is null or empty inside findByIds dao");
            return Collections.emptyList();
        }

        Session session = FactoryConfiguration.getInstance().getSession();
        List<TPrograms> therapyPrograms = session.createQuery(
                        "FROM TPrograms WHERE therapyID IN :ids", TPrograms.class)
                .setParameter("ids", therapyProgramIDs)
                .getResultList();
        session.close();
        return therapyPrograms;
    }
    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();

        String lastPk = session
                .createQuery("SELECT t.id FROM TPrograms t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }

}

