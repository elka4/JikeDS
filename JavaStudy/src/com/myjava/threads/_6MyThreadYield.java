package com.myjava.threads;

import java.util.ArrayList;
import java.util.List;
 
public class _6MyThreadYield {
    public static void main(String a[]){
        List<ExmpThread_2> list = new ArrayList<ExmpThread_2>();
        for(int i=0;i<3;i++){
            ExmpThread_2 et = new ExmpThread_2(i+5);
            list.add(et);
            et.start();
        }
        for(ExmpThread_2 et:list){
            try {
                et.join();
            } catch (InterruptedException ex) { }
        }
    }
}
 
class ExmpThread_2 extends Thread{
     
    private int stopCount;
    public ExmpThread_2(int count){
        this.stopCount = count;
    }
    public void run(){
        for(int i=0;i<50;i++){
            if(i%stopCount == 0){
                System.out.println("Stoping thread: "+getName());
                yield();
            }
        }
    }
}
/*Java Thread Yield Examples
When a thread executes a thread yield, the executing thread is suspended 
and the CPU 
is given to some other runnable thread. This thread will wait until the 
CPU becomes available again.
Technically, in process scheduler's terminology, the executing thread 
is put back into the ready queue of the processor and waits for its next turn.
- See more at: http://www.java2novice.com/java_thread_examples/thread_yield/#sthash.ak8G7Jvs.dpuf*/