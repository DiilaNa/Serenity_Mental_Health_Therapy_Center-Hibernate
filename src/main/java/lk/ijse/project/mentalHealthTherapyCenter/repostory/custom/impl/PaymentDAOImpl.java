package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.entity.Payment;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PaymentDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean delete(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }
}
