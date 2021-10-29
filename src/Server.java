import java.io.*;
import java.net.*;

import handlers.ClientHandler;

public class Server {

    public static void main(String[] args) {
        try {
            // open a server socket
            ServerSocket sv = new ServerSocket(8080);
            System.out.println("Server waiting for a connection...");
            while (true) {
                // block until connection
                Socket s = sv.accept();
                System.out.println("Client Connected...");
                // open thread for this socket
                ClientHandler ch = new ClientHandler(s);
                Thread t = new Thread(ch);
                t.start();

            }

            // close the server
            // sv.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
