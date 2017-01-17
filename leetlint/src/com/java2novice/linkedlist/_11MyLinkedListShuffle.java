package com.java2novice.linkedlist;

import java.util.Collections;
import java.util.LinkedList;
 /*Program: How to shuffle elements in LinkedList?

Description:
This example gives how to shuffle elements in the LinkedList. 
By calling Collections.shuffle() method you can shuffle the content of 
the LinkedList. Everytime you call shuffle() method, 
it generates different order of output.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/shuffle/#sthash.j1X1PL9k.dpuf*/
public class _11MyLinkedListShuffle {
 
    public static void main(String a[]){
         
        LinkedList<String> list = new LinkedList<String>();
        list.add("Java");
        list.add("Cric");
        list.add("Play");
        list.add("Watch");
        list.add("Glass");
        list.add("Movie");
        list.add("Girl");
         
        Collections.shuffle(list);
        System.out.println("Results after shuffle operation:");
        for(String str: list){
            System.out.println(str);
        }
         
        Collections.shuffle(list);
        System.out.println("Results after shuffle operation:");
        for(String str: list){
            System.out.println(str);
        }
    }
}
