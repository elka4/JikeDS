package com.myjava.threads;

public class _3DaemonThread extends Thread{
    
    public _3DaemonThread(){
        setDaemon(true);
    }
    public void run(){
        System.out.println("Is this thread Daemon? - "+isDaemon());
    }
    public static void main(String a[]){
    	_3DaemonThread dt = new _3DaemonThread();
        // even you can set daemon constrain here also
        // it is like dt.setDeamon(true)
        dt.start();
    }
}

/*Java Daemon Thread Examples
You can make any java thread as daemon thread. 
Daemon threads acts like service providers 
for other threads running in the same process.
Daemon threads will be terminated by the JVM when there are none of the 
other threads running, it includs main thread of execution as well.
To specify that a thread is a daemon thread, call the setDaemon method 
with the argument true.
To determine if a thread is a daemon thread, use the accessor method isDaemon.
- See more at: http://www.java2novice.com/java_thread_examples/daemon_threads/#sthash.IeKOR7ZG.dpuf*/