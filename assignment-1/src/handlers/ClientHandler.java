package handlers;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    Socket s;

    public ClientHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            // create I/O streams
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // IO with client
            while (true) {
                // request the starting point
                dos.writeUTF("Please enter the starting point");
                dos.flush();
                String start = dis.readUTF();
                // request the destination
                dos.writeUTF("Please enter the destination");
                dos.flush();
                String destination = dis.readUTF();
                String usr_choice = dis.readUTF();
                dos.flush();
                if (usr_choice.equalsIgnoreCase("n")) {
                    dos.writeUTF("bye");
                    System.out.println("Client disconnected");
                    dos.flush();
                    break;
                }
            }

            // close connection
            dis.close();
            dos.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
