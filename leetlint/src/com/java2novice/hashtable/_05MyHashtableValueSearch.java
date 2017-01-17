package com.java2novice.hashtable;

import java.util.Hashtable;
/*Program: How to search a value in Hashtable?

Description:
Below example shows how to search a value from the Hashtable. 
The method containsValue() helps us to find whether the specified 
value exists or not.

- See more at: http://www.java2novice.com/java-collections-and-util/hashtable/value-search/#sthash.hUEI62lL.dpuf*/
public class _05MyHashtableValueSearch {
 
    public static void main(String a[]){
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        if(hm.containsValue("SECOND INSERTED")){
            System.out.println("The Hashtable contains value SECOND INSERTED");
        } else {
            System.out.println("The Hashtable does not contains value SECOND INSERTED");
        }
        if(hm.containsValue("first")){
            System.out.println("The Hashtable contains value first");
        } else {
            System.out.println("The Hashtable does not contains value first");
        }
    }
}
