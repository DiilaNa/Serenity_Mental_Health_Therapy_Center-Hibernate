package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.UserDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO);
}
