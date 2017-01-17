package com.java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
 
public class _04MyCheckedCollection {
 
	public static void main(String a[]){
         
        List myList = new ArrayList();
        myList.add("one");
        myList.add("two");
        myList.add("three");
        myList.add("four");
        Collection chkList = Collections.checkedCollection(myList, String.class);
        System.out.println("Checked list content: "+chkList);
        //you can add any type of elements to myList object
        myList.add(10);
        //you cannot add any type of elements to chkList object, doing so
        //throws ClassCastException
        chkList.add(10); //throws ClassCastException
    }
}
/*Program: Write an example for Collections.checkedCollection()
 *  method.
 

Description:
Collections.checkedCollection() method returns a dynamically 
typesafe view of the specified collection. Any attempt to 
insert an element of the wrong type will result in an 
immediate ClassCastException. Assuming a collection contains
 no incorrectly typed elements prior to the time a dynamically 
 generated, and that all subsequent access to the collection 
 takes place through the view, it is guaranteed that the 
 collection cannot contain an incorrectly typed element.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/checked-collection/#sthash.kDN0FIPR.dpuf*/