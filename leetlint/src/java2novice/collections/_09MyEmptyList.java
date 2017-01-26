package java2novice.collections;
 
import java.util.Collections;
import java.util.List;
 
public class _09MyEmptyList {
 
    public static void main(String a[]){
         
        List<String> myEmptyList = Collections.<String>emptyList();
        System.out.println("Empty list: "+myEmptyList);
    }
}
/*Program: How to create empty list using Collections class?

Description:
Collections.emptyList() method returns the empty list (immutable). 
This list is serializable.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/empty-list/#sthash.Cq8Y8vPQ.dpuf*/