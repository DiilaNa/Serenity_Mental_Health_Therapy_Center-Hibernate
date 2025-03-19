package lk.ijse.project.mentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private  String paymentID;
    private  String paymentAmount;
    private  String paymentMethod;
    private  String paymentDate;
    private  String paymentTime;

}
