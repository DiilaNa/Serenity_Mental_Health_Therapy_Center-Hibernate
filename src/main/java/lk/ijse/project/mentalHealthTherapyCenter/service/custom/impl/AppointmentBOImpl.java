package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.*;
import lk.ijse.project.mentalHealthTherapyCenter.entity.*;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.*;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AppointmentBOImpl implements AppointmentBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOType.PATIENT);
    AppointmentDAO appointmentDAO = DAOFactory.getInstance().getDAO(DAOType.APPOINTMENTS);
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.PAYMENT);
    TProgramDAO programDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPY_PROGRAMS);
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);

    @Override
    public boolean addAppointment(PatientDTO patientDTO, ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, TherapistDetailsDTO therapistDetailsDTO, PaymentDTO paymentDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Payment payment = new Payment();
            payment.setPaymentID(paymentDTO.getPaymentID());
            payment.setPatientName(patientDTO.getPatientName());
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setPaymentTime(paymentDTO.getPaymentTime());

            boolean isPaymentSaved = paymentDAO.save(payment);

            if (!isPaymentSaved) {
                System.out.println("Payment not saved");
                transaction.rollback();
                return false;
            }else{
                System.out.println("Payment saved");
            }

            Patient patient = new Patient();
            patient.setPatientID(patientDTO.getPatientID());
            patient.setPatientName(patientDTO.getPatientName());
            patient.setPatientBirthDate(patientDTO.getPatientBirthDate());
            patient.setPatientNIC(patientDTO.getPatientNIC());
            patient.setPatientGender(patientDTO.getPatientGender());
            patient.setPatientAddress(patientDTO.getPatientAddress());
            patient.setPatientPhone(patientDTO.getPatientPhone());
            patient.setPatientEmail(patientDTO.getPatientEmail());


           /* patient.setPayment(payment);*/
            String payID = payment.getPaymentID();
            Optional<Payment> optionalPayment = paymentDAO.findByPK(payID);
            if (optionalPayment.isEmpty()) {
                System.out.println("Error: Payment ID " + payID + " not found in database.");
                transaction.rollback();
                return false;
            }
            Payment pay = optionalPayment.get();
            patient.setPayment(pay);



            List<String> programIDs = programDetailsDTO.getProgramId();
            if (programIDs == null || programIDs.isEmpty()) {
                System.out.println("Error: No program IDs provided through Controller");
                transaction.rollback();
                return false;
            }

            List<TPrograms> therapyPrograms = programDAO.findByIds(programIDs);

            if (therapyPrograms.isEmpty()) {
                System.out.println("No valid therapy programs get from DAO");
                transaction.rollback();
                return false;
            }



            boolean isPatientSaved = patientDAO.save(patient);

            if (!isPatientSaved) {
                System.out.println("Patient not saved");
                transaction.rollback();
                return false;
            }else {
                System.out.println("Patient saved");
            }

            Appointments appointments = new Appointments();
            appointments.setSessionId(sessionDTO.getSessionId());
            appointments.setPay_ID(sessionDTO.getPay_ID());
            appointments.setTime(sessionDTO.getTime());
            appointments.setNotes(sessionDTO.getNotes());
            appointments.setDate(sessionDTO.getDate());
            appointments.setPatient(patient);

            List<String> doctorIDs = therapistDetailsDTO.getDoctorId();
            List<Therapist> therapists = therapistDAO.findByDocID(doctorIDs);

            if (therapists.isEmpty()) {
                System.out.println("No valid therapists found");
                transaction.rollback();
                return false;
            }

            boolean isAppointmentSaved = appointmentDAO.save(appointments);
            if (!isAppointmentSaved) {
                System.out.println("Appointment not saved");
                transaction.rollback();
                return false;
            }else{
                System.out.println("Appointment saved");
            }

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
