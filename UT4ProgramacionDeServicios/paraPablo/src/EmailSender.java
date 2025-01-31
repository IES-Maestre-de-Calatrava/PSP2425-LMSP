import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public void sendEmail(String recipient, String subject, String body) {
        //Crear Contraseñas de aplicación: java
        final String username = "lm.serrano.p@gmail.com";
        final String password = "xlgp cghh ubxj urlp";

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", "587");
        props.put("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        try {
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    }
            );
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);

            // set the message content here
            Transport.send(message);
            System.out.println("Email sent successfully to: " + recipient);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String email = "pablovelascoruiz68@gmail.com";
        String subject = "Rata";
        String body = "Hola,\nlas ratas son animales bellos en algunas culturas como la Akapatelka de la isla de Wiharouh, y además son animales sabrosos cocinado s a fuego lento.";

        // Sending email without attachment
        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail(email, subject, body);
    }
}