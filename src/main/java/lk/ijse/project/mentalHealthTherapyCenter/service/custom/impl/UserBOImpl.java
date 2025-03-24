package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.User;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.UserDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;

import java.util.Optional;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) {
        try {
            User user = new User();
            user.setUserID(userDTO.getUserID());
            user.setUserFullName(userDTO.getUserFullName());
            user.setUserEmail(userDTO.getUserEmail());
            user.setUserRole(userDTO.getUserRole());
            user.setUserName(userDTO.getUserName());
            user.setUserPassword(userDTO.getUserPassword());
            return userDAO.save(user);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error saving user");
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
        try{
            Optional<User> idsOPtional = userDAO.findUser(UserName);
            if(idsOPtional.isPresent()){
                return true;
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Credentials").show();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error saving user");
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
