package java2novice.hashtable;

import java.util.HashMap;
import java.util.Hashtable;
 /*Program: How to copy Map content to another Hashtable?

Description:
Below example shows how to copy another collection object to Hashtable. 
Below example copies all HashMap entries to Hashtable. 
putAll() method helps us to copy another collection object.

- See more at: http://www.java2novice.com/java-collections-and-util/hashtable/copy-to-another/#sthash.lT4LgyNZ.dpuf*/
public class _03MyHashtableCopy {
 
    public static void main(String a[]){
        Hashtable<String, String> hm = new Hashtable<String, String>();
        //add key-value pair to Hashtable
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
