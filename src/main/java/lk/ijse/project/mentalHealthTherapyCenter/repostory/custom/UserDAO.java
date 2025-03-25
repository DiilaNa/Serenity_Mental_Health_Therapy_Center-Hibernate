package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;
import org.hibernate.Session;

import java.sql.SQLException;


public interface UserDAO extends CrudDAO<User,String> {
    boolean saveUser(User user, Session session) throws SQLException, ClassNotFoundException;
    boolean updateUser(String UserName, String UserEmail, String UserNewPassword);
    boolean findUser(String UserName, Session session);
}
