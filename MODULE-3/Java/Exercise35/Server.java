import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Server started on port " + PORT);
        System.out.println();

        try (ServerSocket server = new ServerSocket(PORT)) {

            Socket remote = server.accept();
            System.out.println("Remote client connected.");
            System.out.println();

            DataInputStream input = new DataInputStream(remote.getInputStream());
            DataOutputStream output = new DataOutputStream(remote.getOutputStream());

            String received = input.readUTF();
            System.out.println("Received from client:");
            System.out.println(received);
            System.out.println();

            String reply = "Greetings from server!";
            output.writeUTF(reply);
            System.out.println("Sent to client:");
            System.out.println(reply);

            remote.close();

        } catch (IOException e) {
            System.out.println("Server failure: " + e.getMessage());
        }
    }
}
