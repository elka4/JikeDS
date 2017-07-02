package DataStructure;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Linear_Queue {


    @Test
    public void test01(){
        Queue q = new LinkedList(){};
        q.offer("1");
        q.offer("2");
        q.offer("3");
        System.out.println(q);
        System.out.println(q.poll());

    }
}
