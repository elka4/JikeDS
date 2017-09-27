package java2novice.arraylist;

import java.util.ArrayList;
/*Here we can see example for deleting all objects from ArrayList at one
 *  method call. We can do this by calling clear() method on ArrayList, 
 *  it will delete all objects. 
 *  - See more at: http://www.java2novice.com/java-collections-and-util/arraylist/remove-all-elements/#sthash.XlxOYrIV.dpuf*/
public class _05ClearMyArrayList {
 
    public static void main(String a[]){
        ArrayList<String> arrl = new ArrayList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        System.out.println("Actual ArrayList:"+arrl);
        arrl.clear();
        System.out.println("After clear ArrayList:"+arrl);
    }
}
