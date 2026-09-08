package clientserversocketcomms;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        String hostToResolve = "www.paraninfo.es";
        String serverAddress = "localhost";
        int serverPort = 5000;

        try {
            // 1. Obtain IP address using InetAddress
            InetAddress inet = InetAddress.getByName(hostToResolve);
            byte[] ipBytes = inet.getAddress(); // 4 bytes for IPv4

            System.out.println("Resolved IP: " + inet.getHostAddress());

            // 2. Open socket connection to server
            Socket socket = new Socket(serverAddress, serverPort);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // 3. Send IP byte by byte (as integers)
            for (byte b : ipBytes) {
                out.writeInt(b & 0xFF); // convert signed byte to unsigned int
            }

            System.out.println("IP address sent to server.");

            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
