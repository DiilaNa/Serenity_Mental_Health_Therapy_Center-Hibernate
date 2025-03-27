package lk.ijse.project.mentalHealthTherapyCenter.service.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.dto.DoctorDTO;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOFactory;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.DAOType;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.TherapistDAO;
import lk.ijse.project.mentalHealthTherapyCenter.service.custom.TherapistBO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistBOImpl implements TherapistBO {
    TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOType.THERAPIST);

    @Override
    public List<DoctorDTO> getALLDoctors() throws Exception {
        List<Therapist> therapists = therapistDAO.getAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();

        for (Therapist therapist : therapists) {
            DoctorDTO dto = new DoctorDTO(
                    therapist.getDoctorID(),
                    therapist.getDoctorName(),
                    therapist.getDoctorQualifications(),
                    therapist.getDoctorAvailability(),
                    therapist.getDoctorPhone(),
                    therapist.getDoctorEmail()
            );
            doctorDTOS.add(dto);
        }
        return doctorDTOS;
    }

    @Override
    public boolean saveTherapist(DoctorDTO doctorDTO) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Therapist therapist = new Therapist();
            therapist.setDoctorID(doctorDTO.getDoctorID());
            therapist.setDoctorName(doctorDTO.getDoctorName());
            therapist.setDoctorQualifications(doctorDTO.getDoctorQualifications());
            therapist.setDoctorAvailability(doctorDTO.getDoctorAvailability());
            therapist.setDoctorPhone(doctorDTO.getDoctorPhone());
            therapist.setDoctorEmail(doctorDTO.getDoctorEmail());

            boolean isSaved =  therapistDAO.save(therapist,session);
            if (isSaved) {
                transaction.commit();
                return true;
            }else{
                transaction.rollback();
                return false;
            }

        } catch (HibernateException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error while saving therapist: " + e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateTherapist(DoctorDTO doctorDTO) {
        System.out.println(doctorDTO.getDoctorID());
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = new Therapist();
            therapist.setDoctorID(doctorDTO.getDoctorID());
            therapist.setDoctorName(doctorDTO.getDoctorName());
            therapist.setDoctorQualifications(doctorDTO.getDoctorQualifications());
            therapist.setDoctorAvailability(doctorDTO.getDoctorAvailability());
            therapist.setDoctorPhone(doctorDTO.getDoctorPhone());
            therapist.setDoctorEmail(doctorDTO.getDoctorEmail());
            System.out.println("BO........."+therapist);
            boolean isUpdated = therapistDAO.update(therapist,session);
            if (isUpdated) {
                transaction.commit();
                return true;
            }else {
                System.out.println("not updatd therapist");
                transaction.rollback();
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL error while saving therapist");
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Class not found Error");
        }finally {
            session.close();
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
            DoctorDTO doctorDTO = new DoctorDTO(
                    therapist.getDoctorID(),
                    therapist.getDoctorName(),
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
