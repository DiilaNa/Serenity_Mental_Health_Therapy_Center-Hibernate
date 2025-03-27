package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.entity.AppointmentDetails;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.AppointmentDetailsDAO;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppointmentDetailsDAOImpl implements AppointmentDetailsDAO {
    @Override
    public boolean save(AppointmentDetails appointmentDetails, Session session) throws SQLException {
        try{
            session.persist(appointmentDetails);
            session.flush();
            return true;
        }catch(Exception e){
            throw new SQLException("save failed in AppointmentDetailsDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(AppointmentDetails appointmentDetails, Session session) throws SQLException, ClassNotFoundException {
        try{
            session.merge(appointmentDetails);
            return true;
        }catch(Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<AppointmentDetails> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<AppointmentDetails> findByPK(String pk, Session session) throws SQLException {
        AppointmentDetails appointmentDetails = session.get(AppointmentDetails.class, pk);
        session.close();
        if (appointmentDetails == null) {
            return Optional.empty();
        }
        return Optional.of(appointmentDetails);
    }

    @Override
    public Optional<String> getLastPK() {
        return Optional.empty();
    }
}
