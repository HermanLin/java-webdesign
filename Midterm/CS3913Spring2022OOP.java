/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022oop;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2022OOP {

    /**
     * @param args the command line arguments
     */
    static int x;
    int y; //Instance variable
    public static void main(String[] args) {
        x=100;
        CS3913Spring2022OOP ob = new CS3913Spring2022OOP();
        ob.y =100;
        
        
        
        A.storage = 100;
        A a1 = new A("Hello");
        A a2 = new A("World");
        a1.storage =101;
        a2.storage = 51;
        System.out.println(a1.someAccess());
        B b1 = new B();
        B b2 = new B();
        b1.x = 100;
        b1.y = 50;
        b2.x = 20;
        b2.y = 10;
        System.out.println("b1.x="+b1.x+"\tb1.y="+b1.y);
        System.out.println("b2.x="+b2.x+"\tb2.y="+b2.y);
        
        
        b1.x = 100;
        B.y = 50;
        b2.x = 20;
        B.y = 10;
        System.out.println("b1.x="+b1.x+"\tb1.y="+B.y);
        System.out.println("b2.x="+b2.x+"\tb2.y="+B.y);
        
        double ratio;
        try{
            ratio=calcDMRatio(100,0);
        }
        catch(NoMilkException e){
            System.out.println("You can't have no milk!!!!");
        }
        finally{
            System.out.println("We're FINALLY done!!!!");
        }
        
        /*
        Object o1;
        Object o2;
        o1= new Object();
        o2= new Object();
        swap(o1,o2);
        */
    }
    
    public static double calcDMRatio(int donuts, float milk)throws NoMilkException{
        if (milk<=0)
            throw new NoMilkException();
        return donuts/milk;
    }
    public static double calcDMRatio2(int donuts, float milk){
        assert milk>0: "Sorry, you must have milk!";
        return donuts/milk;
    }
    public static void swap(Object a, Object b){ //Useless function!
        Object temp = a;
        a = b;
        b = temp;
    }
    
}
class NoMilkException extends Throwable{}

abstract class E{
    static int x;
    abstract void func();
}

class Base{
    private int x;
    Base(){
        this(0);
    }
    Base(int newx){
        x = newx;
    }
    int getX(){
        return x;
    }
}


class Derived extends Base{
    private double d;
    Derived(){
        super(10);
        d = 5.5;
    }
    @Override
    int getX(){
        return (int)Math.round(super.getX()*d);
    }
}


class B{
    int x;
    static int y;
}

class A{
    //String message="Hello world!"; //Instance Initializer
    String message; //Instance Variable
    static int storage;
    A(){
        //String msg = "hello world"; //Not allowed!
        this("Hello world"); //Invoking the other constructor
    }
    A(String newmsg){
        message = newmsg;
    }
    private String noAccess(){
        return "You can't see this from outside the class!";
    }
    public String allAccess(){
        return "Everyone can see this!";
    }
    String someAccess(){
        return "You can only see this from inside the package!";
    }
    @Override
    public String toString(){
        return message;
    }
}
