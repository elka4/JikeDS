package com.java2novice.hashset;

import java.util.HashSet;
/*Program: How to copy all elements from HashSet to an array?

Description:
Below example shows how to copy all elements from HashSet to an array. 
By calling toArray() method and passing existing array object to this 
method, we can copy all elements of HashSet to an array.

- See more at: http://www.java2novice.com/java-collections-and-util/hashset/copy-to-array/#sthash.IPatCaSN.dpuf*/
public class _05MyHashSetToArray {
 
    public static void main(String a[]){
        HashSet<String> hs = new HashSet<String>();
        //add elements to HashSet
        hs.add("first");
        hs.add("second");
        hs.add("third");
        System.out.println("HashSet content: ");
        System.out.println(hs);
        String[] strArr = new String[hs.size()];
        hs.toArray(strArr);
        System.out.println("Copied array content:");
        for(String str:strArr){
            System.out.println(str);
        }
    }
}
