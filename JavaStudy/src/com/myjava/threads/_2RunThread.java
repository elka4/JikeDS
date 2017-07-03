package com.myjava.threads;

class _2MySmpThread extends Thread{
    public static int myCount = 0;
    public void run(){
        while(_2MySmpThread.myCount <= 10){
            try{
                System.out.println("Expl Thread: "+(++_2MySmpThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            }
        }
    }
}
public class _2RunThread {
    public static void main(String a[]){
        System.out.println("Starting Main Thread...");
        _2MySmpThread mst = new _2MySmpThread();
        mst.start();
        while(_2MySmpThread.myCount <= 10){
            try{
                System.out.println("Main Thread: "+(++_2MySmpThread.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex){
                System.out.println("Exception in main thread: "+iex.getMessage());
            }
        }
        System.out.println("End of Main Thread...");
    }
}
/*Java Thread By Extending Thread Class
A thread can be created in java by extending Thread class, where you must override run() method.
Call start() method to start executing the thread object.
- See more at: http://www.java2novice.com/java_thread_examples/extending_thread_class/#sthash.wqN0DhcY.dpuf*/