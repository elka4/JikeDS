package java2novice.hashmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
 /*Program: How to get all keys from HashMap?

Description:
Below example shows how to get all keys from the given HashMap. 
By calling keySet() method, you can get set object with all key values.

- See more at: http://www.java2novice.com/java-collections-and-util/hashmap/all-keys/#sthash.eCTNbHxT.dpuf*/
public class _06MyHashMapKeys {
     
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        Set<String> keys = hm.keySet();
        Collection<String> values = hm.values();
        for(String key: keys){
            System.out.println(key);
        }
        System.out.println(values);
    }
}
