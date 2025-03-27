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
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);
    ProgramDetailsDAO programDetailsDAO = DAOFactory.getInstance().getDAO(DAOType.PROGRAM_DETAILS);
    AppointmentDetailsDAO appointmentDetailsDAO = DAOFactory.getInstance().getDAO(DAOType.APPOINTMENTS_DETAILS);

    @Override
    public boolean addAppointment(PatientDTO patientDTO, ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, TherapistDetailsDTO therapistDetailsDTO, PaymentDTO paymentDTO) {
       Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        /* try{
            Payment payment = new Payment();
            payment.setPaymentID(paymentDTO.getPaymentID());
            payment.setPatientName(patientDTO.getPatientName());
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setPaymentTime(paymentDTO.getPaymentTime());

            boolean isPaymentSaved = paymentDAO.save(payment,session);

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


           *//* patient.setPayment(payment);*//*
            String payID = payment.getPaymentID();
           *//* Optional<Payment> optionalPayment = paymentDAO.findByPK(payID,session);*//*
            Payment payment1 = session.get(Payment.class,payID);
            System.out.println(payment1);
            if (payment1==null) {
                System.out.println("Error: Payment ID " + payID + " not found in database.");
                transaction.rollback();
                return false;
            }
            patient.setPayment(payment1);



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



            boolean isPatientSaved = patientDAO.save(patient,session);

            if (!isPatientSaved) {
                System.out.println("Patient not saved");
                transaction.rollback();
                return false;
            }else {
                System.out.println("Patient saved");
            }
            ProgramDetails programDetails = new ProgramDetails();
            String patientID = patientDTO.getPatientID();
            Patient patient1 = session.get(Patient.class,patientID);
            if (patient1==null) {
                transaction.rollback();
                throw new RuntimeException("Error: Patient ID " + patientID + " not found in database.");
            }
            programDetails.setPatient(patient1);
            String pid = String.valueOf(programDetailsDTO.getProgramId());
            TPrograms tPrograms = session.get(TPrograms.class,pid);
            if (tPrograms==null) {
                transaction.rollback();
                throw new RuntimeException("Error: Program ID " + pid + " not found in database.");
            }
            programDetails.setTPrograms(tPrograms);

            boolean isSaved = programDetailsDAO.save(programDetails,session);
            if (!isSaved) {
                System.out.println("Program not saved");
                transaction.rollback();
                return false;
            }else  {
                System.out.println("program saved");
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

            boolean isAppointmentSaved = appointmentDAO.save(appointments,session);
            if (!isAppointmentSaved) {
                System.out.println("Appointment not saved");
                transaction.rollback();
                return false;
            }else{
                System.out.println("Appointment saved");
            }

            AppointmentDetails appointmentDetails = new AppointmentDetails();
            String appointmentID = appointments.getSessionId();
            Appointments appointmentDetails1 = session.get(Appointments.class,appointmentID);
            if (appointmentDetails1==null) {
                transaction.rollback();
                throw new RuntimeException("Error: Appointment ID " + appointmentID + " not found in database.");
            }
            appointmentDetails.setAppointment(appointmentDetails1);

            List<String> docID = therapistDetailsDTO.getDoctorId();
            TherapistDetailsDTO therapistDetailsDTO1 = session.get(TherapistDetailsDTO.class,docID);
            if (therapistDetailsDTO1==null) {
                System.out.println("No valid therapists id found in therapistDetailsDTO search");
                transaction.rollback();
                return false;
            }

            appointmentDetails.setTherapist(therapistDetailsDTO1);

            transaction.commit();
            return true;

            *//*therapists.get(0);*//*

        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally{
            if (session!=null) {
                session.close();
            }
        }*/
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
            patient.setPayment(payment); // Directly assign instead of fetching

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

            // Save Appointment
            Appointments appointments = new Appointments();
            appointments.setSessionId(sessionDTO.getSessionId());
            appointments.setPay_ID(sessionDTO.getPay_ID());
            appointments.setTime(sessionDTO.getTime());
            appointments.setNotes(sessionDTO.getNotes());
            appointments.setDate(sessionDTO.getDate());
            appointments.setPatient(patient);

            boolean isAppointmentSaved = appointmentDAO.save(appointments, session);
            System.out.println("appointment saved " + isAppointmentSaved);
            if (!isAppointmentSaved) {
                System.out.println("appointment save failed");
                transaction.rollback();
                return false;
            }
            session.flush(); // Ensure Appointment is saved

            // Save Appointment Details
            AppointmentDetails appointmentDetails = new AppointmentDetails();
            appointmentDetails.setAppointment(appointments);

            // Assign Therapist
            List<Therapist> therapists = therapistDAO.findByDocID(therapistDetailsDTO.getDoctorId(),session);

            if (!therapists.isEmpty()) {
                System.out.println("therapists found");
                appointmentDetails.setTherapist(therapists.get(0)); // Assign first therapist
            } else {
                System.out.println("therapists not found");
                transaction.rollback();
                return false;
            }
            boolean isSaved = appointmentDetailsDAO.save(appointmentDetails, session);
            System.out.println("appointment details saved " + isSaved);
            if (!isSaved) {
                System.out.println("appointment save failed");
                transaction.rollback();
                return false;
            }
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
