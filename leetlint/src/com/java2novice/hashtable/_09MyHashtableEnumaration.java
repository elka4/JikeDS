package com.java2novice.hashtable;

import java.util.Enumeration;
import java.util.Hashtable;

/*Program: How to read elements using Enumeration in Hashtable?

Description:
Below example shows how to get all keys as Enumeration object. 
By calling keys() method, we can get all keys as Enumeration object. 
By using Enumeration methods like hasMoreElements() and nextElement() 
we can read all values from Hashtable.

- See more at: http://www.java2novice.com/java-collections-and-util/hashtable/enumeration/#sthash.2H2vRDd3.dpuf*/
public class _09MyHashtableEnumaration {
 
public static void main(String a[]){
         
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        Enumeration<String> keys = hm.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            System.out.println("Value of "+key+" is: "+hm.get(key));
        }
    }
}
