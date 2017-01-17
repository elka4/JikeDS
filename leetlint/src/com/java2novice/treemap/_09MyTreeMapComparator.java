package com.java2novice.treemap;

import java.util.Comparator;
import java.util.TreeMap;
 /*Program: How to sort keys in TreeMap by using Comparator?

Description:
Below example shows how to use Comparator with TreeMap. 
Comarator is required to implement your own sorting logic. 
You have to provide the sorting logic inside compare methods. 
Comparator is very much useful if you are working with user defined objects.

- See more at: http://www.java2novice.com/java-collections-and-util/treemap/basic-comparator/#sthash.vWh3QCmS.dpuf*/
public class _09MyTreeMapComparator {
     
    public static void main(String a[]){
        //the treemap sorts by key
        TreeMap<String, String> hm = new TreeMap<String, String>(new MyComp());
        //add key-value pair to TreeMap
        hm.put("java", "language");
        hm.put("computer", "machine");
        hm.put("india","country");
        hm.put("mango","fruit");
        System.out.println(hm);
    }
}
 
class MyComp implements Comparator<String>{
 
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
     
}
