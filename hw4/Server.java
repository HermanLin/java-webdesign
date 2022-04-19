// Herman Lin
/******************************************************************* 
Server.java

This is the Server of a simple Client/Server chat room service.
*******************************************************************/

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

            // accept all valid connections to this server
            // and add them to the list of all clients
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
            // Error when getting the input/output stream only really
            // happens when something happens to the socket (i.e. the
            // Client is being closed). Ignored for this assignment.
        }
    }

    /*
        Write a message received from the Client to all clients on
        the server
    */
    private void writeToAll(String message) {
        for (Connection c : clients) {
            c.sout.println(clientName + ": " + message);
        }
    }
    
    @Override
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
            } finally { // if the socket closes or Client sends "LOGOUT"
                clientSock.close(); 
                clients.remove(this);
            }
        } catch (IOException e) {}
    }
}