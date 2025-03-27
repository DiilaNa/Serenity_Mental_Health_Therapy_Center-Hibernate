package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.UserDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.exeception.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {


    @Override
    public boolean update(User user,Session session) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();;
        Query<User> query = session.createQuery("from User ", User.class);
        return query.list();
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();;
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
    public Optional<User> findByPK(String pk,Session session) {
        return Optional.of(null);
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastPk = session
                .createQuery("SELECT t.id FROM User t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(lastPk);
    }

    @Override
    public boolean save(User user, Session session)  {
        try{
            session.persist(user);
            return true;
        }catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Saving user failed");
        }
    }

    @Override
    public boolean updateUser(String UserName, String UserEmail, String UserNewPassword) {
        return false;
    }

    @Override
    public boolean findUser(String UserName, Session session) {
        User user = (User) session.createQuery("FROM User WHERE userName = :uname")
                .setParameter("uname", UserName)
                .uniqueResult();
       if (user != null) {
           return true;
       }
       return false;
    }
}
