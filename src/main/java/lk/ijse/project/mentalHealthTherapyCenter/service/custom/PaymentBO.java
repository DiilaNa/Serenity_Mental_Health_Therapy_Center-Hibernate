package lk.ijse.project.mentalHealthTherapyCenter.service.custom;

import lk.ijse.project.mentalHealthTherapyCenter.dto.PaymentDTO;
import lk.ijse.project.mentalHealthTherapyCenter.service.SuperBO;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<PaymentDTO> getALL() throws Exception;
}
