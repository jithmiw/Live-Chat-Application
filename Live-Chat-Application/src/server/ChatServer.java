package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable {
    private ChatServerThread[] clients = new ChatServerThread[50];
    private ServerSocket server = null;
    private Thread thread = null;
    private int clientCount = 0;

    public ChatServer(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            start();
        } catch (IOException e) {
            System.out.println("Cannot bind to port " + port + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ChatServer(5000);
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            } catch (IOException ioe) {
                System.out.println("Server accept error: " + ioe);
                stop();
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread = null;
        }
    }

    public synchronized void handle(int ID, String input) {
        for (int i = 0; i < clientCount; i++) {
            if (clients[i].getID() == ID) {//This prevents the person's own message to show up again.
                continue;
            }
            clients[i].send(input);
        }
    }

    private void addThread(Socket socket) {
        if (clientCount < clients.length) {
            System.out.println("Client accepted: " + socket);
            clients[clientCount] = new ChatServerThread(this, socket);
            try {
                clients[clientCount].open();
                clients[clientCount].start();
                clientCount++;
            } catch (IOException e) {
                System.out.println("Error opening thread: " + e);
            }
        } else {
            System.out.println("Client refused: maximum " + clients.length + " reached.");
        }
    }

    public ChatServerThread[] getClients() {
        return clients;
    }

    public void setClients(ChatServerThread[] clients) {
        this.clients = clients;
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }
}
