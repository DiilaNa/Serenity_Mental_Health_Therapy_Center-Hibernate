package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.UserDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = new User();
            user.setUserID(userDTO.getUserID());
            user.setUserFullName(userDTO.getUserFullName());
            user.setUserEmail(userDTO.getUserEmail());
            user.setUserRole(userDTO.getUserRole());
            user.setUserName(userDTO.getUserName());
            user.setUserPassword(userDTO.getUserPassword());

            boolean isSaved= userDAO.save(user,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else {
                transaction.rollback();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error saving user");
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateUser(String UserName, String UserEmail, String UserNewPassword) {
        try {
            return userDAO.updateUser(UserName,UserEmail,UserNewPassword);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error updating user");
        }
    }

    @Override
    public boolean findUser(String UserName, String Password) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean i  = userDAO.findUser(UserName,session);
            if(i){
                transaction.commit();
                return true;
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Credentials").show();
                transaction.rollback();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error saving user");
        }finally {
            session.close();
        }
    }

    @Override
    public String getNextID() {
        Optional<String> lastPkOptional = userDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("U", "")) + 1;  // Extract number and increment
            return String.format("U%03d", nextId);  // Format as "P001", "P002", etc.
        } else {
            return "U001";  // Default if no records exist
        }
    }
}
