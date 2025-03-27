package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.*;
import lk.ijse.project.mentalHealthTherapyCenter.entity.*;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.*;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.AppointmentBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class AppointmentBOImpl implements AppointmentBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOType.PATIENT);
    AppointmentDAO appointmentDAO = DAOFactory.getInstance().getDAO(DAOType.APPOINTMENTS);
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.PAYMENT);
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);
    ProgramDetailsDAO programDetailsDAO = DAOFactory.getInstance().getDAO(DAOType.PROGRAM_DETAILS);

    @Override
    public boolean addAppointment(PatientDTO patientDTO, ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, PaymentDTO paymentDTO) {
       Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Save Payment
            Payment payment = new Payment();
            payment.setPaymentID(paymentDTO.getPaymentID());
            payment.setPatientName(patientDTO.getPatientName());
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setPaymentTime(paymentDTO.getPaymentTime());

            boolean isPaymentSaved = paymentDAO.save(payment, session);
            System.out.println(" payment saved  " + isPaymentSaved);
            if (!isPaymentSaved) {
                System.out.println("payment save failed");
                transaction.rollback();
                return false;
            }
            session.flush(); // Ensure Payment is saved in DB

            Patient patient = new Patient();

            patient.setPatientID(patientDTO.getPatientID());
            patient.setPatientName(patientDTO.getPatientName());
            patient.setPatientBirthDate(patientDTO.getPatientBirthDate());
            patient.setPatientNIC(patientDTO.getPatientNIC());
            patient.setPatientGender(patientDTO.getPatientGender());
            patient.setPatientAddress(patientDTO.getPatientAddress());
            patient.setPatientPhone(patientDTO.getPatientPhone());
            patient.setPatientEmail(patientDTO.getPatientEmail());

            boolean isPatientSaved = patientDAO.save(patient, session);
            System.out.println(" patient saved " + isPatientSaved);
            if (!isPatientSaved) {
                System.out.println("patient save failed");
                transaction.rollback();
                return false;
            }
            session.flush(); // Ensure Patient is saved

            // Save Program Details
            for (String pid : programDetailsDTO.getProgramId()) {
                TPrograms tPrograms = session.get(TPrograms.class, pid);
                if (tPrograms != null) {
                    System.out.println("program saved " + tPrograms.getProgramName());
                }
                if (tPrograms == null) {
                    transaction.rollback();
                    throw new RuntimeException("Error: Program ID " + pid + " not found in database.");
                }
                ProgramDetails programDetails = new ProgramDetails();
                ProgramDetailsID programDetailsID = new ProgramDetailsID(patient.getPatientID(), tPrograms.getProgramID());
                programDetails.setID(programDetailsID);
                programDetails.setPatient(patient);
                programDetails.setTPrograms(tPrograms);


                boolean isSaved = programDetailsDAO.save(programDetails, session);
                System.out.println(" program details saved " + isSaved);
                if (!isSaved) {
                    System.out.println("program details save failed");
                    transaction.rollback();
                    return false;
                }
            }
            session.flush();
            session.clear();

            String tid  = sessionDTO.getTherapist_ID();
            System.out.println("therapist id from dto " + tid);
            Optional<Therapist> optional = therapistDAO.findByPK(tid,session);
            optional.ifPresent(therapist -> System.out.println("therapist found whotto " + therapist.getDoctorName()));
         /*  Therapist therapist = session.get(Therapist.class, tid);
            if (therapist == null) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist ID " + tid + " not found in database.", ButtonType.CANCEL);
                transaction.rollback();
                return false;
            }*/
                // Save Appointment
            Appointments appointments = new Appointments();
            appointments.setSessionId(sessionDTO.getSessionId());
            appointments.setPay_ID(sessionDTO.getPay_ID());
            appointments.setTime(sessionDTO.getTime());
            appointments.setNotes(sessionDTO.getNotes());
            appointments.setDate(sessionDTO.getDate());
            appointments.setPatient(patient);
            appointments.setTherapist(optional.get());
            appointments.setPayment(payment);

            boolean isAppointmentSaved = appointmentDAO.save(appointments, session);
            System.out.println("appointment saved " + isAppointmentSaved);
            if (!isAppointmentSaved) {
                System.out.println("appointment save failed");
                transaction.rollback();
                return false;
            }
            session.flush(); // Ensure Appointment is saved

            transaction.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
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
