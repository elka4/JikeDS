package com.java2novice.collections;
 
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
 
public class _15MyEnumerationToList {
 
    public static void main(String a[]){
         
        Vector<String> vct = new Vector<String>();
        vct.add("one");
        vct.add("two");
        vct.add("three");
        vct.add("four");
        vct.add("five");
        Enumeration<String> enm = vct.elements();
        List<String> ll = Collections.list(enm);
        System.out.println("List elements: "+ll);
    }
}
/*Program: How to convert Enumeration to List object?

Description:
Collections.list() method returns an array list containing 
the elements returned by the specified enumeration in the 
order they are returned by the enumeration. 
This method provides interoperability between legacy APIs 
that return enumerations and new APIs that require collections.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/list/#sthash.8adEnoV6.dpuf*/