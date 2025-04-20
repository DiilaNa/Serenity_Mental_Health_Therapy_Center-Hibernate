package lk.ijse.project.mentalHealthTherapyCenter.controller.Login.UtilClasses;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    public static void sendEmailWithGmail(String Email, String username) {
        final String From = "dilandark602@gmail.com";
        final String password = "jkfn whfi vvgb dwcz";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(From, password);
            }
        });
        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(From));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email));
            message.setSubject("Security Alert: Account Credentials Changed");

            String content = "Dear " + username + ",\n\n" +
                    "We noticed a change to your username or password. If this was you, no action is needed.\n" +
                    "If you did not make this change, please contact our support immediately.\n\n" +
                    "Best regards,\nSerenity Mental Health Therapy Center";
            message.setText(content);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
        }
    }
}
