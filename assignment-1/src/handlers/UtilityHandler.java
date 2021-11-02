package handlers;

import java.io.*;
import java.net.*;

public class UtilityHandler implements Runnable {

    Socket s;

    public UtilityHandler(Socket s) {
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
                // Perform calculations to get the best route
                String best_route = getBestRoute(start, destination);
                dos.writeUTF("The best route would be " + best_route + "\n Start Over?[y/n]?");
                dos.flush();
                String usr_choice = dis.readUTF();
                dos.flush();
                if (usr_choice.equalsIgnoreCase("n")) {
                    dos.writeUTF("bye");
                    System.out.println("Utility Client disconnected");
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

    public String getBestRoute(String start, String destination) {
        // dummy function for calculating best route
        return "Northeast";
    }
}
