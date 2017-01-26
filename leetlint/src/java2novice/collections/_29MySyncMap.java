package java2novice.collections;
 
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
 
public class _29MySyncMap {
 
    public static void main(String a[]){
         
        Map<String,String> mp = new HashMap<String,String>();
        Map<String,String> sysMapt = Collections.synchronizedMap(mp);
        System.out.println("Synchronized map got created...");
    }
}
/*Program: How to create synchronized map?

Description:
The Map is not synchornized (not thread-safe). 
Collections.synchronizedMap() 
method returns a synchronized (thread-safe) map backed 
by the specified map. In order to guarantee serial access, 
it is critical that all access to the backing map is 
accomplished through the returned map.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/synchronized-map/#sthash.nC0geSHv.dpuf*/