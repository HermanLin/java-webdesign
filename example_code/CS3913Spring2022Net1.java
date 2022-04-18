/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022net1;

import java.io.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2022Net1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("www.google.com",80);
            PrintStream sout = new PrintStream(sock.getOutputStream());
            Scanner sin = new Scanner(sock.getInputStream());
            
            sout.print("GET / HTTP/1.0\r\n\r\n"); //Client sends FIRST!!!!
            while (sin.hasNext())
                System.out.println(sin.nextLine());
        } catch (IOException ex) {
            System.out.println("Socket could not connect!");
        }
        
    }
    
}
