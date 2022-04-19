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
            
            // send the server the username as an acknowledgement
            // (part of the assignment requirements)
            sout.write((username + "\r\n").getBytes());
        } catch (IOException e) {
            // TODO: implement error handling
            // Error when getting the input/output stream only really
            // happens when something happens to the socket (i.e. the
            // Client is being closed). Ignored for this assignment.
        }
    }

    /*
        Read any message from the input stream
    */
    public String read() {
        String message = "";
        try { message = sin.nextLine(); }
        catch (Exception e) {}
        return message;
    }

    /*
        Write a message to the Server
    */
    public Boolean write(String message) {
        try {
            sout.write(message.getBytes());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}