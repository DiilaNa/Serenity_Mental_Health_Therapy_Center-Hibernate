package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom;

import lk.ijse.project.mentalHealthTherapyCenter.entity.ProgramDetails;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.CrudDAO;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import org.hibernate.Session;

import java.util.List;

public interface ProgramDetailsDAO extends CrudDAO<ProgramDetails,String> {
    List <ProgramDetails> getByPatientAndSession(String patientID, String id , Session session);
}
