package com.java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class _18MyMaxNumber {
 
    public static void main(String a[]){
         
        List<Integer> li = new ArrayList<Integer>();
        li.add(23);
        li.add(44);
        li.add(12);
        li.add(45);
        li.add(2);
        li.add(16);
        System.out.println("Maximum element from the list: "+Collections.max(li));
    }
}
/*Program: How to get max element from the given list?

Description:
Collections.max() method returns the maximum element of 
the given collection, 
according to the natural ordering of its elements.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/max-number/#sthash.w9i6WJPG.dpuf*/