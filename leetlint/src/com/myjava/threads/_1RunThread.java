package com.myjava.threads;

class _1MySmpThread extends Thread{
    public static int myCount = 0;
    public void run(){
        while(_1MySmpThread.myCount <= 10){
            try{
                System.out.println("Expl Thread: "+(++_1MySmpThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            }
        }
    }
}
public class _1RunThread {
    public static void main(String a[]){
        System.out.println("Starting Main Thread...");
        _1MySmpThread mst = new _1MySmpThread();
        mst.start();
        while(_1MySmpThread.myCount <= 10){
            try{
                System.out.println("Main Thread: "+(++_1MySmpThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex){
                System.out.println("Exception in main thread: "+iex.getMessage());
            }
        }
        System.out.println("End of Main Thread...");
    }
}

/*Java Thread By Implementing Runnable Interface
A Thread can be created by extending Thread class also. 
But Java allows only one class to extend, 
it wont allow multiple inheritance. 
So it is always better to create a thread by implementing Runnable interface. 
Java allows you to impliment multiple interfaces at a time.
By implementing Runnable interface, you need to provide implementation for 
run() method.
To run this implementation class, create a Thread object, pass Runnable 
implementation class object to its constructor. Call start() method on 
thread class to start executing run() method.
Implementing Runnable interface does not create a Thread object, 
it only defines an entry point for threads in your object. 
It allows you to pass the object to the Thread(Runnable implementation)
 constructor.
- See more at: http://www.java2novice.com/java_thread_examples/implementing_runnable/#sthash.o12kxQOk.dpuf*/