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



    @FXML
    static
    TextField reportTitle;

    private static status stat_1;
    private static status stat_0;
    private static JLabel sendStatusInfo;

//    @FXML
//    Label sendStatusInfo;

    enum status{
        OK, ERROR
    };





    public static void send(String from, String subject, String emailBody) throws SocketException, MessagingException {

        ServerConf serverConf = new ServerConf().invoke();
        String username = serverConf.getUsername();
        String pass = serverConf.getPass();
        Properties prop = serverConf.getProp();

        Session session = CreateSession(username, pass, prop);

        messageData(from, subject, emailBody, session);


    }




    private static void displayInterfaceInformation(NetworkInterface netint) throws SocketException{

        out.printf("Dsplay name: %s \n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());


    }



    private static void messageData(String from, String subject, String emailBody, Session session)  {
//        stat_0=status.OK;
//        stat_1=status.ERROR;

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("radek@pacek.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(from));
            message.setSubject(subject);
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);


//            sendStatusInfo.setText("OK");

            out.println("Done");


        }catch (MessagingException e){

//            sendStatusInfo.setText("ERROR");

            throw new RuntimeException(e);


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
            username = "facc6bd39b36d1";
            pass = "ae99b878bc08eb";

            prop = new Properties();
            prop.put("mail.smtp.auth","true");
            prop.put("mail.smtp.starttls.enable","enable");
//        prop.put("mail.smtp.host","ntsoe010.xnet.oe.olympus");
            prop.put("mail.smtp.host","smtp.mailtrap.io");
            prop.put("mail.smtp.port","465");
            prop.put("mail.smtp.authentication", "cram_md5");
            return this;
        }
    }



























}
