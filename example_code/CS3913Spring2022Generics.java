/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022generics;

import java.util.*;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2022Generics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> a1 = new ArrayList();
        for (int i=0; i<10; i++){
            a1.add(i);
        }
        for (Integer i: a1){
            System.out.println(i);
        }
        /*Iterator<Integer> i = a1.iterator();
        while (i.hasNext()){
            Integer val = i.next();
            System.out.println(val);
        }*/
        /*ArrayList a1 = new ArrayList();
        for(int i=0; i<10; i++){
            Integer temp = i;
            a1.add(temp);
        }
        
        Iterator i = a1.iterator();
        while(i.hasNext()){
            Object o = i.next();
            
            Integer val = (Integer) o;
            System.out.println(val);
        }*/
    }
    
}
