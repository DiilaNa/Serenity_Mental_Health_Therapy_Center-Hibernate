package lk.ijse.project.mentalHealthTherapyCenter.service;

import lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl.AppointmentBOImpl;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl.PatientBOImpl;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl.PaymentBOImpl;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl.UserBOImpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    @SuppressWarnings("unchecked")
    public <T extends SuperBO>T getBO(BOType type) {
        return switch (type) {
            case USER -> (T) new UserBOImpl();
            case APPOINTMENT -> (T) new AppointmentBOImpl();
            case PATIENT -> (T) new PatientBOImpl();
            case PAYMENT -> (T) new PaymentBOImpl();
        };
    }

}

