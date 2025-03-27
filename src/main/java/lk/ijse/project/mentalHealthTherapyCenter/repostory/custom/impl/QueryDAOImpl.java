package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.TPrograms;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<TPrograms> getALLTherapists() {
        Session session = FactoryConfiguration.getInstance().getSession();
        if (session == null) {
            return new ArrayList<>();  // Return an empty list if session is null
        }
        List<TPrograms> tPrograms = new ArrayList<>();
        try {
            Query<TPrograms> query = session.createQuery("SELECT t FROM TPrograms t JOIN t.therapist p", TPrograms.class);
            tPrograms = query.getResultList();  // Fetch the result list
        } catch (Exception e) {
            e.printStackTrace();  // Log any exception for debugging
        } finally {
            session.close();  // Ensure session is closed after the operation
        }
        return tPrograms;
    }
}
