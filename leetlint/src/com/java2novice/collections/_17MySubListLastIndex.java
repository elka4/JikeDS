package com.java2novice.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class _17MySubListLastIndex {
 
public static void main(String a[]){
         
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("c");
        list.add("c++");
        list.add("unix");
        list.add("perl");
        list.add("php");
        list.add("javascript");
        list.add("ruby");
        list.add(".net");
        list.add("jdbc");
        list.add("php");
        list.add("javascript");
        list.add("ruby");
        list.add("servlets");
        List<String> subList = new ArrayList<String>();
        subList.add("php");
        subList.add("javascript");
        subList.add("ruby");
        System.out.println("Last index of sublist: "
                    +Collections.lastIndexOfSubList(list, subList));
    }
}
/*Program: How to get last index of a sub list from another 
 * list?
 

Description:
Collections.lastIndexOfSubList() method returns the starting
 position of the last occurrence of 
the specified target list within the specified source list, 
or -1 if there is no such occurrence.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/sub-list-last-index/#sthash.0ZE1TJyp.dpuf*/