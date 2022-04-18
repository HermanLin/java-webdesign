import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    static final int DEFAULT_PORT = 5190;
    Socket sock;
    Scanner sin;
    OutputStream sout;

    Client() {}
    Client(String address) {
        try {
            sock = new Socket(address, DEFAULT_PORT);
            sin = new Scanner(sock.getInputStream()); 
            sout = sock.getOutputStream();
        } catch (IOException e) {
            // TODO: implement error handling
        }
    }

    public String read() {
        String message = "";
        
        while (sin.hasNext())
            message += sin.nextLine();
        return message;
    }

    public Boolean write(String message) {
        byte[] buffer = message.getBytes();

        try {
            sout.write(buffer);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}