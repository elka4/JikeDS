package java2novice.treemap;

import java.util.Set;
import java.util.TreeMap;
 /*Program: How to get all keys from TreeMap?

Description:
Below example shows how to get all keys from the given TreeMap. 
By using keySet() method, you can get all key entries as a Set object.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/all-keys/#sthash.NKkaCg9l.dpuf*/
public class _06MyTreeMapKeys {
 
    public static void main(String a[]){
        TreeMap<String, String> hm = new TreeMap<String, String>();
        //add key-value pair to TreeMap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        Set<String> keys = hm.keySet();
        for(String key: keys){
            System.out.println(key);
        }
    }
}
