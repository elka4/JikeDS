package com.java2novice.hashmap;

import java.util.HashMap;
/*Program: How to copy Map content to another HashMap?

Description:
Below example shows how to copy another collection to HashMap. 
putAll() method helps us to copy another collections to HashMap object.

- See more at: http://www.java2novice.com/java-collections-and-util/hashmap/copy-to-another/#sthash.dbKIiUXa.dpuf*/
public class _03MyHashMapCopy {
 
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        HashMap<String, String> subMap = new HashMap<String, String>();
        subMap.put("s1", "S1 VALUE");
        subMap.put("s2", "S2 VALUE");
        hm.putAll(subMap);
        System.out.println(hm);
    }
}
