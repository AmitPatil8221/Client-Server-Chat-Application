package ChatApplication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/*
 * Client-side application that connects to the chat server
 * sends messages typed by the user and prints messages from the server.
 */

public class ChatClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server IP
        int port = 12345; // Server Port

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("✅ Connected to the chat server.");

            // Create input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            // Thread to read messages from server and print to console
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg); // Display message from server
                    }
                } catch (IOException e) {
                    System.out.println("⚠️ Disconnected from server.");
                }
            }).start();

            // Read input from user and send to server
            while (true) {
                String userInput = scanner.nextLine();
                out.println(userInput); // Send a user message to server
            }

        } catch (IOException e) {
            System.out.println("❌ Unable to connect to server: " + e.getMessage());
        }
    }
}
