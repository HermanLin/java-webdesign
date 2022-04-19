import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    Socket sock;
    Scanner sin;
    OutputStream sout;

    Client(Socket newSocket, String username) {
        try {
            sock = newSocket;
            sin = new Scanner(sock.getInputStream()); 
            sout = sock.getOutputStream();
            
            sout.write((username + "\r\n").getBytes());
        } catch (IOException e) {
            // TODO: implement error handling
        }
    }

    public String read() {
        String message = "";
        try { message = sin.nextLine(); }
        catch (Exception e) {}
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