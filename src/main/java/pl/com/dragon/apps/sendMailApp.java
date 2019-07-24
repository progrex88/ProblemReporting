package pl.com.dragon.apps;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Properties;

import static java.lang.System.out;

public class sendMailApp extends HBox {

    public static void send(String from, String subject, String emailBody, String to) throws SocketException, MessagingException {

        ServerConf serverConf = new ServerConf().invoke();
        String username = serverConf.getUsername();
        String pass = serverConf.getPass();
        Properties prop = serverConf.getProp();
        Session session = CreateSession(username, pass, prop);
        messageData(from, subject, emailBody, session, to);


    }




    private static void displayInterfaceInformation(NetworkInterface netint) throws SocketException{
        out.printf("Dsplay name: %s \n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());
    }



    private static void messageData(String from, String subject, String emailBody, Session session, String to)  {

        try{
            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from + "@olympus-europa.com"));
            message.setFrom(new InternetAddress("radoslaw.pacek@olympus-europa.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.addHeader(" X-OTRS-Queue=ECE", " X-OTRS-State=new");
            message.setContent(emailBody, "text/html; charset=utf-8;");
            Transport.send(message);
            out.println("Done");


        }catch (MessagingException e){
            System.err.println("Can't send email. "+ e);
        }
    }



    private static Session CreateSession(String username, String pass, Properties prop) {
        return Session.getInstance(prop, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username,pass);
            }
        });
    }


    public static class ServerConf {
        private String username;
        private String pass;
        private Properties prop;

        public String getUsername() {
            return username;
        }

        public String getPass() {
            return pass;
        }

        public Properties getProp() {
            return prop;
        }

        public ServerConf invoke() {
            //username = "facc6bd39b36d1";
            //pass = "ae99b878bc08eb";



//            prop = new Properties();
//            prop.put("mail.smtp.auth","true");
//            prop.put("mail.smtp.starttls.enable","enable");
//            prop.put("mail.smtp.host","smtp.mailtrap.io");
//            prop.put("mail.smtp.port","465");

//            prop = new Properties();
//            prop.put("mail.smtp.auth","false");
//            prop.put("mail.smtp.starttls.enable","disable");
//            prop.put("mail.smtp.host","ntsoe010.xnet.oe.olympus");
//            prop.put("mail.smtp.port","25");

            prop = new Properties();
            prop.put("mail.smtp.auth","true");
            prop.put("mail.smtp.starttls.enable","true");
            prop.put("mail.smtp.host","smtp.gmail.com");
            prop.put("mail.smtp.port","587");

            return this;
        }
    }



























}
