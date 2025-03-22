package lk.ijse.project.mentalHealthTherapyCenter.repostory;

import lk.ijse.project.mentalHealthTherapyCenter.dto.TM.PatientTM;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t) throws SQLException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws Exception;
    boolean delete(String pk) throws SQLException, ClassNotFoundException;
}
