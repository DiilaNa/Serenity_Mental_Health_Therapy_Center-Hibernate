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
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(Appointments appointments) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Appointments existApt = session.get(Appointments.class,appointments.getSessionId());
            if (existApt != null) {
                throw new SQLException("Appointment already exists");
            }
            session.persist(appointments);

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
    public boolean update(Appointments appointments) throws SQLException, ClassNotFoundException {
        return false;
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
    public Optional<Appointments> findByPK(String pk) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }
}
