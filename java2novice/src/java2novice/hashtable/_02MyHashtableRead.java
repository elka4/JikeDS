package java2novice.hashtable;

import java.util.Hashtable;
import java.util.Set;
 /*Program: How to iterate through Hashtable?

Description:
Below example shows how to read elements from Hashtable. You can iterate through each and every element by getting all keys as set object. Using each element as a key from set, you can values from Hashtable.

- See more at: http://www.java2novice.com/java-collections-and-util/hashtable/iterate/#sthash.w6a3vuyN.dpuf*/
public class _02MyHashtableRead {
 
    public static void main(String a[]){
         
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        Set<String> keys = hm.keySet();
        System.out.println(keys);
        for(String key: keys){
            System.out.println("Value of "+key+" is: "+hm.get(key));
        }
    }
}
