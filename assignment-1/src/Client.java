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
            // Creating a server socket to connect to sensors
            ServerSocket sv = new ServerSocket(8090);

            // accept connection from sensors
            Socket sensor_socket = sv.accept();
            // create socket to connect to the server
            Socket s = new Socket("127.0.0.1", 8080);
            System.out.println("Connecting to sensors...");
            // Create I/O streams for server
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {
                // block until connection
                Socket s1 = sv.accept();
                System.out.println("Sensor Connected...");
                // open thread for this socket
                UtilityHandler ch = new UtilityHandler(s);
                Thread t = new Thread(ch);
                t.start();

                System.out.println("Sensor Connection established...");

                // ask the
                dos.writeUTF("Do you want to close the connection? (y/n)");
                dos.flush();
                String sensor_msg = dis.readUTF();
                if (sensor_msg.equals("y")) {
                    System.out.println("Session ended");
                    break;
                }
                // take command from usr and send to the server
                // String usr_msg = sc.next();
                // dos.writeUTF(usr_msg);
                // dos.flush();
            }
            // close the connections
            dis.close();
            dos.close();
            s.close();
            sensor_socket.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
