package com.java2novice.treemap;

import java.util.Set;
import java.util.TreeMap;
 /*Program: How to iterate through TreeMap?

Description:
Below example shows how to iterate through all entries from the TreeMap. 
First you can get all keys by calling keySet() method, 
which returns list of keys as Set object. 
By going through each element in the set, 
you can get corresponding values from TreeMap.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/iterate/#sthash.I2eZmX5a.dpuf*/
public class _02MyTreeMapRead {
 
    public static void main(String a[]){
        TreeMap<String, String> hm = new TreeMap<String, String>();
        //add key-value pair to TreeMap
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
