package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);
    boolean updateUser(String UserName,String UserEmail, String UserNewPassword);
    boolean findUser(String UserName);
    String getNextID();
    String findPassWord(String username);
}
