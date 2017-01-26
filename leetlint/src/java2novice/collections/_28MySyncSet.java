package java2novice.collections;
 
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
 
public class _28MySyncSet {
     
    public static void main(String a[]){
         
        Set<String> ss = new HashSet<String>();
        Set<String> sysSet = Collections.synchronizedSet(ss);
        System.out.println("Synchronized set got created...");
    }
}

/*Program: How to create synchronized set?

Description:
The Set is not synchornized (not thread-safe). 
Collections.synchronizedSet() method returns a synchronized 
(thread-safe) set backed by the specified set. In order to 
guarantee serial access, it is critical that all access to 
the backing set is accomplished through the returned set.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/synchronized-set/#sthash.kx7joMMp.dpuf*/