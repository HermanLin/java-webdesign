import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    static final int DEFAULT_PORT = 5190;


    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(DEFAULT_PORT);

            while(true) {
                Socket clientSock = ss.accept();
                new Connection(clientSock).start();
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
                //sout.println(line);
                System.out.println(line);
                line = sin.nextLine();
            }

            clientSock.close();
        } catch (IOException e) {}
    }
}