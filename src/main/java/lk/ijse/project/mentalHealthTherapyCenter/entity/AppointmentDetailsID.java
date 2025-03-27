package lk.ijse.project.mentalHealthTherapyCenter.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AppointmentDetailsID implements Serializable {
    private String appointmentId;
    private String therapistId;
}
