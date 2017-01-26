package java2novice.linkedlist;

import java.util.LinkedList;

public class _05ClearMyLinkedList {
 
    public static void main(String a[]){
         
        LinkedList<String> arrl = new LinkedList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        System.out.println("Actual LinkedList:"+arrl);
        arrl.clear();
        System.out.println("After clear LinkedList:"+arrl);
    }
}
