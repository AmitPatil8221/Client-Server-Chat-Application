package ChatApplication;

/*

MULTITHREADED CHAT APPLICATION

1. Build A Client-Server Chat Application Using Java Sockets And Multithreading To Handle Multiple Users.
2. Deliverable: A Functional Chat Application With A Server And Multiple Clients Communicating In Real Time.

This is the server-side main class. It listens for client connections and spawns a new thread for each client.

*/

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    // List to keep track of all connected clients
    private static final List<ClientHandler> clientHandlers = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        int port = 12345; // Port number where server listens

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("✅ Server started on port " + port);

            // Continuously accept client connections
            while (true) {
                Socket socket = serverSocket.accept(); // Accept incoming client
                System.out.println("🔗 New client connected: " + socket);

                // Create a new handler for the connected client
                ClientHandler handler = new ClientHandler(socket, clientHandlers);

                // Add to a list of active clients
                clientHandlers.add(handler);

                // Start handler on a new thread
                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
