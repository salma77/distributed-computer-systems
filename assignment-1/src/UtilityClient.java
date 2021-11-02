import java.io.*;
import java.net.*;
import java.util.*;

public class UtilityClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create socket to connect to the client at port 8090
            Socket s = new Socket("127.0.0.1", 8090);
            // Create I/O streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // IO with client
            while (true) {
                // receive client command & print to user
                String client_msg = dis.readUTF();
                if (client_msg.equals("bye")) {
                    System.out.println("Session ended, bye");
                    break;
                }
                System.out.println(client_msg);
                // take command from usr and send to the client
                String usr_msg = sc.next();
                dos.writeUTF(usr_msg);
                dos.flush();
            }
            // close the connections
            dis.close();
            dos.close();
            s.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
