package lk.ijse.project.mentalHealthTherapyCenter.repostory;

import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl.PatientDAOImpl;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl.UserDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    @SuppressWarnings("unchecked")
    public <T extends SuperDAO>T getDAO(DAOType daoType) {
        return switch (daoType) {
            case USER ->(T) new UserDAOImpl();
            case PATIENT -> (T) new PatientDAOImpl();
        };
    }
}

