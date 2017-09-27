package java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class _05MyCheckedList {
 
	public static void main(String a[]){
         
        List myList = new ArrayList();
        myList.add("one");
        myList.add("two");
        myList.add("three");
        myList.add("four");
        List chkList = Collections.checkedList(myList, String.class);
        System.out.println("Checked list content: "+chkList);
        //you can add any type of elements to myList object
        myList.add(10);
        //you cannot add any type of elements to chkList object, doing so
        //throws ClassCastException
        chkList.add(10); //throws ClassCastException
    }
}
/*Program: Write an example for Collections.checkedList() method.

Description:
Collections.checkedList() method returns a dynamically typesafe 
view of the specified list. 
Any attempt to insert an element of the wrong type will result
 in an immediate ClassCastException. Assuming a list contains 
 no incorrectly typed elements prior to the time a dynamically 
 typesafe view is generated, and that all subsequent access to
  the list takes place through the view, it is guaranteed that
   the list cannot contain an incorrectly typed element.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/checked-list/#sthash.Y4qXuzPK.dpuf*/