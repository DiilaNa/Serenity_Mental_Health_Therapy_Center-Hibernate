package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;

import java.util.Optional;


public interface UserDAO extends CrudDAO<User,String> {
    boolean updateUser(String UserName, String UserEmail, String UserNewPassword);
    Optional<User> findUser(String UserName);
}
