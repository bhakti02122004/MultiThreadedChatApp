# Multi-Threaded Chat Application - CodTech Task 3

This is a classic client-server chat application written in Java for **CodTech Internship Task 3**. It demonstrates the use of Java Sockets for networking and Java Concurrency (multithreading) to handle multiple clients at the same time.

## 🚀 Features

* **Central Server:** A single server that listens for and accepts client connections.
* **Multithreading:** The server creates a new thread for each connected client, allowing it to handle multiple users simultaneously.
* **Real-time Chat:** Messages sent by one client are instantly broadcast to all other connected clients.
* **User Naming:** The server prompts each user for a name upon connection.
* **Join/Leave Notifications:** All clients are notified when a new user joins or an existing user leaves the chat.

## 🛠️ Technologies Used

* **Java**
* **Java Networking (`java.net.Socket`, `java.net.ServerSocket`)**
* **Java Concurrency (`java.lang.Thread`, `java.lang.Runnable`)**
* **Java IO (`BufferedReader`, `PrintWriter`)**

## ⚡ How to Run

You must run the `Server` first, and then run as many `Client` instances as you like.

### Prerequisites

* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/)
* [Git](https://git-scm.com/downloads)

### Running from the Command Line

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/](https://github.com/)[YOUR_USERNAME]/CodTech-Task-3-ChatApp.git
    cd CodTech-Task-3-ChatApp
    ```

2.  **Compile all the Java files:**
    ```sh
    javac src/Server.java src/ClientHandler.java src/Client.java
    ```

3.  **Step 1: Run the Server**
    Open a terminal and run the server. It will wait for clients.
    ```sh
    java -cp src Server
    ```
    You will see: `Chat Server started...`

4.  **Step 2: Run a Client**
    Open a **new, separate terminal** and run the client.
    ```sh
    java -cp src Client
    ```
    It will connect and ask for your name.

5.  **Step 3: Run More Clients**
    Open *another* new terminal and run the client command again. You can do this as many times as you want. All clients will be able to chat with each other in real-time.
