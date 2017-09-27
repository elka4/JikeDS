package java2novice.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class _01CollectionsAddAll {
 
    public static void main(String a[]){
         
        List<String> myList = new ArrayList<String>();
        myList.add("java");
        myList.add("c");
        myList.add("c++");
        System.out.println("Initial list:"+myList);
        Collections.addAll(myList, "perl","php");
        System.out.println("After adding elements:"+myList);
        String[] strArr = {".Net", "unix"};
        Collections.addAll(myList, strArr);
        System.out.println("After adding array:"+myList);
    }
}
/*Program: How to add all elements to the given collection object?

Description:
Collections.addAll() method adds all of the specified elements to 
the specified collection. 
Elements to be added may be specified individually or as an array. 
The behavior of this convenience method is identical to that of 
c.addAll(Arrays.asList(elements)), but this method is likely to
 run significantly faster under most implementations.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/addall/#sthash.4aBymN6U.dpuf*/