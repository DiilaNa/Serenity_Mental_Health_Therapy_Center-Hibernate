package lk.ijse.project.mentalHealthTherapyCenter.repostory;

import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl.*;

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
            case APPOINTMENTS -> (T) new AppointmentDAOImpl();
            case USER ->(T) new UserDAOImpl();
            case PATIENT -> (T) new PatientDAOImpl();
            case PAYMENT -> (T) new PaymentDAOImpl();
            case THERAPIST -> (T) new TherapistDAOImpl();
            case THERAPY_PROGRAMS -> (T) new TProgramDAOImpl();
            case QUERY -> (T) new QueryDAOImpl();
            case PROGRAM_DETAILS -> (T) new ProgramDetailsDAOImpl();
            case APPOINTMENTS_DETAILS -> (T) new AppointmentDetailsDAOImpl();
            default -> null;
        };
    }
}

