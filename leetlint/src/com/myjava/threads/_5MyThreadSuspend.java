package com.myjava.threads;

import java.util.ArrayList;
import java.util.List;
 
public class _5MyThreadSuspend {
    public static void main(String a[]){
        List<ExmpThread> list = new ArrayList<ExmpThread>();
        for(int i=0;i<4;i++){
            ExmpThread et = new ExmpThread(i+10);
            list.add(et);
            et.start();
        }
        for(ExmpThread et:list){
            try {
                et.join();
            } catch (InterruptedException ex) { }
        }
    }
}
 
class ExmpThread extends Thread{
    private int suspendCount;
    public ExmpThread(int count){
        this.suspendCount = count;
    }
    public void run(){
        for(int i=0;i<50;i++){
            if(i%suspendCount == 0){
                try {
                    System.out.println("Thread Sleep: " + getName());
                    Thread.sleep(500);
                } catch (InterruptedException ex) { }
            }
        }
    }
}

/*Java Thread Sleep
It makes current executing thread to sleep specified number of milli seconds. 
Below example shows sample code for thread sleep.
- See more at: http://www.java2novice.com/java_thread_examples/thread_sleep/#sthash.BZZ2oLgh.dpuf*/
