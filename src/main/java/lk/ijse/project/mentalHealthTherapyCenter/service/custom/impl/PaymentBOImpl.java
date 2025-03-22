package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.PaymentDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Payment;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PaymentDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.PaymentBO;

import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.PAYMENT);
    @Override
    public ArrayList<PaymentDTO> getALL() throws Exception {
        ArrayList<Payment>paymentDTOS = paymentDAO.getAll();
        return null;
    }
}
