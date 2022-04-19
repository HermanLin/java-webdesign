import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    static final int DEFAULT_PORT = 5190;
    Socket sock;
    SocketChannel socketChannel;
    SocketAddress socketAddr;
    Scanner sin;
    OutputStream sout;

    Client(String address, String username) {
        try {
            sock = new Socket(address, DEFAULT_PORT);
            sin = new Scanner(sock.getInputStream()); 
            sout = sock.getOutputStream();
            
            sout.write((username + "\r\n").getBytes());
        } catch (IOException e) {
            // TODO: implement error handling
        }
    }

    public String read() {
        String message = sin.nextLine();
        System.out.println("Message received: " + message);
        return message;
    }

    public Boolean write(String message) {
        try {
            sout.write(message.getBytes());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}