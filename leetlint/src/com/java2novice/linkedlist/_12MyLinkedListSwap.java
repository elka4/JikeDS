package com.java2novice.linkedlist;

import java.util.Collections;
import java.util.LinkedList;
 /*Program: How to swap two elements in a LinkedList?

Description:
This example gives how to swap two elements in the LinkedList. 
By calling Collections.swap() method you can swap two elements 
of the LinkedList. You have to pass the indexes which you need to swap.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/swap/#sthash.i5O2mGSi.dpuf*/
public class _12MyLinkedListSwap {
 
    public static void main(String a[]){
         
        LinkedList<String> list = new LinkedList<String>();
        list.add("Java");
        list.add("Cric");
        list.add("Play");
        list.add("Watch");
        list.add("Glass");
        list.add("Movie");
        list.add("Girl");
         
        Collections.swap(list, 2, 5);
        System.out.println("Results after swap operation:");
        for(String str: list){
            System.out.println(str);
        }
    }
}
