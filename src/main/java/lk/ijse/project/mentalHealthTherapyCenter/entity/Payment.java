package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "payment")
public class Payment implements SuperEntity {
    @Id
    private  String paymentID;
    private  String patientName;
    private  Double paymentAmount;
    private  String paymentMethod;
    private  String paymentDate;
    private  String paymentTime;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    private Appointments appointments;

}
