package lk.ijse.project.mentalhealththerapycenter.repostory.custom;

import lk.ijse.project.mentalhealththerapycenter.repostory.SuperDAO;
import lk.ijse.project.mentalhealththerapycenter.repostory.custom.impl.UserDAOImpl;

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
    public <T extends SuperDAO>T getSuperDAO(DAOType daoType) {
        return switch (daoType) {
            case USER ->(T) new UserDAOImpl();
        };
    }
}

