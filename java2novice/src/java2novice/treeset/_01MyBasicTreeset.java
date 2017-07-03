package java2novice.treeset;

import java.util.TreeSet;
/*Program: Basic TreeSet Operations.

Description:
Below example shows basic operations on TreeSet like creating object, 
adding elements to it, verifies elements existance, 
deleting all elements at once, size of the set and deleting a specific
 element.

- See more at: http://www.java2novice.com/java-collections-and-util/treeset/basic-operations/#sthash.fvroKm8n.dpuf*/
public class _01MyBasicTreeset {
 
    public static void main(String a[]){
         
        TreeSet<String> ts = new TreeSet<String>();
        ts.add("one");
        ts.add("two");
        ts.add("three");
        System.out.println("Elements: "+ts);
        //check is set empty?
        System.out.println("Is set empty: "+ts.isEmpty());
        //delete all elements from set
        ts.clear();
        System.out.println("Is set empty: "+ts.isEmpty());
        ts.add("one");
        ts.add("two");
        ts.add("three");
        System.out.println("Size of the set: "+ts.size());
        //remove one string
        ts.remove("two");
        System.out.println("Elements: "+ts);
    }
}
