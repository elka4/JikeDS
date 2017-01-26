package java2novice.hashset;

import java.util.HashSet;
/*Program: How to compare two sets and retain elements 
 * which are same on both sets?

Description:
Below example shows how to compare two sets, 
and retain the values which are common on both set objects. 
By calling retainAll() method you can do this operation.

- See more at: http://www.java2novice.com/java-collections-and-util/hashset/retain-all/#sthash.c0sRPtNF.dpuf*/
public class _06MyHashSetRetain {
 
    public static void main(String a[]){
        HashSet<String> hs = new HashSet<String>();
        //add elements to HashSet
        hs.add("first");
        hs.add("second");
        hs.add("third");
        hs.add("apple");
        hs.add("rat");
        System.out.println(hs);
        HashSet<String> subSet = new HashSet<String>();
        subSet.add("rat");
        subSet.add("second");
        subSet.add("first");
        subSet.add("hehe hehe");
        hs.retainAll(subSet);
        System.out.println("HashSet content:");
        System.out.println(hs);
    }
}
