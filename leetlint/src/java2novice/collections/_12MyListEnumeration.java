package java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
 
public class _12MyListEnumeration {
 
    public static void main(String a[]){
         
        List<String> ls = new ArrayList<String>();
        ls.add("one");
        ls.add("two");
        ls.add("three");
        ls.add("four");
        Enumeration<String> enm = Collections.enumeration(ls);
        while(enm.hasMoreElements()){
            System.out.println(enm.nextElement());
        }
    }
}
/*Program: How to Enumeration for ArrayList object?

Description:
Below example shows how to get enumeration object for ArrayList object. 
Collections.enumeration() method provides enumeration object.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/list-enumeration/#sthash.06lgC6bt.dpuf*/