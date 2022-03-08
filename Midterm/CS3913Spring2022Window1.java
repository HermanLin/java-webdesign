/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022window1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2022Window1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Hello World!");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        jf.setResizable(false);
        JButton jb = new JButton("PRESS ME!");
        JPanel jp = new JPanel();
        jp.setBackground(Color.RED);
        jp.setLayout(new BorderLayout());
        
        jf.add(jp);
        JLabel jl;
    
        jl = new JLabel("<HTML><H1>Some Text</H2>");
        jp.add(jl,BorderLayout.NORTH);
        
        jp.add(jb, BorderLayout.SOUTH);
        jb.addActionListener(new ButtonListener(jl));
        jf.setVisible(true);
        
        
        /*Derived d = new Derived();
        method(d);
        System.out.println(d.x);
        
        Refuelable [] rArr = new Refuelable[5];
        rArr[0] = new GasCan();
        rArr[1] = new Vehicle(20);
        rArr[2] = new GasCan();
        rArr[3] = new Vehicle(20);
        rArr[4] = new GasCan();
        for (Object o : rArr){
            System.out.println(o.getClass().toString());
        }
        */
               
    }
    
    static void method(Test1 t){
        t.methodB(55);
        //t.func1(); //Cannot be done, despite this being a Derived object
    }
}

class ButtonListener implements ActionListener{
    
    JLabel jl;
    ButtonListener(JLabel newJl){jl = newJl;}
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton jb = (JButton) arg0.getSource();
        if (jl.getText()=="<HTML><Font size=40>Press registered!"){
            jl.setText("<HTML><Font size=40>I HEARD YOU!!!!");
            jb.setText("I SAID ONLY ONCE!!!!");
            jb.setEnabled(false);
        }
        else{
            jl.setText("<HTML><Font size=40>Press registered!");
            jb.setText("Press only once!");
        }
    }
    
}
interface Driveable{
    void drive();
}
interface Refuelable{
    public double putGasIn(double gallons); // Returns number of gallons put in
}
class GasCan implements Refuelable{
    double gasTank;
    double tankCapacity;
    public double putGasIn(double gallons) {
        double added = 0;
        if ((gasTank+gallons)>tankCapacity){
            added = tankCapacity-gasTank;
            gasTank = tankCapacity;
        }
        else{
            tankCapacity+=gallons;
            added = gallons;
        }
        return added;
    }
}
class Vehicle implements Refuelable,Driveable{
    double gasTank;
    double tankCapacity;
    Vehicle(double newCapacity){
        tankCapacity=newCapacity;
        gasTank = tankCapacity;
    }

    @Override
    public double putGasIn(double gallons) {
        double added = 0;
        if ((gasTank+gallons)>tankCapacity){
            added = tankCapacity-gasTank;
            gasTank = tankCapacity;
        }
        else{
            tankCapacity+=gallons;
            added = gallons;
        }
        return added;
    }

    @Override
    public void drive() {
        System.out.println("As the Beatles said.... Baby you can drive my car!");
    }
}

class GasStation{
    void fillErUp(Refuelable r){
        r.putGasIn(100);
    }
}
    
 
  

interface Test1{
    public void methodA();
    public void methodB(int x);
    int methodC();
}
class Derived extends Base implements Test1{
    Derived(int newx){super(newx);}
    Derived(){this(200);}

    @Override
    public void methodA() {
        System.out.println("Hello world!");
    }

    @Override
    public void methodB(int newx) {
        x = newx;
    }

    @Override
    public int methodC() {
        return x;
    }
} 

class Base{
    int x;
    Base(int newx){x = newx;}
    Base(){this(100);}
    public int func1(){return x;}
}


