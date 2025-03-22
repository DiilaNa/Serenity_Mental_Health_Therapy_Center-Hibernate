package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean save(Therapist therapist) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Therapist dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Therapist> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean delete(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }
}
