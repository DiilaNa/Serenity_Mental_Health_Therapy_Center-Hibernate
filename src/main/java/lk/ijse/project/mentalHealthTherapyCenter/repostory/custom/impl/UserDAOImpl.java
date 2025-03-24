package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.UserDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(User user) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(user);
            transaction.commit();
            return true;

        } catch (RuntimeException e) {
            transaction.rollback();
            return false;
        }finally{
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        Query<User> query = session.createQuery("from User ", User.class);
        return query.list();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, pk);
            if (user == null) {
                throw new NotFoundException("User not found");
            }
            session.remove(user);
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
    public Optional<User> findByPK(String pk) {
        Session session = factoryConfiguration.getSession();
        User user = session.get(User.class, pk);
        session.close();
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = factoryConfiguration.getSession();
        String lastPk = session
                .createQuery("SELECT t.id FROM User t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }

    @Override
    public boolean updateUser(String UserName, String UserEmail, String UserNewPassword) {
        return false;
    }

    @Override
    public Optional<User> findUser(String UserName) {
        Session session = factoryConfiguration.getSession();
        User user = session.get(User.class,UserName);

        session.close();
        if (user == null) {
            System.out.println("User nul in dao");
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
