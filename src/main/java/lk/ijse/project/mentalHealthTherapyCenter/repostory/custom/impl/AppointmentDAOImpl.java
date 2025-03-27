package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Appointments;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.AppointmentDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppointmentDAOImpl implements AppointmentDAO {
    private final  FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    public boolean save(Appointments appointments) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(appointments);
            session.flush();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("save failed in AppointmentDAOImpl" + e.getMessage());
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean save(Appointments appointments, Session session) throws SQLException {
        try{
            session.persist(appointments);
            return true;
        }catch(Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean update(Appointments appointments, Session session) throws SQLException, ClassNotFoundException {
        try{
            session.merge(appointments);
            return true;
        }catch(Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<Appointments> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Appointments> findByPK(String pk,Session session) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSession();

        String lastPk = session
                .createQuery("SELECT t.id FROM Appointments t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }
}
