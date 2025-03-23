package lk.ijse.project.mentalHealthTherapyCenter.repostory;
import lk.ijse.project.mentalHealthTherapyCenter.entity.SuperEntity;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID> extends SuperDAO{
    boolean save(T t) throws SQLException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws Exception;
    boolean deleteByPk(ID pk) throws SQLException, ClassNotFoundException;
    Optional<T> findByPK(ID pk);
    Optional<String> getLastPK();
}
