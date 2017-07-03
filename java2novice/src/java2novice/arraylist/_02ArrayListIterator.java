package java2novice.arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/*Here we can see example for reading all elements from ArrayList
by using Iterator. Also you can iterate through the ArrayList based
on index too.
- See more at: http://www.java2novice.com/java-collections-and-util/arraylist/iterator/#sthash.nwnFFcHW.dpuf*/
public class _02ArrayListIterator {
 
    public static void main(String a[]){
        ArrayList<String> arrl = new ArrayList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        Iterator<String> itr = arrl.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }


        System.out.println("========itr2========");

        ListIterator<String> itr2 = arrl.listIterator(1);
        while(itr2.hasNext()){
            System.out.println(itr2.next());
        }

        System.out.println("========itr2  previous ========");
        while(itr2.hasPrevious()){
            System.out.println(itr2.previous());
        }


    }
}
