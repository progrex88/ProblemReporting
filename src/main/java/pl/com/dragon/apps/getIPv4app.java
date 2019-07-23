package pl.com.dragon.apps;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class getIPv4app {

    private static final Pattern Pdot = Pattern.compile(Pattern.quote("."));

    public static ArrayList<String> getIPv4(){
        ArrayList<String> IPs = new ArrayList<String>();

        Enumeration<NetworkInterface> interfaces = null;
        try{
            interfaces = NetworkInterface.getNetworkInterfaces();

        }catch (SocketException e1){
            e1.printStackTrace();
        }

        while (interfaces.hasMoreElements()){
            NetworkInterface thisinterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = thisinterface.getInetAddresses();
            while(addresses.hasMoreElements()){
                String IP = addresses.nextElement().getHostAddress();
                if(Pdot.split(IP).length ==4 && !IP.equals("127.0.0.1")){
                    //255.255.255.255 = 4 dots
                    IPs.add(IP);
                }
            }

        }
        return IPs;
    }
}
