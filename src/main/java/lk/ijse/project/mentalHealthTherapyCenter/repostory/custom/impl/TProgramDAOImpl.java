package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TProgramDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TProgramDAOImpl implements TProgramDAO {
    @Override
    public boolean save(TPrograms tPrograms) throws SQLException {
        return false;
    }

    @Override
    public boolean update(TPrograms tPrograms) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<TPrograms> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean delete(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }
}
