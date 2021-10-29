import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create socket to connect to the server
            Socket s = new Socket("127.0.0.1", 8080);
            // Create I/O streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // IO with server
            while (true) {
                // receive server command & print to user
                String srvr_msg = dis.readUTF();
                if (srvr_msg.equals("bye")) {
                    System.out.println("Session ended");
                    break;
                }
                System.out.println(srvr_msg);
                // take command from usr and send to the server
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
