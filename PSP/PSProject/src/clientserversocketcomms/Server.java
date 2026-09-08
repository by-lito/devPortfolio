package clientserversocketcomms;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        int port = 5000;

        try {
            // 1. Open server socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server waiting for connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            // 2. Receive 4 bytes of the IP address
            int[] ipParts = new int[4];
            for (int i = 0; i < 4; i++) {
                ipParts[i] = in.readInt();
            }

            // 3. Reconstruct and print IP address
            String ipAddress = ipParts[0] + "." +
                    ipParts[1] + "." +
                    ipParts[2] + "." +
                    ipParts[3];

            System.out.println("Received IP address: " + ipAddress);

            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}