import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {

        try (Socket connection = new Socket(HOST, PORT)) {

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            DataInputStream input = new DataInputStream(connection.getInputStream());

            String message = "Hello from client!";
            output.writeUTF(message);
            System.out.println("Client sent:");
            System.out.println(message);
            System.out.println();

            String response = input.readUTF();
            System.out.println("Server replied:");
            System.out.println(response);

        } catch (IOException e) {
            System.out.println("Client encountered error: " + e.getMessage());
            System.out.println("Check if the server is listening.");
        }
    }
}
