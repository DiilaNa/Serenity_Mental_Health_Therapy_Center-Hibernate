package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.UserDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.UserBO;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) {
        System.out.println("User Saved");
        return true;
    }

    @Override
    public boolean updateUser(String UserName, String UserEmail, String UserNewPassword) {
        return true;
    }

}
