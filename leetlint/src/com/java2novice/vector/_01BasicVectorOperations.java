package com.java2novice.vector;
 
import java.util.Vector;
 
public class _01BasicVectorOperations {
 
public static void main(String a[]){
    Vector<String> vct = new Vector<String>();
    //adding elements to the end
    vct.add("First");
    vct.add("Second");
    vct.add("Third");
    System.out.println(vct);
    //adding element at specified index
    vct.add(2,"Random");
    System.out.println(vct);
    //getting elements by index
    System.out.println("Element at index 3 is: "+vct.get(3));
    //getting first element
    System.out.println("The first element of this vector is: "+vct.firstElement());
    //getting last element
    System.out.println("The last element of this vector is: "+vct.lastElement());
    //how to check vector is empty or not
    System.out.println("Is this vector empty? "+vct.isEmpty());
}
}
/*Program: Basic Vector Operations.

Description:
Below example shows how to create vector object, 
adding elements to it, 
getting elements by specifying index, getting elements 
index, getting first element, getting last element,
 and is vector is empty or not.

- See more at: http://www.java2novice.com/java-collections-and-util/vector/basic-operations/#sthash.p9d4W0uC.dpuf*/