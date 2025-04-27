package lk.ijse.project.mentalHealthTherapyCenter.config;


import lk.ijse.project.mentalHealthTherapyCenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final  SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration().configure();/* loads settings from your hibernate.cfg.xml file.*/
        configuration.addAnnotatedClass(Appointments.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Therapist.class);
        configuration.addAnnotatedClass(TPrograms.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(ProgramDetails.class);
        /*Hibernate must know which Java classes (entities) are mapped to database tables.
        telling Hibernate about all your entity classes*/
        sessionFactory = configuration.buildSessionFactory();
        /*After adding all entity classes, build the SessionFactory*/
    }
    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;/*return an instance*/
    }
    public Session getSession() {
        return sessionFactory.openSession();
        /* opens a new Hibernate Session.Every time you need to connect to the database , call this method.
:*/
    }
}
