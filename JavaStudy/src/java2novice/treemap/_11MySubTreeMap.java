package java2novice.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
 /*Program: How to get sorted sub-map from TreeMap?

Description:
Below example shows how to get range of elements from sorted user 
defined objects by using comparator object. You can include you own
 custom sorting logic with compare method. By passing comparator object
  to the TreeMap, you can sort the keys based on the logic provided 
  inside the compare method. Once the keys are sorted, you can call 
  subMap() method to get range of elements based on key.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/sub-map/#sthash.VWGvKBfu.dpuf*/
public class _11MySubTreeMap {
     
    public static void main(String a[]){
        //the treemap sorts by key
        TreeMap<String, String> hm = new TreeMap<String, String>(new MyCompr());
        //add key-value pair to TreeMap
        hm.put("java", "language");
        hm.put("computer", "machine");
        hm.put("india","country");
        hm.put("mango","fruit");
        hm.put("game","cricket");
        System.out.println("TreeMap Entries:");
        System.out.println(hm);
        Map<String, String> subMap1 = hm.subMap("game", "java");
        System.out.println("Sub-Map enties:");
        System.out.println(subMap1);
        //how to get lower boundary also part of sub-map
        Map<String, String> subMap2 = hm.subMap("game", true, "java", true);
        System.out.println("Sub-Map enties:");
        System.out.println(subMap2);
        //how to omit upper boundary in the sub-map
        Map<String, String> subMap3 = hm.subMap("game", false, "java", true);
        System.out.println("Sub-Map enties:");
        System.out.println(subMap3);
    }
}
 
class MyCompr implements Comparator<String>{
 
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
     
}
