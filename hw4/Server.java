import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5190);

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

    Connection(Socket newClientSock) {
        clientSock = newClientSock;
    }


    public void run() {
        try {
            Scanner sin = new Scanner(clientSock.getInputStream());
            PrintStream sout = new PrintStream(clientSock.getOutputStream());
    
            System.out.println("Connection Made");
            String line = sin.nextLine();

            while (line.strip().compareToIgnoreCase("LOGOUT") != 0) {
                sout.println(line);
                line = sin.nextLine();
            }

            clientSock.close();
        } catch (IOException e) {}
    }
}