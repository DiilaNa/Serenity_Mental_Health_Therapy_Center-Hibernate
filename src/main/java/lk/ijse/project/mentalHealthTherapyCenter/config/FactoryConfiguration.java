package lk.ijse.project.mentalHealthTherapyCenter.config;


import lk.ijse.project.mentalHealthTherapyCenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Appointments.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Therapist.class)
                .addAnnotatedClass(TPrograms.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(AppointmentDetails.class)
                .addAnnotatedClass(ProgramDetails.class);

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
