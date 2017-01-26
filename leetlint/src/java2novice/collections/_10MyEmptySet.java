package java2novice.collections;
 
import java.util.Collections;
import java.util.Set;
 
public class _10MyEmptySet {
 
    public static void main(String a[]){
         
        Set<String> myEmptySet = Collections.<String>emptySet();
        System.out.println("Empty set: "+myEmptySet);
    }
}
/*Program: How to create empty set using Collections class?

Description:
Collections.emptySet() method returns the empty set (immutable). 
This set is serializable.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/empty-set/#sthash.LdByXivd.dpuf*/