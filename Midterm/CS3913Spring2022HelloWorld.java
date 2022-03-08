package cs3913spring2022helloworld;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2022HelloWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //java.util.Scanner cin = new java.util.Scanner(System.in);
        Scanner sin = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int c = sin.nextInt();
        System.out.println("You said: "+c);
        
        int [] arr = new int[10];
        for (int i =0; i<arr.length/2; i++)
            arr[i] = i*10;
        
        for(int val:arr)
            System.out.println(val);
        
        Object [] oArray = new Object[10];
        
        for (int i=0; i<oArray.length; i++)
            oArray[i] = new Object();
        
        /*for (int i =0; i<10; i++)
            System.out.println("i="+i);
        
        int x = -10;
        if (x>0) 
            if (x<0) 
                System.out.println("B"); 
            else 
                System.out.println("C");
    
    
            
        

        if (x>0){
            System.out.println("A");
        }
        else if (x<0)
            System.out.println("B");
        else if (x==100)
            System.out.println("C"); //impossible to get here
        else
            System.out.println("D");
        */
        /*final double TAX_RATE;
        double y = 1;
//        int x = (int)2.5;
        
        int val = 100;
        ++val;
        Integer x = val;
        String a = "Hello";
        String b = "World";
        String c = a+b;
        System.out.println("Hello"+"World");
        System.out.println("Val:"+val);
        System.out.println("5+3="+(5+3));
        */
        
        /*final double NYC_TAX=0.04875;
        final double NYS_TAX = 0.04;
        TAX_RATE = NYC_TAX+NYS_TAX;
        System.out.println(TAX_RATE);
        //TAX_RATE  = 0;
        String name = new String("Daniel Katz");
        name = "Daniel Katz";
        ArrayList al; //Not an ArrayLIst object!
        al = new ArrayList();
        al.add("hello");
        */
    }
    
}
