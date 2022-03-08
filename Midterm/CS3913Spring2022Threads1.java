/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022threads1;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2022Threads1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Buffer b1 = new Buffer();
        Thread [] arr = new Thread[10];
        for (int i=0; i<5; i++){
            arr[i] = new Producer(b1);
            arr[i].start();
        }
        for (int i=0; i<5; i++){
            arr[i+5] = new Consumer(b1);
            arr[i+5].start();
        }
        /*
        Thread [] arr = new Thread[5];
        for(int i=0; i<arr.length; i++){
            arr[i] = new ScreenPrinter(i+1);
            arr[i].start();
        }
        */
        
        //MyThread mt = new MyThread();
        //mt.start();
        /*Thread [] arr = new Thread[5];
        arr[0] = new MyThread('#');
        arr[1] = new MyThread('!');
        arr[2] = new MyThread('@');
        arr[3] = new MyThread('$');
        OtherThread ot = new OtherThread(';');
        arr[4] = new Thread(ot);
        for(Thread t: arr){
            t.setPriority(Thread.MIN_PRIORITY);
            t.start();
        }
        arr[3].setPriority(Thread.MAX_PRIORITY);
        for (int i=0; i<1000; i++){
            System.out.print(".");
            //Thread.currentThread().yield();
        }
        */
    }
    
}


class MyThread extends Thread{
    char output;
    MyThread(char newOutput){output = newOutput;}
    @Override
    public void run(){
        for (int i=0; i<1000; i++){
            System.out.print(output);
            //yield();
        }
    }
}

class OtherThread implements Runnable{
    char output;
    OtherThread(char newOutput){output = newOutput;}
    @Override
    public void run() {
        for (int i=0; i<1000; i++){
            System.out.print(output);
            //Thread.currentThread().yield();
        }
    }
    
}

class ScreenPrinter extends Thread{
    int id;
    ScreenPrinter(int newid){id = newid;}
    public void run(){
        for(int i=0; i<100; i++){
            linePrint(id);
        }
    }
    static synchronized void linePrint(int lineid){
        for (int j=0; j<lineid; j++)
                System.out.print("\t");
            System.out.println(lineid);
    }
}

class Buffer{
    boolean[] arr;
    int size;
    int capacity;
    Buffer(){
        capacity=500;
        size=0;
        arr = new boolean[capacity];
        for (int i=0; i<capacity; i++)
            arr[i] = false;
    } 
    synchronized boolean addToBuffer(){
        
        if (size<capacity){
            System.out.println("Size="+size+"\tCapacity="+capacity); //DEBUG
            arr[size]=true;
            size++;
            return true;
        }
        else
            return false;
    }
    synchronized boolean removeFromBuffer(){
        if (size>0){
            System.out.println("Size="+size+"\tCapacity="+capacity); //DEBUG
            size--;
            arr[size]=false;
            return true;
        }
        else
            return false;
    }
    boolean isFull(){return size==capacity;}
    boolean isEmpty(){return size==0;}
}

class Producer extends Thread{
    Buffer buff;
    Producer(Buffer newbuff){buff = newbuff;}
    public void run(){
        for (int i=0; i<100000; i++){
            while (buff.isFull()){
                try{
                    Thread.currentThread().sleep(50);
                }catch(InterruptedException e){return;}
            }
            buff.addToBuffer();
        }
    }
}

class Consumer extends Thread{
    Buffer buff;
    Consumer(Buffer newbuff){buff = newbuff;}
    public void run(){
        for (int i=0; i<100000; i++){
            while (buff.isEmpty()){
                try{
                    Thread.currentThread().sleep(50);
                }catch(InterruptedException e){return;}
            }
            buff.removeFromBuffer();
        }
    }
}