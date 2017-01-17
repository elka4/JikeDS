package com.java2novice.hashset;

import java.util.HashSet;
import java.util.Iterator;
 /*
  * Program: How to iterate through HashSet?

Description:
Below example shows how to read all elements from the HashSet objects. 
You can iterate through HashSet by getting Iterator object. 
By calling iterator() method, you can get Iterator object.

- See more at: http://www.java2novice.com/java-collections-and-util/hashset/iterate/#sthash.PFuDyG5Q.dpuf
  */
public class _02MyHashSetRead {
 
    public static void main(String a[]){
        HashSet<String> hs = new HashSet<String>();
        //add elements to HashSet
        hs.add("first");
        hs.add("second");
        hs.add("third");
        Iterator<String> itr = hs.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
