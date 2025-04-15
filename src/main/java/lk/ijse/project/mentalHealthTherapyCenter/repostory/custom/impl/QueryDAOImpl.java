package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;


import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.MedicalHistoryDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.PatientsInEveryProgramDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ViewSessionDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<ViewSessionDTO> getAllAppointments() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query<Object[]> query = session.createNativeQuery("SELECT DISTINCT\n" +
                    "    s.sessionID,\n" +
                    "    s.date,\n" +
                    "    s.notes,\n" +
                    "    s.time,\n" +
                    "    s.doctor_id,\n" +
                    "    p.patientName,\n" +
                    "    py.paymentID,\n" +
                    "    py.paymentAmount,\n" +
                    "    py.paymentMethod,\n" +
                    "    s.status,\n" +
                    "    pr.programID\n" +
                    "FROM appointments s\n" +
                    "JOIN patient p ON s.patient_Id = p.patientID\n" +
                    "LEFT JOIN payment py ON s.payment_ID = py.paymentID\n" +
                    "LEFT JOIN program_details pp ON pp.patientID = p.patientID\n" +
                    "LEFT JOIN therapy_programs pr ON pr.programID = pp.therapyProgramID\n" +
                    "ORDER BY s.sessionID, pr.programID;\n", Object[].class);

            List<Object[]> resultList = query.list();

            // Convert the result list into ViewSessionDTO
            List<ViewSessionDTO> sessions = new ArrayList<>();
            Map<String, ViewSessionDTO> sessionMap = new HashMap<>();

            for (Object[] result : resultList) {
                String sessionID = (String) result[0];
                String sessionDate = (String) result[1];
                String sessionNotes = (String) result[2];
                String sessionTime = (String) result[3];
                String doctorID = (String) result[4];
                String patientName = (String) result[5];
                String paymentID = (String) result[6];
                Double paymentAmount = (Double) result[7];
                String paymentMethod = (String) result[8];
                String appointmentStatus = (String) result[9];
                String programID = (String) result[10];

                // Check if the session already exists in the map
                ViewSessionDTO sessionDTO = sessionMap.get(sessionID);
                if (sessionDTO == null) {
                    sessionDTO = new ViewSessionDTO(sessionID, sessionDate, sessionNotes, sessionTime, doctorID,
                            new ArrayList<>(), patientName, paymentID, paymentAmount,
                            paymentMethod, appointmentStatus);
                    sessionMap.put(sessionID, sessionDTO);
                }

                // Add programID to the session's list of programs
                if (programID != null && !programID.isEmpty()) {
                    sessionDTO.getPrograms().add(programID);
                }
            }

            // Return the sessions as a list
            return new ArrayList<>(sessionMap.values());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
//
//    @Override
//    public List<MedicalHistoryDTO> getALLMedicalHistory() {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        try {
//            String sql = "SELECT DISTINCT  \n" +
//                    "    p.patientID,\n" +
//                    "    p.patientName,\n" +
//                    "    pd.therapyProgramID,\n" +
//                    "    tp.programName,\n" +
//                    "    t.doctorName,\n" +
//                    "    s.sessionId,\n" +
//                    "    s.date,\n" +
//                    "    s.time\n" +
//                    "FROM patient p\n" +
//                    "JOIN appointments s ON p.patientID = s.patient_Id\n" +
//                    "JOIN therapist t ON s.doctor_id = t.doctorID\n" +
//                    "LEFT JOIN program_details pd ON pd.patientID = p.patientID\n" +
//                    "LEFT JOIN therapy_programs tp ON tp.programID = pd.therapyProgramID\n" +
//                    "ORDER BY s.sessionId, pd.therapyProgramID;\n";
//
//            // Execute the query as a native SQL query and get the result as Object[]
//            Query<Object[]> query = session.createNativeQuery(sql);
//            List<Object[]> resultList = query.list();
//
//            // Convert the result list into MedicalHistoryDTO
//            List<MedicalHistoryDTO> medicalHistoryList = new ArrayList<>();
//
//            for (Object[] result : resultList) {
//                String patientId = (String) result[0];
//                String patientName = (String) result[1];
//                String therapyProgramID = (String) result[2];
//                String programName = (String) result[3];
//                String doctorName = (String) result[4];
//                String sessionId = (String) result[5];
//                String sessionDate = (String) result[6];
//                String sessionTime = (String) result[7];
//
//                if (medicalHistoryList.contains(sessionId)) {
//                    continue; // skip duplicates
//                }
//
//                // Create a new DTO object
//                MedicalHistoryDTO dto = new MedicalHistoryDTO(patientId, patientName, therapyProgramID,
//                        programName, doctorName, sessionId, sessionDate, sessionTime);
//
//                // Add the DTO to the list
//                medicalHistoryList.add(dto);
//            }
//
//            return medicalHistoryList;
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        } finally {
//            session.close();
//        }
//    }
@Override
public List<MedicalHistoryDTO> getALLMedicalHistory() {
    Session session = FactoryConfiguration.getInstance().getSession();
    try {
        String sql = "SELECT DISTINCT  \n" +
                "    p.patientID,\n" +
                "    p.patientName,\n" +
                "    pd.therapyProgramID,\n" +
                "    tp.programName,\n" +
                "    t.doctorName,\n" +
                "    s.sessionId,\n" +
                "    s.date,\n" +
                "    s.time\n" +
                "FROM patient p\n" +
                "JOIN appointments s ON p.patientID = s.patient_Id\n" +
                "JOIN therapist t ON s.doctor_id = t.doctorID\n" +
                "LEFT JOIN program_details pd ON pd.patientID = p.patientID\n" +
                "LEFT JOIN therapy_programs tp ON tp.programID = pd.therapyProgramID\n" +
                "ORDER BY s.sessionId, pd.therapyProgramID;\n";

        Query<Object[]> query = session.createNativeQuery(sql);
        List<Object[]> resultList = query.list();

        Map<String, MedicalHistoryDTO> historyMap = new LinkedHashMap<>();

        for (Object[] result : resultList) {
            String patientId = (String) result[0];
            String patientName = (String) result[1];
            String therapyProgramID = (String) result[2];
            String programName = (String) result[3];
            String doctorName = (String) result[4];
            String sessionId = (String) result[5];
            String sessionDate = (String) result[6];
            String sessionTime = (String) result[7];

            MedicalHistoryDTO dto = historyMap.get(sessionId);
            if (dto == null) {
                dto = new MedicalHistoryDTO(patientId, patientName,therapyProgramID,programName, doctorName, sessionId, sessionDate, sessionTime);
                historyMap.put(sessionId, dto);
            }
        }

        return new ArrayList<>(historyMap.values());
    } catch (RuntimeException e) {
        throw new RuntimeException(e);
    } finally {
        session.close();
    }
}


    @Override
    public List<PatientsInEveryProgramDTO> getPatientsInEveryProgram() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            String sql = "SELECT pd.patientID,\n" +
                    "       p.patientName,\n" +
                    "       p.patientPhone,\n" +
                    "       p.patientAddress\n" +
                    "FROM program_details pd\n" +
                    "         JOIN mental_hospital.patient p ON p.patientID = pd.patientID\n" +
                    "GROUP BY pd.patientID, p.patientName, p.patientPhone, p.patientAddress\n" +
                    "HAVING COUNT(DISTINCT pd.therapyProgramID) = (\n" +
                    "    SELECT COUNT(tp.programID) FROM therapy_programs tp\n" +
                    ");";
            Query<Object[]> query = session.createNativeQuery(sql);
            List<Object[]> resultList = query.list();
            List<PatientsInEveryProgramDTO> patientsInEveryProgramDTOList = new ArrayList<>();
            for (Object[] result : resultList) {
                String patientId = (String) result[0];
                String patientName = (String) result[1];
                String contact = (String) result[2];
                String address = (String) result[3];
                PatientsInEveryProgramDTO dto = new PatientsInEveryProgramDTO(patientId, patientName, contact, address);
                patientsInEveryProgramDTOList.add(dto);
            }
            return patientsInEveryProgramDTOList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
