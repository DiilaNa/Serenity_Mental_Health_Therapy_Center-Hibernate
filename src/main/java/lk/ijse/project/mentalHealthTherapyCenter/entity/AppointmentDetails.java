package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Table(name = "session_details")
public class AppointmentDetails implements SuperEntity{
    @EmbeddedId
    private AppointmentDetailsID ID;

    @ManyToOne
    @MapsId("appointmentId")
    @JoinColumn(name = "session_id")
    Appointments appointment;


    @ManyToOne
    @MapsId("therapistId")
    @JoinColumn(name = "therapist_id")
    Therapist therapist;
}
