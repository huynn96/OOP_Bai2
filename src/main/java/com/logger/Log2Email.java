package com.logger;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

public class Log2Email implements ILogger {
    private static final String SMTP_SERVER = "gmail.com";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String EMAIL_FROM = "huyteonb@gmail.com";
    private static final String EMAIL_TO = "huynnhu1996@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";

    public Log2Email() {

    }

    public void Write(String mes) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timestamp = simpleDateFormat.format(new Date());
        final String username = "huyteonb@gmail.com";
        final String password = "MDk4MzkwNjEwOQ==";
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedString = new String(decodedBytes);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, decodedString);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("huyteonb@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("huynnhu1996@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText(String.format("%s  %s\n", timestamp, message));

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
