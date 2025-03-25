package lk.ijse.project.mentalHealthTherapyCenter.config;


import lk.ijse.project.mentalHealthTherapyCenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final  SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Appointments.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(TPrograms.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(AppointmentDetails.class);
        configuration.addAnnotatedClass(ProgramDetails.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
