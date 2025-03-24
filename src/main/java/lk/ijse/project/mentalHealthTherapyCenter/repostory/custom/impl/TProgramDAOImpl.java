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
import java.util.List;
import java.util.Optional;

public class TProgramDAOImpl implements TProgramDAO {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TPrograms tPrograms) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            TPrograms programs = session.get(TPrograms.class,tPrograms.getTherapyID());
            if (programs !=null) {
                throw new DuplicateException("Therapy program id already exists");
            }
            session.persist(tPrograms);
            transaction.commit();
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
        Session session = factoryConfiguration.getSession();
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
        Session session = factoryConfiguration.getSession();
        Query<TPrograms> query = session.createQuery("from TPrograms ", TPrograms.class);
        return query.list();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
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
        Session session = factoryConfiguration.getSession();
        TPrograms tPrograms = session.get(TPrograms.class, pk);
        session.close();
        if (tPrograms == null) {
            System.out.println("null no program found");
            return Optional.empty();
        }
        return Optional.of(tPrograms);
    }


    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT t.id FROM TPrograms t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }

    @Override
    public Optional<String> findByiD(String pk) {
        Session session = factoryConfiguration.getSession();

        String myPK = session.createQuery("SELECT t.id FROM TPrograms t WHERE t.id ='pk' ",String.class)
                .setMaxResults(1).uniqueResult();

        return Optional.ofNullable(myPK);
    }

}

