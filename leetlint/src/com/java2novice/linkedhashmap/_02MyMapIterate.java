package com.java2novice.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Set;
 /*Program: How to iterate through LinkedHashMap?

Description:
This example show how to iterate through LinkedHashMap.
 You always get elements by its insertion order.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedhashmap/read/#sthash.MkhG3Adz.dpuf*/
public class _02MyMapIterate {
 
    public static void main(String a[]){
         
        LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
        lhm.put("one", "This is first element");
        lhm.put("two", "This is second element");
        lhm.put("four", "Element inserted at 3rd position");
        Set<String> keys = lhm.keySet();
        for(String k:keys){
            System.out.println(k+" -- "+lhm.get(k));
        }
    }
}
