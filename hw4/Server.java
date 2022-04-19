import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    static final int DEFAULT_PORT = 5190;
    // dynamic array allows for n clients
    //private ArrayList<Connection> clients;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(DEFAULT_PORT);
            //clients = new ArrayList<Connection>();

            while(true) {
                Socket clientSock = ss.accept();
                Connection clientConn = new Connection(clientSock);
                //clients.add(clientConn);
                clientConn.start();
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port 5190");
        }
    }
}

class Connection extends Thread {

    Socket clientSock;
    String clientName;

    Connection(Socket newClientSock) {
        clientSock = newClientSock;
    }

    public void run() {
        try {
            Scanner sin = new Scanner(clientSock.getInputStream());
            PrintStream sout = new PrintStream(clientSock.getOutputStream());
    
            clientName = sin.nextLine().strip();
            System.out.println("Connection Made with " + clientName);

            String line = sin.nextLine();
            while (line.strip().compareToIgnoreCase("LOGOUT") != 0) {
                System.out.println(clientName + ": " + line);
                sout.println(line);
                line = sin.nextLine();
            }

            clientSock.close();
        } catch (IOException e) {}
    }
}