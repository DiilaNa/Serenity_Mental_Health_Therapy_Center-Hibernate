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

import java.util.*;

public class AppointmentBOImpl implements AppointmentBO {
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOType.PATIENT);
    AppointmentDAO appointmentDAO = DAOFactory.getInstance().getDAO(DAOType.APPOINTMENTS);
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.PAYMENT);
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);
    ProgramDetailsDAO programDetailsDAO = DAOFactory.getInstance().getDAO(DAOType.PROGRAM_DETAILS);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);

    @Override
    public boolean addAppointment( ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, PaymentDTO paymentDTO) {
       Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Payment payment = new Payment();
            payment.setPaymentID(paymentDTO.getPaymentID());
            payment.setPatientName(paymentDTO.getPatientName());
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setPaymentTime(paymentDTO.getPaymentTime());

            boolean isPaymentSaved = paymentDAO.save(payment, session);
            if (!isPaymentSaved) {
                transaction.rollback();
                return false;
            }
            session.flush();

            Set<String> uniqueProgramIDs = new HashSet<>(programDetailsDTO.getProgramId());

            if (uniqueProgramIDs.isEmpty() && uniqueProgramIDs==null){
                transaction.rollback();
                return false;
            }

            List<TPrograms> validPrograms = new ArrayList<>();
            for (String pid : uniqueProgramIDs) {
                TPrograms tPrograms = session.get(TPrograms.class, pid);
                if (tPrograms == null) {
                    transaction.rollback();
                    throw new RuntimeException("Error: Program ID " + pid + " not found in database.");
                }
                validPrograms.add(tPrograms);
            }

            String patientID = programDetailsDTO.getPatientId();
            Patient patient = session.get(Patient.class,patientID);

            if (patient == null) {
                transaction.rollback();
                throw new RuntimeException("Error: Patient ID " + patientID + " not found in database.");
            }

            for (TPrograms selectedProgram : validPrograms) {
                ProgramDetails programDetails = new ProgramDetails();
                programDetails.setPatient(patient);
                programDetails.setTPrograms(selectedProgram);
                programDetails.setSessionID(sessionDTO.getSessionId());

               boolean isSaved = programDetailsDAO.save(programDetails, session);
               if (!isSaved) {
                   transaction.rollback();
                   return false;
               }
            }
            session.flush();

            String tid  = sessionDTO.getTherapist_ID();
            Optional<Therapist> optional = therapistDAO.findByPK(tid,session);

            Appointments appointments = new Appointments();
            appointments.setSessionId(sessionDTO.getSessionId());
            appointments.setTime(sessionDTO.getTime());
            appointments.setNotes(sessionDTO.getNotes());
            appointments.setDate(sessionDTO.getDate());
            appointments.setPatient(patient);
            appointments.setTherapist(optional.get());
            appointments.setPayment(payment);

            boolean isAppointmentSaved = appointmentDAO.save(appointments, session);
            if (!isAppointmentSaved) {
                transaction.rollback();
                return false;
            }
            session.flush();

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
//   @Override
//   public boolean updateAppointments( ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, PaymentDTO paymentDTO) {
//       Session session = FactoryConfiguration.getInstance().getSession();
//       Transaction transaction = session.beginTransaction();
//       try {
//           Payment payment = session.get(Payment.class, paymentDTO.getPaymentID());
//           if (payment == null) {
//               transaction.rollback();
//               throw new RuntimeException("Payment not found for ID: " + paymentDTO.getPaymentID());
//           }
//           payment.setPaymentID(paymentDTO.getPaymentID());
//           payment.setPatientName(paymentDTO.getPatientName());
//           payment.setPaymentAmount(paymentDTO.getPaymentAmount());
//           payment.setPaymentMethod(paymentDTO.getPaymentMethod());
//
//           boolean isPaymentSaved = paymentDAO.update(payment, session);
//           if (!isPaymentSaved) {
//               transaction.rollback();
//               return false;
//           }
//           session.flush();
//
//           Set<String> uniqueProgramIDs = new HashSet<>(programDetailsDTO.getProgramId());
//
//           if (uniqueProgramIDs.isEmpty()) {
//               transaction.rollback();
//               return false;
//           }
//
//           List<TPrograms> validPrograms = new ArrayList<>();
//           for (String pid : uniqueProgramIDs) {
//               TPrograms tPrograms = session.get(TPrograms.class, pid);
//               if (tPrograms == null) {
//                   transaction.rollback();
//                   throw new RuntimeException("Error: Program ID " + pid + " not found in database.");
//               }
//               validPrograms.add(tPrograms);
//           }
//
//           String patientID = programDetailsDTO.getPatientId();
//           Patient patient = session.get(Patient.class,patientID);
//
//           if (patient == null) {
//               transaction.rollback();
//               throw new RuntimeException("Error: Patient ID " + patientID + " not found in database.");
//           }
//
//           for (TPrograms selectedProgram : validPrograms) {
//                  ProgramDetails programDetails = new ProgramDetails();
//                   programDetails.setPatient(patient);
//                   programDetails.setTPrograms(selectedProgram);
//                   programDetails.setSessionID(sessionDTO.getSessionId());
//               boolean isSaved = programDetailsDAO.update(programDetails, session);
//               if (!isSaved) {
//                   transaction.rollback();
//                   return false;
//               }
//           }
//           session.flush();
//           session.clear();
//
//
//           String tid  = sessionDTO.getTherapist_ID();
//           Optional<Therapist> optional = therapistDAO.findByPK(tid,session);
//
//           Appointments appointments = session.get(Appointments.class, sessionDTO.getSessionId());
//           if (appointments == null) {
//               transaction.rollback();
//               throw new RuntimeException("Appointment not found for ID: " + sessionDTO.getSessionId());
//           }
//
//           appointments.setTime(sessionDTO.getTime());
//           appointments.setNotes(sessionDTO.getNotes());
//           appointments.setDate(sessionDTO.getDate());
//           appointments.setTherapist(optional.get());
//           appointments.setPayment(payment);
//           appointments.setPatient(patient);
//           boolean isAppointmentSaved = appointmentDAO.update(appointments, session);
//           if (!isAppointmentSaved) {
//               transaction.rollback();
//               return false;
//           }
//           session.flush();
//
//           transaction.commit();
//           return true;
//
//       } catch (Exception e) {
//           e.printStackTrace();
//           transaction.rollback();
//           return false;
//       } finally {
//           session.close();
//       }
 //  }

    @Override
    public boolean updateAppointments(ProgramDetailsDTO programDetailsDTO, SessionDTO sessionDTO, PaymentDTO paymentDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Payment payment = session.get(Payment.class, paymentDTO.getPaymentID());
            if (payment == null) {
                transaction.rollback();
                throw new RuntimeException("Payment not found for ID: " + paymentDTO.getPaymentID());
            }
            payment.setPatientName(paymentDTO.getPatientName());
            payment.setPaymentAmount(paymentDTO.getPaymentAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());

            boolean isPaymentSaved = paymentDAO.update(payment, session);
            if (!isPaymentSaved) {
                transaction.rollback();
                throw new RuntimeException("Error: Payment ID " + paymentDTO.getPaymentID() + " not found in database.");
            }
            session.flush();

            String patientID = programDetailsDTO.getPatientId();
            Patient patient = session.get(Patient.class, patientID);
            if (patient == null) {
                transaction.rollback();
                throw new RuntimeException("Error: Patient ID " + patientID + " not found in database.");
            }

            String tid = sessionDTO.getTherapist_ID();
            Optional<Therapist> optional = therapistDAO.findByPK(tid, session);
            if (optional==null) {
                throw new RuntimeException("Error: Therapist ID " + tid + " not found in database.");
            }

            Appointments appointments = session.get(Appointments.class, sessionDTO.getSessionId());
            if (appointments == null) {
                transaction.rollback();
                throw new RuntimeException("appointments not found for ID: " + sessionDTO.getSessionId());
            }

            appointments.setTime(sessionDTO.getTime());
            appointments.setNotes(sessionDTO.getNotes());
            appointments.setDate(sessionDTO.getDate());
            appointments.setTherapist(optional.get());
            appointments.setPayment(payment);
            appointments.setPatient(patient);

            boolean isAppointmentSaved = appointmentDAO.update(appointments, session);
            if (!isAppointmentSaved) {
                transaction.rollback();
                throw new RuntimeException("not saved apt");
            }
            session.flush();

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
            int nextId = Integer.parseInt(lastPk.replace("P", "")) + 1;
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
            int nextId = Integer.parseInt(lastPk.replace("APT", "")) + 1;
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

    @Override
    public List<PatientDTO> searchPatientBYName(String searchBYName) {
        List<Patient> patients = patientDAO.searchPatientName(searchBYName);
        List<PatientDTO> patientDTOList = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDTO patientDTO = new PatientDTO(
                    patient.getPatientID(),
                    patient.getPatientName(),
                    patient.getPatientBirthDate(),
                    patient.getPatientNIC(),
                    patient.getPatientGender(),
                    patient.getPatientAddress(),
                    patient.getPatientPhone(),
                    patient.getPatientEmail()
            );
            patientDTOList.add(patientDTO);
        }
        return patientDTOList;
    }

    @Override
    public List<ViewSessionDTO> getAllAppointments() {
        List<ViewSessionDTO> appointments = queryDAO.getAllAppointments();
        return appointments;
    }

    @Override
    public List<String> loadDoctorIds() throws Exception {
        List<Therapist> therapists = therapistDAO.getAll();
        List<String> therapistIDS = new ArrayList<>();

        for (Therapist dto : therapists) {
            therapistIDS.add(dto.getDoctorID());
        }
        return therapistIDS;
    }

    @Override
    public String searchPatientID(String patientName) {
        return patientDAO.search(patientName);
    }

    @Override
    public boolean cancelAppointment(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Appointments appointment = session.get(Appointments.class, id);

            if (appointment == null) {
                transaction.rollback();
                System.out.println("Appointment not found for ID: " + id);
                return false;
            }
            appointment.setStatus("Appointment Cancelled");
            session.update(appointment);
            session.flush();
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
    public Optional<String> getLastAptID() {
        return appointmentDAO.getLastPK();
    }
}
