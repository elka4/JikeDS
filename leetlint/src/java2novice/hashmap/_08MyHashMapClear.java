package java2novice.hashmap;

import java.util.HashMap;
/*Program: How to delete all elements from HashMap?

Description:
Below example shows how to delete all elements from the given HashMap at one call. 
By calling clear() method, we can delete all key-value pairs from HashMap.

- See more at: http://www.java2novice.com/java-collections-and-util/hashmap/delete-all/#sthash.UkXmLcpJ.dpuf*/
public class _08MyHashMapClear {
    public static void main(String a[]){
        HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println("My HashMap content:");
        System.out.println(hm);
        System.out.println("Clearing HashMap:");
        hm.clear();
        System.out.println("Content After clear:");
        System.out.println(hm);
    }
}
