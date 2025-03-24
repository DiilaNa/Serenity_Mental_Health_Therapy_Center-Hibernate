package lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.impl;

import lk.ijse.project.mentalHealthTherapyCenter.config.FactoryConfiguration;
import lk.ijse.project.mentalHealthTherapyCenter.entity.Therapist;
import lk.ijse.project.mentalHealthTherapyCenter.repostory.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public List<Therapist> getALLTherapists() {
        Session session = factoryConfiguration.getSession();
        if (session == null) {
            return new ArrayList<>();  // Return an empty list if session is null
        }
        List<Therapist> therapists = new ArrayList<>();
        try {
            Query<Therapist> query = session.createQuery("SELECT t FROM Therapist t JOIN t.tPrograms p", Therapist.class);
            therapists = query.getResultList();  // Fetch the result list
        } catch (Exception e) {
            e.printStackTrace();  // Log any exception for debugging
        } finally {
            session.close();  // Ensure session is closed after the operation
        }
        return therapists;
    }
}
