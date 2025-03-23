package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "appointments")
public class Appointments implements SuperEntity{
    @Id
    private String sessionId;
    private String pay_ID;
    private String time;
    private String notes;
    private String date;
    @ManyToOne
    @JoinColumn(name = "patient_Id")
    private Patient patient;

    @ManyToMany(mappedBy = "sessionds")
    private List<Therapist> therapists;

}



