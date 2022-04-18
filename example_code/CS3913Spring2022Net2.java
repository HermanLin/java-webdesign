/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022net2;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2022Net2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6543);
            while (true){
                Socket client_sock = ss.accept();
                new ProcessConnection(client_sock).start();
            }
            
        } catch (IOException ex) {
            System.out.println("Could not listen on port 6543");
        }
        
    }
    
}

class ProcessConnection extends Thread{
    Socket client_sock;
    ProcessConnection(Socket newClientSock){
        client_sock = newClientSock;
    }
    public void run(){
        try{
            Scanner sin = new Scanner(client_sock.getInputStream());
            PrintStream sout = new PrintStream(client_sock.getOutputStream());

            System.out.println("Got Connection from "+client_sock.getInetAddress().toString());
            sout.println("Welcome to my ECHO server!");
            String line = sin.nextLine();
            while (line.strip().compareToIgnoreCase("EXIT")!=0){
                sout.println(line);
                System.out.println("Client said: "+line);
                line = sin.nextLine();
            }
            client_sock.close();
        }
        catch(IOException e){}
    }
}
