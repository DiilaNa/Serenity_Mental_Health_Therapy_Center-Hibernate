package lk.ijse.project.mentalHealthTherapyCenter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userID;
    private String userFullName;
    private String userEmail;
    private String userRole;
    private String userName;
    private String userPassword;
}
