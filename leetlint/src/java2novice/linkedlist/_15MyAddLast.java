package java2novice.linkedlist;

import java.util.LinkedList;
/*Program: How to add element at last position in LinkedList?

Description:
Below example shows how to add element at last position in LinkedList.
LinkedList provides few methods to add element at last position, those methods are:

addLast(): Appends the specified element to the end of this list.

offerLast(): Inserts the specified element at the end of this list.

offer(): Adds the specified element as the tail (last element) of this list.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/add-last/#sthash.fdKhBJL2.dpuf*/
public class _15MyAddLast {
 
    public static void main(String a[]){
         
        LinkedList<String> arrl = new LinkedList<String>();
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        System.out.println(arrl);

        System.out.println("Adding element at last position...");
        arrl.addLast("I am last"); //Deque
        System.out.println(arrl);

        System.out.println("Adding element at last position...");
        arrl.offerLast("I am last - 1");
        System.out.println(arrl);     //Deque

        System.out.println("Adding element at last position...");
        arrl.offer("I am last - 2");
        System.out.println(arrl);    //Deque
    }
}
