package java2novice.collections;
 
import java.util.Collections;
import java.util.Map;
 
public class _11MyEmptyMap {
 
    public static void main(String a[]){
         
        Map<String, String> myEmptyMap = Collections.<String, String>emptyMap();
        System.out.println("Empty map: "+myEmptyMap);
    }
}
/*Program: How to create empty map using Collections class?

Description:
Collections.emptyMap() method returns the empty map (immutable). 
This map is serializable.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/empty-map/#sthash.9qXXUzly.dpuf*/