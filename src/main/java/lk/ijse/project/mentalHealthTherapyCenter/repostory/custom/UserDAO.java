package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;
import org.hibernate.Session;



public interface UserDAO extends CrudDAO<User,String> {
    boolean updateUser(String UserName, String UserEmail, String UserNewPassword);
    boolean findUser(String UserName, Session session);
    User findPassWord(String password,String role, Session session);
}
