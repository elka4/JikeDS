 package java2novice.treemap;

import java.util.TreeMap;
/*Program: Basic TreeMap Operations.

Description:
Below example shows basic operations on TreeMap like creating an object, 
adding key-value pair objects, gtting value by passing key object, 
checking whether the map has elements or not, deleting specific entry, 
and size of the TreeMap.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/basic-operations/#sthash.aWbm7fSR.dpuf*/
public class _01MyBasicOperations {
     
    public static void main(String a[]){
        TreeMap<String, String> hm = new TreeMap<String, String>();
        //add key-value pair to TreeMap
        hm.put("first", "FIRST INSERTED");
        hm.put("second", "SECOND INSERTED");
        hm.put("third","THIRD INSERTED");
        System.out.println(hm);
        //getting value for the given key from TreeMap
        System.out.println("Value of second: "+hm.get("second"));
        System.out.println("Is TreeMap empty? "+hm.isEmpty());
        hm.remove("third");
        System.out.println(hm);
        System.out.println("Size of the TreeMap: "+hm.size());
    }
}
