# 💬 Multithreaded Chat Application

A simple and functional Client-Server Chat Application built using Java Sockets and Multithreading. This project allows multiple users to connect to a central server and communicate with each other in real-time.

## 🚀 Features

- Real-time communication between multiple clients  
- Server handles each client with a dedicated thread  
- Broadcast messages to all connected users  
- Join and leave notifications  
- Simple and clean console interface  

## 🛠️ How It Works

This project contains three main Java files inside a package called `ChatApplication`:

1. **ChatServer.java**  
   - Starts the server on port 12345  
   - Accepts multiple client connections  
   - Uses threads to handle each client separately  
   - Stores all connected clients in a synchronized list  
   - Broadcasts messages from one client to all others  

2. **ClientHandler.java**  
   - Runs in its own thread per client  
   - Reads messages from the connected client  
   - Sends received messages to all other clients  
   - Notifies when a user joins or leaves  

3. **ChatClient.java**  
   - Connects to the chat server  
   - Sends user input to the server  
   - Listens for and displays messages from the server in real-time  

## 📂 Folder Structure

All files must be placed inside a package named `ChatApplication`.

ChatApplication/  
├── ChatServer.java  
├── ChatClient.java  
└── ClientHandler.java  

## 🧪 How to Run

1. Open your terminal or IDE and navigate to the directory containing the `ChatApplication` folder.

2. Compile all Java files:
   javac ChatApplication/*.java

3. Start the server:
   java ChatApplication.ChatServer

4. Open new terminal windows or tabs for clients and run:
   java ChatApplication.ChatClient

Each client will be prompted to enter a name and then can chat in real-time with other users.

## 💡 Sample Output

Client 1:  
Enter your name:  
Alice  
🟢 Alice has joined the chat.  

Client 2:  
Enter your name:  
Bob  
🟢 Bob has joined the chat.  

[Alice]: Hi Bob!  
[Bob]: Hello Alice!  

## ✅ Requirements

- Java 8 or above  
- Any IDE (like IntelliJ IDEA, Eclipse) or command line tools  
- Basic knowledge of Java, Sockets, and Multithreading  

## 🧑‍💻 Author

Amit Patil  
amitvpatil8221@gmail.com  
https://github.com/AmitPatil8221  

## 📄 License

This project is licensed under the MIT License. You are free to use, modify, and share it for learning or development purposes.

## 🌟 Future Enhancements

- Private messaging (DMs)  
- Timestamped messages  
- GUI using Java Swing or JavaFX  
- Save chat logs to file  
- Add sound notifications for incoming messages  
