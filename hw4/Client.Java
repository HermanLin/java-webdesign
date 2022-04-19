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

            //socketAddr = new InetSocketAddress(address, DEFAULT_PORT);
            //socketChannel = SocketChannel.open();
            //socketChannel.connect(socketAddr);

            //socketChannel.write(ByteBuffer.wrap((username+"\r\n").getBytes()));
        } catch (IOException e) {
            // TODO: implement error handling
        }
    }

    /*
        Read from the SocketChannel using ByteBuffers

        Decodes the custom byte-array packet used in write()
    */
    public String read() {
        /*
        ByteBuffer size = ByteBuffer.allocate(1);
        try { socketChannel.read(size); }
        catch (Exception e) {}

        ByteBuffer len = ByteBuffer.allocate(size.getInt());
        try { socketChannel.read(len); }
        catch (Exception e) {}
        
        int msgLen = Integer.parseInt(len.array().toString());
        ByteBuffer msg = ByteBuffer.allocate(msgLen);
        try { socketChannel.read(msg); }
        catch (Exception e) {}
        */
        /*
        ByteBuffer msg = ByteBuffer.allocate(100);
        try { socketChannel.read(msg); }
        catch (Exception e) {
            System.out.println(e.toString());
            return "ERROR";
        }

        return msg.array().toString();
        */
        String message = sin.nextLine();
        return message;
    }

    /*
        Write to the SocketChannel using ByteBuffers
        
        In order to successfully read and write, a custom
        byte-array packet is used:
        [0]  : how many bytes to read for the size of the packet
        [1-x]: length of the message in bytes (String)
        [x-y]: message in bytes
    */
    public Boolean write(String message) {
        /*
        msg                   = "Hello"
        msgSize               = 5
        msgLen                = "5"
        sizeLen               = 1
        packetSize (in bytes) = 1 + 1 + 5 = 7
        packet = [byte(sizeLen), msgLen.getBytes(), message.getBytes()]
        */
        /*
        int msgSize = message.length();
        String msgLen = Integer.toString(msgSize);
        int sizeLen = msgLen.length();
        
        // byte parts of the packet
        byte[] size = new byte[1];
        size[0] = 1;
        byte[] len = msgLen.getBytes();
        byte[] msg = message.getBytes();
        
        // concatenate byte parts
        byte[] packet = new byte[size.length + len.length + msg.length];
        System.arraycopy(size, 0, packet, 0, size.length);
        System.arraycopy(len, 0, packet, size.length, len.length);
        System.arraycopy(msg, 0, packet, size.length + len.length, msg.length);

        //System.out.println("Packet: " + packet.toString());
        */

        /*
        try {
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
            return true;
        } catch (Exception e) {
            return false;
        }
        */

        try {
            sout.write(message.getBytes());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}