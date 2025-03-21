package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl.AppointmentBOImpl;
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
        };
    }

}

