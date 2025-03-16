package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private String userID;

    private String userFullName;
    private String userEmail;
    private String userRole;
    private String userName;
    private String userPassword;
}
