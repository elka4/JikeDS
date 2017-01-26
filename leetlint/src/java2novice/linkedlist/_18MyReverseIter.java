package java2novice.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
 /*Program: How to iterate through LinkedList in reverse order?

Description:
Below example shows how to iterate through LinkedList in reverse order. The method descendingIterator() returns an Iterator object with reverse order. By iterating through it, you can get the elements in the reverse order.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/reverse-iterate/#sthash.WcWDWtaY.dpuf*/
public class _18MyReverseIter {
 
    public static void main(String a[]){
         
        LinkedList<String> arrl = new LinkedList<String>();
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        Iterator<String> itr = arrl.descendingIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
