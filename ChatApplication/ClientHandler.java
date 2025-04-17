package ChatApplication;

/*
 * Handles a single client communication on its own thread.
 * Receives messages from one client and broadcasts to all other clients.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;
    private List<ClientHandler> clientHandlers;

    public ClientHandler(Socket socket, List<ClientHandler> clientHandlers) {
        this.socket = socket;
        this.clientHandlers = clientHandlers;
    }

    @Override
    public void run() {
        try {
            // Set up input and output streams for communication
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Ask for and read client's name
            out.println("Enter your name:");
            clientName = in.readLine();
            System.out.println("👤 " + clientName + " has joined the chat.");

            // Notify all users about the new participant
            broadcastMessage("🟢 " + clientName + " has joined the chat.", this);

            String message;
            // Continuously read messages from this client
            while ((message = in.readLine()) != null) {
                // Broadcast the received message to all other clients
                broadcastMessage("[" + clientName + "]: " + message, this);
            }

        } catch (IOException e) {
            System.out.println("❌ Connection lost with " + clientName);
        } finally {
            try {
                socket.close(); // Close socket
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Remove client from list and notify others
            clientHandlers.remove(this);
            broadcastMessage("🔴 " + clientName + " has left the chat.", this);
        }
    }

    // Send a message to all connected clients except the sender
    private void broadcastMessage(String message, ClientHandler sender) {
        synchronized (clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                if (handler != sender) {
                    handler.out.println(message); // Send message
                }
            }
        }
    }
}
