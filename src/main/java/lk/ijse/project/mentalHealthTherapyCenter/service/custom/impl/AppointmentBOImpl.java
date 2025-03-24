package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.*;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Appointments;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Patient;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Payment;
import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.AppointmentDAO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PatientDAO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.PaymentDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Optional;

public class AppointmentBOImpl implements AppointmentBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOType.PATIENT);
    AppointmentDAO appointmentDAO = DAOFactory.getInstance().getDAO(DAOType.APPOINTMENTS);
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.PAYMENT);

    @Override
    public boolean addAppointment(PatientDTO patientDTO, ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, TherapistDetailsDTO therapistDetailsDTO, PaymentDTO paymentDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Patient patient = new Patient();
            patient.setPatientID(patientDTO.getPatientID());
            patient.setPatientName(patientDTO.getPatientName());
            patient.setPatientBirthDate(patientDTO.getPatientBirthDate());
            patient.setPatientNIC(patientDTO.getPatientNIC());
            patient.setPatientGender(patientDTO.getPatientGender());
            patient.setPatientAddress(patientDTO.getPatientAddress());
            patient.setPatientPhone(patientDTO.getPatientPhone());
            patient.setPatientEmail(patientDTO.getPatientEmail());

            try {
                boolean isPatientSaved = patientDAO.save(patient);

                if (!isPatientSaved) {
                    transaction.rollback();
                    return false;
                }
            } catch (SQLException e) {
               e.printStackTrace();
            }

            Appointments appointments = new Appointments();
            appointments.setSessionId(sessionDTO.getSessionId());
            appointments.setPay_ID(sessionDTO.getPay_ID());
            appointments.setTime(sessionDTO.getTime());
            appointments.setNotes(sessionDTO.getNotes());
            appointments.setDate(sessionDTO.getDate());

            boolean isAppointmentSaved = appointmentDAO.save(appointments);
            if (!isAppointmentSaved) {
                transaction.rollback();
                return false;
            }

            Payment payment = new Payment();
            payment.setPaymentID(paymentDTO.getPaymentID());
            payment.setPatientName(patientDTO.getPatientName());
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setPaymentTime(paymentDTO.getPaymentTime());

            boolean isPaymentSaved = paymentDAO.save(payment);
            if (!isPaymentSaved) {
                transaction.rollback();
                return false;
            }

            TPrograms tPrograms = new TPrograms();
            tPrograms.setTherapyID(programDetailsDTO.getProgramId());
            transaction.commit();
            return true;

        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally{
            if (session!=null) {
                session.close();
            }
        }
    }

    @Override
    public String getNextPatientID() {
        Optional<String> lastPkOptional = patientDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("P", "")) + 1;  // Extract number and increment
            return String.format("P%03d", nextId);
        } else {
            return "P001";
        }
    }

    @Override
    public String getNextSessionID() {
        Optional<String> lastPkOptional = appointmentDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("APT", "")) + 1;  // Extract number and increment
            return String.format("APT%03d", nextId);
        } else {
            return "APT001";
        }
    }

    @Override
    public String getNextPaymentID() {
        Optional<String> lastPkOptional = paymentDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("PAY", "")) + 1;  // Extract number and increment
            return String.format("PAY%03d", nextId);
        } else {
            return "PAY001";
        }
    }
}
