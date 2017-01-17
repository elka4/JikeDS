package com.java2novice.hashtable;


import java.util.Hashtable;
/*Program: How to search a key in Hashtable?

Description:
Below example shows how to search a key from the Hashtable. 
The method containsKey() helps us to find whether the specified key 
exists or not.

- See more at: http://www.java2novice.com/java-collections-and-util/hashtable/key-search/#sthash.OX59LHA7.dpuf*/
public class _04MyHashtableKeySearch {
 
    public static void main(String a[]){
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        if(hm.containsKey("first")){
            System.out.println("The Hashtable contains key first");
        } else {
            System.out.println("The Hashtable does not contains key first");
        }
        if(hm.containsKey("fifth")){
            System.out.println("The Hashtable contains key fifth");
        } else {
            System.out.println("The Hashtable does not contains key fifth");
        }
    }
}
