package com.java2novice.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
 /*Program: How to reverse sorted keys in a TreeMap?

Description:
Below example shows how to reverse keys from TreeMap based on sorting.
 To reverse keys, you have to sort the TreeMap based on user defined
  objects by using comparator object. You can include you own custom 
  sorting logic with compare method. By passing comparator object to 
  the TreeMap, you can sort the keys based on the logic provided inside 
  the compare method. Once the TreeMap keys are in sorting order, 
  you can call descendingMap() method to reverse the Map object.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/reverse-keys/#sthash.VJID58a2.dpuf*/
public class _14MyReverseOrderMap {
 
    public static void main(String a[]){
        //the treemap sorts by key
        TreeMap<String, String> hm = new TreeMap<String, String>(new MyCopr());
        //add key-value pair to TreeMap
        hm.put("java", "language");
        hm.put("computer", "machine");
        hm.put("india","country");
        hm.put("mango","fruit");
        hm.put("game","cricket");
        System.out.println("TreeMap Entries:");
        System.out.println(hm);
        Map<String, String> rm = hm.descendingMap();
        System.out.println("Reverse Map Content: ");
        System.out.println(rm);
    }
}
 
class MyCopr implements Comparator<String>{
 
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
     
}
