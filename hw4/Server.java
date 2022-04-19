import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    static final int DEFAULT_PORT = 5190;
    // dynamic array allows for n clients
    private static ArrayList<Connection> clients;

    public static void main(String[] args) {
        clients = new ArrayList<Connection>();

        // start thread to manage live connections;
        Manager manager = new Manager(clients);
        manager.start();
        
        try {
            ServerSocket ss = new ServerSocket(DEFAULT_PORT);

            while(true) {
                Socket clientSock = ss.accept();
                Connection clientConn = new Connection(clientSock, clients);
                clientConn.start();
                clients.add(clientConn);
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port 5190");
        }
    }
}

class Manager extends Thread {

    ArrayList<Connection> clients;

    Manager(ArrayList<Connection> clientList) {
        clients = clientList;
    }

    public void run() {
        while(true) {
            ArrayList<Connection> newClientList = new ArrayList<Connection>();
            for (Connection c : clients) {
                if (c.isAlive()) { newClientList.add(c); }
            }
            clients = newClientList;
        }
    }
}

class Connection extends Thread {

    Socket clientSock;
    String clientName;
    Scanner sin;
    PrintStream sout;
    ArrayList<Connection> clients;

    Connection(Socket newClientSock,
               ArrayList<Connection> clientList) {
        try {
            clientSock = newClientSock;
            sin = new Scanner(clientSock.getInputStream());
            sout = new PrintStream(clientSock.getOutputStream());

            clients = clientList;
        } catch (IOException e) {
            // TODO: implement error handling
        }
    }

    public Boolean write(String message) {
        sout.println(message);
        return true;
    }

    private void writeToAll(String message) {
        for (Connection c : clients) {
            System.out.println("Writing to " + c.clientName + ": " + message);
            c.write(clientName + ": " + message);
        }
    }
    
    public void run() {
        try {
            clientName = sin.nextLine().strip();
            System.out.println("Connection Made with " + clientName);

            try { 
                String line = sin.nextLine(); 
                while (line.strip().compareToIgnoreCase("LOGOUT") != 0) {
                    System.out.println(clientName + ": " + line);
                    writeToAll(line);
                    try { line = sin.nextLine(); }
                    catch (Exception e) { break; }
                }
            } catch (Exception e) {}

            clientSock.close();
        } catch (IOException e) {}
    }
}