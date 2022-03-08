/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022nestedclasses;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2022NestedClasses {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        A a = new A();
        cs3913spring2022nestedclasses.A.InsideA ina = a.getInsideA();
        A.SClass asc = new A.SClass(a);
        
        Base b = func2();
        System.out.println(b.getVar()); //Prints 5000 because b is a derived object
        System.out.println(func2().getVar());
        System.out.println(new Base(42).getVar());
        
        System.out.println(new Base(20){ 
            int anonVar = 50;
            int getVar(){return super.getVar()*100;}}.getVar());
    }
    
    void func(){
        class LocalClass{
            int x;
            LocalClass(){this(100);}
            LocalClass(int newx){x=newx;}
            int getx(){return x;}
        }
        LocalClass lc = new LocalClass();
        
    }
    
    static Base func2(){
        //LocalClass lc; //LocalClass doesn't exist here!
        class Derived extends Base{
            Derived(){super(500);}
            int getVar(){ return super.getVar()*10;}
        }
        Derived d = new Derived();
        return d;
    }
    
}
class Base{
    int var;
    Base(){var = 100;}
    Base(int newVar){var = newVar;}
    int getVar(){return var;}
}

class NotNested{
    A ref;
    NotNested(A newa){ref = newa;}
    int getx(){/*return ref.x;*/ return 10;}
}

class A{
    private int x;
    private static double d;
    A(){x=100; d = 5.5;}
    
    static class SClass{
        A ref;
        double d;
        static double sd;
        SClass(A newa){ref = newa;}
        int getx(){return ref.x;}
        double getd(){return d;}
        void makeAnA(){A temp = new A();}
    }
    class InsideA{ // Must have an instance of A to create this
        int x; //Shadowing
        int getx(){ return x;}
        double getd(){return d;}
        
    }
    class YAInsideA{
        
        void addx(int val){x+=val;}
    }
    
    InsideA getInsideA(){
        return new InsideA();
    }
    
}
class AnotherA{
    class InsideA{
    }
}