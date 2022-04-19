import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    static final int DEFAULT_PORT = 5190;
    // dynamic array allows for n clients
    private static ArrayList<Connection> clients;

    public static void main(String[] args) {
        clients = new ArrayList<Connection>();

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

    private void writeToAll(String message) {
        for (Connection c : clients) {
            c.sout.println(clientName + ": " + message);
        }
    }
    
    public void run() {
        try {
            clientName = sin.nextLine().strip();

            try { 
                String line = sin.nextLine(); 
                while (line.strip().compareToIgnoreCase("LOGOUT") != 0) {
                    writeToAll(line);
                    try { line = sin.nextLine(); }
                    catch (Exception e) { break; }
                }
            } catch (Exception e) {
            } finally { 
                clientSock.close(); 
                clients.remove(this);
            }
        } catch (IOException e) {}
    }
}