package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Payment;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PaymentDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment payment, Session session) throws SQLException {
        try{
            session.persist(payment);
            session.flush();
            return true;
        } catch (Exception e) {
            throw new HibernateException("save failed in PaymentDAOImpl" + e.getMessage());
        }
    }

    @Override
    public boolean update(Payment payment,Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Payment> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Payment> query = session.createQuery("from Payment ", Payment.class);
        return query.list();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Payment> findByPK(String pk,Session session) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();

        String lastPk = session
                .createQuery("SELECT p.id FROM Payment p ORDER BY p.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }
}
