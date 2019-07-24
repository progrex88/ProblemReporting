package pl.com.dragon.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pl.com.dragon.apps.getIPv4app;
import pl.com.dragon.apps.sendMailApp;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class MainReportWindowController extends HBox {

    @FXML
    private BorderPane borderPaneWindow;

    @FXML
    TextArea messageID;

    @FXML
    TextField reportTitle;

    @FXML
    Label computerID;


    @FXML
    Label userID;

    @FXML
    Label localNetworkIP;

    @FXML
    Label VPNnetworkIP;

    @FXML
    Label sendStatusInfo;

    String userString =null;

    String userMessage=null;

    String messageTitle=null;

    String sendStatus=null;

    String usercomputerID=null;

    InetAddress inetAddress = InetAddress.getLocalHost();

    public MainReportWindowController() throws UnknownHostException {
    }

    @FXML
    public void initialize() throws Exception {
        userString = System.getProperty("user.name");
        userID.setText(userString);

        String getComputerName=inetAddress.getHostName();
        computerID.setText(getComputerName);

        // getComputerInfo();
        localNetworkIP.setText(inetAddress.getHostAddress());

        ArrayList<String> IPv4s= getIPv4app.getIPv4();
        out.println(IPv4s);

    }

    private void getComputerInfo() throws  Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();

        System.out.println("IP Address:- " + inetAddress.getCanonicalHostName());
        System.out.println("IP Address:- " + inetAddress.getAddress());
        System.out.println("IP Address:- " + inetAddress.getHostAddress());
        System.out.println("Host Name:- " + inetAddress.getHostName());

        localNetworkIP.setText(inetAddress.getHostAddress());
    }

    @FXML public  void onSend() throws SocketException, MessagingException,InterruptedException{

        userMessage = messageID.getText();
        messageTitle = reportTitle.getText();
        sendStatus = sendStatusInfo.getText();
        sendMailApp.send(userString, messageTitle, userMessage, "radoslaw.pacek@olympus-europa.com");
        sendStatusInfo.setText("Sended");

        //  cleaningContent();


    }

    private void cleaningContent() throws InterruptedException {
        messageID.setText("");
        reportTitle.setText("");
        sendStatusInfo.setText("");
    }


}
