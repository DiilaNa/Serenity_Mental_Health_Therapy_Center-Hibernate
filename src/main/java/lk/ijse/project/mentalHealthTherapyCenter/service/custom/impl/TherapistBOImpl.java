package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.dto.ProgramNDocDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.QueryDAO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TProgramDAO;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);
    TProgramDAO programDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPY_PROGRAMS);
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);

    @Override
    public List<ProgramNDocDTO> getALLTherapist() {
        List<Therapist> therapists = queryDAO.getALLTherapists();
        List<ProgramNDocDTO> dtos = new ArrayList<>();

        for (Therapist therapist : therapists) {
            String therapyPID = therapist.getTPrograms().getTherapyID();
            String therapyPName = therapist.getTPrograms().getTherapyName();
            ProgramNDocDTO dto = new ProgramNDocDTO(
                    therapist.getDoctorID(),
                    therapist.getDoctorName(),
                    therapyPID,
                    therapyPName,
                    therapist.getDoctorQualifications(),
                    therapist.getDoctorAvailability(),
                    therapist.getDoctorPhone(),
                    therapist.getDoctorEmail()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public boolean saveTherapist(DoctorDTO doctorDTO) {
        try {
            Therapist therapist = new Therapist();
            therapist.setDoctorID(doctorDTO.getDoctorID());
            therapist.setDoctorName(doctorDTO.getDoctorName());
            therapist.setDoctorQualifications(doctorDTO.getDoctorQualifications());
            therapist.setDoctorAvailability(doctorDTO.getDoctorAvailability());
            therapist.setDoctorPhone(doctorDTO.getDoctorPhone());
            therapist.setDoctorEmail(doctorDTO.getDoctorEmail());

            String therapyPID = doctorDTO.getTherapyID();
            System.out.println(therapyPID);
            Optional<String> optionalID = programDAO.findByiD(therapyPID);
            if (optionalID.isPresent()){
                System.out.println("Program already exists");
                String t = optionalID.get();
            }
            return therapistDAO.save(therapist);
        } catch (HibernateException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error while saving therapist: " + e.getMessage());
        }
    }

    @Override
    public boolean updateTherapist(DoctorDTO doctorDTO) {
        try {
            Therapist therapist = new Therapist();
            therapist.setDoctorID(doctorDTO.getDoctorID());
            therapist.setDoctorName(doctorDTO.getDoctorName());
            therapist.setDoctorQualifications(doctorDTO.getDoctorQualifications());
            therapist.setDoctorAvailability(doctorDTO.getDoctorAvailability());
            therapist.setDoctorPhone(doctorDTO.getDoctorPhone());
            therapist.setDoctorEmail(doctorDTO.getDoctorEmail());

            return therapistDAO.update(therapist);
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Class not found Error");
        }
    }

    @Override
    public boolean deleteTherapist(String DoctorID) {
        try {
            return therapistDAO.deleteByPk(DoctorID);
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found Error");
        }
    }

    @Override
    public String getNextTherapyID() {
        Optional<String> lastPkOptional = therapistDAO.getLastPK();
        if (lastPkOptional.isPresent()) {
            String lastPk = lastPkOptional.get();
            int nextId = Integer.parseInt(lastPk.replace("T", "")) + 1;
            return String.format("T%03d", nextId);
        } else {
            return "T001";  // Default if no records exist
        }
    }

    @Override
    public List<DoctorDTO> getDocNames() throws Exception {
        List<Therapist> therapists = therapistDAO.getAll();
        List<DoctorDTO> docNames = new ArrayList<>();
        for (Therapist therapist : therapists) {
            String therapyPID = (therapist.getTPrograms() != null) ? therapist.getTPrograms().getTherapyID() : null;

            DoctorDTO doctorDTO = new DoctorDTO(
                    therapist.getDoctorID(),
                    therapist.getDoctorName(),
                    therapyPID,
                    therapist.getDoctorQualifications(),
                    therapist.getDoctorAvailability(),
                    therapist.getDoctorPhone(),
                    therapist.getDoctorEmail()
            );
            docNames.add(doctorDTO);
        }
        return docNames;
    }
}
