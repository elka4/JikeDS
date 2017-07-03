package java2novice.linkedhashmap;

import java.util.LinkedHashMap;
/*Program: How to delete all entries from LinkedHashMap object?

Description:
By calling clear() method on LinkedHashMap object, 
you can delete all elements at at time.

- See more at: http://www.java2novice.com/java-collections-and-util/linkedhashmap/clear/#sthash.FJMHBQcC.dpuf*/
public class _04MyMapClear {
 
    public static void main(String a[]){
         
        LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
        lhm.put("one", "This is first element");
        lhm.put("two", "This is second element");
        lhm.put("four", "Element inserted at 3rd position");
        System.out.println(lhm);
        lhm.clear();
        System.out.println(lhm);
    }
}
