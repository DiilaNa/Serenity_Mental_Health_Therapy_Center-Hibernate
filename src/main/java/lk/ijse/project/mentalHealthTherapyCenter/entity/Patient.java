package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    private String patientID;
    private String patientName;
    private String patientBirthDate;
    private String patientNIC;
    private String patientGender;
    @Column(length = 100)
    private String patientAddress;
    private String patientPhone;
    private String patientEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_Id")
    private Payment payment;

    @OneToMany(mappedBy = "patient" ,cascade = CascadeType.ALL)
    private List<appointments> appointments;

    @ManyToMany(mappedBy = "patient" ,cascade = CascadeType.ALL)
    private List<TPrograms> tPrograms;

}
