package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private  String paymentID;
    private  String patientName;
    private  Double paymentAmount;
    private  String paymentMethod;
    private  String paymentDate;
    private  String paymentTime;
}
