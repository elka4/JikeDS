package com.java2novice.treeset;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
 /*Program: How to create a TreeSet with a List?

Description:
Below example shows how to create TreeSet with other collection. 
By passing another collection to the TreeSet constructor, 
you can copy entire collections elements to the TreeSet.


- See more at: http://www.java2novice.com/java-collections-and-util/treeset/treeset-with-list/#sthash.hHJlxEYy.dpuf*/
public class _02MySetWithCollection {
    public static void main(String a[]){
        List<String> li = new ArrayList<String>();
        li.add("one");
        li.add("two");
        li.add("three");
        li.add("four");
        System.out.println("List: "+li);
        //create a treeset with the list
        TreeSet<String> myset = new TreeSet<String>(li);
        System.out.println("Set: "+myset);
    }
}
