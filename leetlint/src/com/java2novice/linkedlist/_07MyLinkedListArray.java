package com.java2novice.linkedlist;

import java.util.LinkedList;
/*Here we can see example for copying all content of LinkedList to an array. 
 * You can get this done by calling toArray() method. 
 * - See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/to-array/#sthash.LZ8RdNng.dpuf*/
public class _07MyLinkedListArray {
 
    public static void main(String a[]){
         
        LinkedList<String> arrl = new LinkedList<String>();
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        System.out.println("Actual LinkedList:"+arrl);
        String[] strArr = new String[arrl.size()];
        arrl.toArray(strArr);
        System.out.println("Created Array content:");
        for(String str:strArr){
            System.out.println(str);
        }
    }
}
