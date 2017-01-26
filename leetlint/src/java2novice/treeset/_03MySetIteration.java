package java2novice.treeset;

import java.util.Iterator;
import java.util.TreeSet;
 /*Program: How to read objects from TreeSet using using Iterator?

Description:
Below example shows how to read objects using Iterator. 
By calling iterator() method you will get Iterator object, 
through which you can iterate through all the elements of the TreeSet.

- See more at: http://www.java2novice.com/java-collections-and-util/treeset/treeset-iterator/#sthash.reOgeaBM.dpuf*/
public class _03MySetIteration {
     
    public static void main(String a[]){
         
        TreeSet<String> ts = new TreeSet<String>();
        ts.add("one");
        ts.add("two");
        ts.add("three");
        Iterator<String> itr = ts.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
