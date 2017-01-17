package com.java2novice.hashmap;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
 /*Program: How to get entry set from HashMap?

Description:
Below example shows how to get all key-value pair as Entry objects. 
Entry class provides getter methods to access key-value details. 
The method entrySet() provides all entries as set object.

- See more at: http://www.java2novice.com/java-collections-and-util/hashmap/entry-set/#sthash.Fu0IivJa.dpuf*/
public class _07MyHashMapEntrySet {
 
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        //getting value for the given key from hashmap
        Set<Entry<String, String>> entires = hm.entrySet();
        for(Entry<String,String> ent:entires){
            System.out.println(ent.getKey()+" ==> "+ent.getValue());
        }
    }
}
