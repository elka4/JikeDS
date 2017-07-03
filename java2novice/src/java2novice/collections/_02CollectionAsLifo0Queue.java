package java2novice.collections;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;
 
public class _02CollectionAsLifo0Queue {
 
    public static void main(String args[]) {

          Deque<String> dq = new ArrayDeque<String>(5);
          dq.add("java");
          dq.add("c");
          dq.add("c++");
          dq.add("unix");
          dq.add("perl");       
          Queue<String> q = Collections.asLifoQueue(dq);  
          System.out.println("returned queue is: "+q);
          q.peek();
          q.poll();
          q.remove();
       }
}

/*Program: Write an example for Collections.asLifoQueue() method.

Description:
The Collections.asLifoQueue() method returns a view of a Deque as 
a Last-in-first-out (Lifo) Queue. 
Method add is mapped to push, remove is mapped to pop and so
 on. This view can be useful when you would like to use a 
 method requiring a Queue but you need Lifo ordering.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/aslifoqueue/#sthash.t99TdS3W.dpuf*/