package com.java2novice.hashset;

import java.util.HashSet;
/*Program: How to copy Set content to another HashSet?

Description:
Below example shows how to copy another collection object to HashSet object. 
By calling addAll() method you can copy another collection to HashSet object.

- See more at: http://www.java2novice.com/java-collections-and-util/hashset/copy-to-another/#sthash.VOvsLn6u.dpuf*/
public class _03MyHashSetCopy {
 
    public static void main(String a[]){
        HashSet<String> hs = new HashSet<String>();
        //add elements to HashSet
        hs.add("first");
        hs.add("second");
        hs.add("third");
        System.out.println(hs);
        HashSet<String> subSet = new HashSet<String>();
        subSet.add("s1");
        subSet.add("s2");
        hs.addAll(subSet);
        System.out.println("HashSet content after adding another collection:");
        System.out.println(hs);
    }
}
