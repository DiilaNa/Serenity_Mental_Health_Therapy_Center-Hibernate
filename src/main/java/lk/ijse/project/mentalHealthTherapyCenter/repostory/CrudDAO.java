package lk.ijse.project.mentalHealthTherapyCenter.repostory;

import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.PatientTM;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws Exception;
}
