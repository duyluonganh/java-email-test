package com.katalon.example.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {

    public static void main(String [] args) throws Exception {

        // Recipient's email ID needs to be mentioned.
        String to = "recipient-email";

        // Sender's email ID needs to be mentioned
        String from = "user-email";
        String username = "user-email";
        String password = "user-password";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.office365.com");



        // Get the default Session object.
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set Subject: header field
        message.setSubject("Katalon sample email test!");

        // Now set the actual message
        message.setText("This is actual message");

        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");
    }
}