package java2novice.collections;
 
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
 
public class _06MyCheckedSet {
 
    public static void main(String a[]){
         
        Set mySet = new HashSet();
        mySet.add("one");
        mySet.add("two");
        mySet.add("three");
        mySet.add("four");
        Set chkSet = Collections.checkedSet(mySet, String.class);
        System.out.println("Checked set content: "+chkSet);
        //you can add any type of elements to mySet object
        mySet.add(10);
        //you cannot add any type of elements to chkSet object, doing so
        //throws ClassCastException
        chkSet.add(10); //throws ClassCastException
    }
}

/*Program: Write an example for Collections.checkedSet() method.

Description:
Collections.checkedSet() method returns a dynamically typesafe 
view of the specified set. Any attempt to insert an element 
of the wrong type will result in an immediate 
ClassCastException. Assuming a set contains no incorrectly 
typed elements prior to the time a dynamically typesafe view 
is generated, and that all subsequent access to the set takes 
place through the view, it is guaranteed that the set cannot 
contain an incorrectly typed element.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/checked-set/#sthash.Cm1Zz637.dpuf*/
