package com.moda.utils;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;

public class EmailSender {
    private EmailSender() {
        throw new IllegalStateException("Utility class");
    }
    public static void sendEmailHTMLFile(File htmlFile, boolean isAttachment){
        sendEmail(null, htmlFile, isAttachment);
    }

    public static void sendEmailPDFFile(DataSource pdfFile, boolean isAttachment){
        sendEmail(pdfFile, null, isAttachment);
    }

    private static void sendEmail(DataSource pdfFile, File htmlFile, boolean isAttachment) {
        // SMTP server configuration
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = ConfigFileReader.getConfigPropertyValue("mailFrom"); // Your Gmail address
        String password = ConfigFileReader.getConfigPropertyValue("mailPassword"); // Your Gmail password

        // Recipient's email address
        String mailTo = ConfigFileReader.getConfigPropertyValue("mailTo");

        // Email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Session to authenticate the sender
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        });

        try {
            // Create MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(mailFrom));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

            // Set Subject: header field
            message.setSubject("TestNG Report");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Set the actual message
            if (pdfFile != null) {
                messageBodyPart.setText("Please find the attached TestNG report.");
                DataSource source = pdfFile;
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(pdfFile.getName());
                multipart.addBodyPart(messageBodyPart);
            } else if (htmlFile != null) {
                messageBodyPart.setText("Please find the TestNG report below:");
                multipart.addBodyPart(messageBodyPart);
                DataSource source = new FileDataSource(htmlFile);

                // Add message parts to multipart
                if (isAttachment){
                    MimeBodyPart htmlAttachmentPart = new MimeBodyPart();
                    htmlAttachmentPart.setDataHandler(new DataHandler(source));
                    htmlAttachmentPart.setFileName(htmlFile.getName());
                    multipart.addBodyPart(htmlAttachmentPart);
                } else {
                    messageBodyPart.setDataHandler(new DataHandler(source));
                }

            }

            // Set the multipart message to the email message
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            LogHelper.getLogger().info("Email Sent Successfully");
        } catch (MessagingException e) {
            LogHelper.getLogger().error(e.getMessage());
        }
    }

}
