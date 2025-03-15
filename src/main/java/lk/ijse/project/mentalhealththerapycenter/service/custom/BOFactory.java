package lk.ijse.project.mentalhealththerapycenter.service.custom;

import lk.ijse.project.mentalhealththerapycenter.service.SuperBO;
import lk.ijse.project.mentalhealththerapycenter.service.custom.impl.userBOImpl;

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
            case USER -> (T) new userBOImpl();
        };
    }

}

