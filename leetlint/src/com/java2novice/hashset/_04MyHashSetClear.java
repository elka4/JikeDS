package com.java2novice.hashset;

import java.util.HashSet;
/*Program: How to delete all elements from HashSet?

Description:
Below example shows how to delete all entries at one call from HashSet. 
By calling clear() method, we can remove all elements from HashSet at once.

- See more at: http://www.java2novice.com/java-collections-and-util/hashset/delete-all/#sthash.FQkIysTZ.dpuf*/
public class _04MyHashSetClear {
 
    public static void main(String a[]){
        HashSet<String> hs = new HashSet<String>();
        //add elements to HashSet
        hs.add("first");
        hs.add("second");
        hs.add("third");
        System.out.println("My HashSet content:");
        System.out.println(hs);
        System.out.println("Clearing HashSet:");
        hs.clear();
        System.out.println("Content After clear:");
        System.out.println(hs);
    }
}
