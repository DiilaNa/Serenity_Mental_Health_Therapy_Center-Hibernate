module lk.ijse.project.mentalHealthTherapyCenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires net.sf.jasperreports.core;
    requires java.mail;
    requires mysql.connector.j;
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires spring.security.core;
    requires spring.security.crypto;
    requires java.desktop;
    opens lk.ijse.project.mentalHealthTherapyCenter.config to jakarta.persistence;
    opens lk.ijse.project.mentalHealthTherapyCenter.entity to org.hibernate.orm.core;

    exports lk.ijse.project.mentalHealthTherapyCenter.entity;

    opens lk.ijse.project.mentalHealthTherapyCenter to javafx.fxml;
    exports lk.ijse.project.mentalHealthTherapyCenter;
    exports lk.ijse.project.mentalHealthTherapyCenter.controller;
    opens lk.ijse.project.mentalHealthTherapyCenter.controller to javafx.fxml;
    opens lk.ijse.project.mentalHealthTherapyCenter.dto.TM to javafx.base;
    exports lk.ijse.project.mentalHealthTherapyCenter.controller.popups;
    opens lk.ijse.project.mentalHealthTherapyCenter.controller.popups to javafx.fxml;
    exports lk.ijse.project.mentalHealthTherapyCenter.controller.Login;
    opens lk.ijse.project.mentalHealthTherapyCenter.controller.Login to javafx.fxml;

    exports lk.ijse.project.mentalHealthTherapyCenter.dto to org.hibernate.orm.core;
    exports lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses;
    opens lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses to javafx.fxml;

}