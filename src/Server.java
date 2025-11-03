import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // The port the server will listen on
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Chat Server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            // This is the main server loop. It waits for new connections.
            while (true) {
                // accept() blocks until a new client connects
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());

                // Create a new thread to handle this client
                // This is the multithreading part!
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}