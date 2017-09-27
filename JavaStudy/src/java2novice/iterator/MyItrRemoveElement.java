package java2novice.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 /*
  * Program: How to remove an element from collection using Iterator object?

Description:
Below example shows how to remove an element from collection object using Iterator object. The remove() method removes from the underlying collection the last element returned by the iterator

- See more at: http://www.java2novice.com/java-collections-and-util/iterator/remove-object/#sthash.Fm0yH2Tf.dpuf
  */
public class MyItrRemoveElement {
 
    public static void main(String a[]){
         
        String removeElem = "Perl";
        List<String> myList = new ArrayList<String>();
        myList.add("Java");
        myList.add("Unix");
        myList.add("Oracle");
        myList.add("C++");
        myList.add("Perl");
        System.out.println("Before remove:");
        System.out.println(myList);
        Iterator<String> itr = myList.iterator();
        while(itr.hasNext()){
            if(removeElem.equals(itr.next())){
                itr.remove();
            }
        }
        System.out.println("After remove:");
        System.out.println(myList);
    }
}
