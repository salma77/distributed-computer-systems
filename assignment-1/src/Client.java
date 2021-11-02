import java.io.*;
import java.net.*;
import java.util.*;

import handlers.UtilityHandler;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Connecting Client to server
        // and connecting client to the sensors
        try {
            // create socket to connect to the server
            Socket client_socket = new Socket("127.0.0.1", 8080);
            System.out.println("Waiting for connection with utility clients...");
            
            // Creating a server socket to connect to sensors
            ServerSocket sv = new ServerSocket(8090);
            
            // Create I/O streams for server
            DataInputStream dis = new DataInputStream(client_socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(client_socket.getOutputStream());

            
            while (true) {
                // accept connection from sensors
                // block until connection
                Socket sensor_socket = sv.accept();
                System.out.println("Utility Client Connected...");

                // open thread for this socket
                UtilityHandler ch = new UtilityHandler(sensor_socket);
                Thread t = new Thread(ch);
                t.start();

                // ask the user whether he wants to close
                dos.writeUTF("Do you want to close the connection? [y/n]");
                dos.flush();
                String sensor_msg = dis.readUTF();
                dos.flush();
                if (sensor_msg.equals("y")) {
                    System.out.println("yo yo yo");
                    break;
                }
            }
            // close the connections
            dis.close();
            dos.close();
            client_socket.close();
            // sensor_socket.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
