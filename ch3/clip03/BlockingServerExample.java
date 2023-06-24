import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServerExample {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Started server and port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected Client: " + clientSocket.getInetAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine = reader.readLine();
                System.out.println("Received message: " + inputLine);

                clientSocket.close();
                System.out.println("Closed Client: " + clientSocket.getInetAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
