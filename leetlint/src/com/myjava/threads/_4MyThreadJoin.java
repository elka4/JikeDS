package com.myjava.threads;

import java.util.ArrayList;
import java.util.List;
 
public class _4MyThreadJoin {
 
    public static List<String> names = new ArrayList<String>();
     
    public static void main(String a[]){
        List<SampleThread> list = new ArrayList<SampleThread>();
        for(int i=0;i<5;i++){
            SampleThread s = new SampleThread();
            list.add(s);
            s.start();
        }
        for(SampleThread st:list){
            try{
                st.join();
            } catch (Exception ex){}
        }
        System.out.println(names);
    }
}
 
class SampleThread extends Thread{
     
    public void run(){
        for(int i=0; i<10; i++){
            try{
                Thread.sleep(10);
            } catch(Exception ex){}
        }
        _4MyThreadJoin.names.add(getName());
    }
}

/*Java Thread Join Examples
Imagine the following scenario. You are preparing for tomorrow's final 
examination and feel
 a little hungry. So, you give your younger brother ten bucks and ask him 
 to buy a pizza for you. In this case, you are the main thread and your 
 brother is a child thread. Once your order is given, both you and your 
 brother are doing their job concurrently (i.e., studying and buying a pizza).
  Now, we have two cases to consider. First, your brother brings your pizza 
  back and terminates while you are studying. In this case, you can stop 
  studying and enjoy the pizza. Second, you finish your study early and 
  sleep (i.e., your assigned job for today - study for tomorrow's final 
  exam - is done) before the pizza is available. Of course, you cannot sleep;
   otherwise, you won't have a chance to eat the pizza. What you are going 
   to do is to wait until your brother brings the pizza back.
A thread can execute a thread join to wait until the other thread terminates
A parent thread may join with many child threads created by the parent. 
Or, a parent only join with some of its child threads, and ignore other 
child threads. In this case, those child threads that are ignored by the 
parent will be terminated when the parent terminates.
- See more at: http://www.java2novice.com/java_thread_examples/thread_join/#sthash.CufVixl2.dpuf*/