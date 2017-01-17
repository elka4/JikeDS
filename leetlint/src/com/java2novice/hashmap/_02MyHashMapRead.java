package com.java2novice.hashmap;

import java.util.HashMap;
import java.util.Set;
 /*Program: How to iterate through HashMap?

Description:
Below example shows how to read add elements from HashMap. 
The method keySet() returns all key entries as a set object. 
Iterating through each key, we can get corresponding value object.

- See more at: http://www.java2novice.com/java-collections-and-util/hashmap/iterate/#sthash.Y8f7uVPh.dpuf*/
public class _02MyHashMapRead {
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        Set<String> keys = hm.keySet();
        for(String key: keys){
            System.out.println("Value of "+key+" is: "+hm.get(key));
        }
    }
}
