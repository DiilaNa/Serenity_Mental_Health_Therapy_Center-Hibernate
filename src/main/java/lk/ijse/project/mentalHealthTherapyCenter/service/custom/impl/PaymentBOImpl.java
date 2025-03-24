package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.PaymentDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.TherapyProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Payment;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PaymentDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.PaymentBO;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.PAYMENT);
    @Override
    public List<PaymentDTO> getALL() throws Exception {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(
                    payment.getPaymentID(),
                    payment.getPatientName(),
                    payment.getPaymentAmount(),
                    payment.getPaymentMethod(),
                    payment.getPaymentDate(),
                    payment.getPaymentTime()
            ));
        }
        return paymentDTOS;

    }
}
