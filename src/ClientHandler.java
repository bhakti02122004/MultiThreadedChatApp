import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName; // To store the client's name

    // A static, thread-safe list to hold all client writers
    // This is how we broadcast messages!
    private static final List<PrintWriter> allClientWriters = Collections.synchronizedList(new ArrayList<>());

    public ClientHandler(Socket socket) {
        try {
            this.clientSocket = socket;
            // Reader for incoming messages from the client
            this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Writer for sending messages back to this client
            this.writer = new PrintWriter(clientSocket.getOutputStream(), true); // true for auto-flush

            // Ask for the client's name first
            writer.println("Enter your name:");
            this.clientName = reader.readLine();

            // Add this client's writer to the broadcast list
            allClientWriters.add(writer);
            System.out.println(clientName + " has joined the chat.");
            broadcastMessage("SERVER: " + clientName + " has joined the chat.");

        } catch (IOException e) {
            System.err.println("ClientHandler error: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String clientMessage;
        try {
            // Continuously read messages from the client
            while ((clientMessage = reader.readLine()) != null) {
                // Broadcast the message to all clients
                String messageToSend = clientName + ": " + clientMessage;
                broadcastMessage(messageToSend);
                System.out.println("Message from " + clientName + ": " + clientMessage);
            }
        } catch (IOException e) {
            // Client disconnected (e.g., closed the window)
        } finally {
            // Clean up when client disconnects
            allClientWriters.remove(writer);
            broadcastMessage("SERVER: " + clientName + " has left the chat.");
            System.out.println(clientName + " has disconnected.");
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Sends a message to every single connected client
    private void broadcastMessage(String message) {
        // We must iterate over the synchronized list in a synchronized block
        synchronized (allClientWriters) {
            for (PrintWriter clientWriter : allClientWriters) {
                clientWriter.println(message);
            }
        }
    }
}