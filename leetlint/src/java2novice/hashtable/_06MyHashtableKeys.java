package java2novice.hashtable;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
 /*Program: How to get all keys from Hashtable?

Description:
Below example shows how to get list of keys. 
keySet() method returns Set object will all keys.

- See more at: http://www.java2novice.com/java-collections-and-util/hashtable/all-keys/#sthash.lEyOx6Rw.dpuf*/
public class _06MyHashtableKeys {
 
    public static void main(String a[]){
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        Set<String> keys = hm.keySet();
        for(String key: keys){
            System.out.println(key);
        }
        Collection<String> values = hm.values();
        System.out.println(values);
    }
}
