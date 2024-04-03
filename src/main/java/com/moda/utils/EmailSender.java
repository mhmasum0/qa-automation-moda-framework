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
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = ConfigFileReader.getConfigPropertyValue("mailFrom");
        String password = ConfigFileReader.getConfigPropertyValue("mailPassword");

        String mailTo = ConfigFileReader.getConfigPropertyValue("mailTo");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            message.setSubject("TestNG Report");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();

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

                if (isAttachment){
                    MimeBodyPart htmlAttachmentPart = new MimeBodyPart();
                    htmlAttachmentPart.setDataHandler(new DataHandler(source));
                    htmlAttachmentPart.setFileName(htmlFile.getName());
                    multipart.addBodyPart(htmlAttachmentPart);
                } else {
                    messageBodyPart.setDataHandler(new DataHandler(source));
                }

            }

            message.setContent(multipart);

            Transport.send(message);
            LogHelper.getLogger().info("Email Sent Successfully");
        } catch (MessagingException e) {
            LogHelper.getLogger().error(e.getMessage());
        }
    }

}
