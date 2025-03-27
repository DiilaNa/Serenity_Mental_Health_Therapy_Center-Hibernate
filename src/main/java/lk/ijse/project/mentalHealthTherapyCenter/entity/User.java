package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_details")
public class User implements SuperEntity {
    @Id
    private String userID;
    private String userFullName;
    private String userEmail;
    private String userRole;
    private String userName;
    private String userPassword;
}
