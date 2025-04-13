package lk.ijse.project.mentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private  String paymentID;
    private  String patientName;
    private  Double paymentAmount;
    private  String paymentMethod;
    private  String paymentDate;
    private  String paymentTime;

    public PaymentDTO(String paymentID, String patientName, Double paymentAmount, String paymentMethod) {
        this.paymentID = paymentID;
        this.patientName = patientName;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
    }



}
