/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022files;

import java.util.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2022Files {

    /**
     * @param args the command line arguments
     */
    public static void printFileNames(File f){
        ArrayList<File> dirs = new ArrayList();
        File[] entries = f.listFiles();
        for (File item: entries){
            if (item.isDirectory())
                dirs.add(item);
            else
                System.out.println(item.getAbsolutePath());
        }
        for (File dir: dirs)
            printFileNames(dir);
    }
    public static void main(String[] args) {
        //try{
            File f = new File("../");
            printFileNames(f);
            /*URL url = new URL("https://finance.yahoo.com/quote/AMZN");
            URLConnection conn = url.openConnection();
            Scanner in = new Scanner(conn.getInputStream());
            while (in.hasNext())
                System.out.println(in.nextLine());
            System.exit(0);
            
            ArrayList<Integer> al = new ArrayList();
            Scanner fin = new Scanner(new File("file.txt"));
            while (fin.hasNextInt()){
                al.add(fin.nextInt());
            }
            PrintStream fout = new PrintStream("output.txt");
            PrintStream log = new PrintStream(new FileOutputStream("Runtime.log",true));
            log.println("Run at "+Calendar.getInstance().getTime().toString());
            log.close();
            for (int i: al){
                //System.out.println(i);
                fout.println(i);
            }*/
            
            /*
            FileInputStream fis = new FileInputStream(new File("input.jpg"));
            ArrayList<Byte> alb = new ArrayList();
            int val=fis.read();
            while (val!=-1){
                alb.add((byte)val);
                val=fis.read();
            }
            for (int i:alb)
                System.out.println(i);
            */
            
        //}
        /*catch(FileNotFoundException e){
            System.out.println("File.txt could not be found!");
        }
        catch(IOException e){
            System.out.println("IOException!");
        }*/
    }
    
}
