package java2novice.arraylist;

import java.util.ArrayList;
import java.util.Collections;
 /*This example gives how to swap two elements in the ArrayList. 
  * By calling Collections.swap() method you can swap two elements 
  * of the ArrayList. You have to pass the indexes which you need to swap.
  *  - See more at: http://www.java2novice.com/java-collections-and-util/arraylist/swap/#sthash.6CuwwCow.dpuf*/
public class _12MyArrayListSwap {
 
    public static void main(String a[]){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Java"); //0
        list.add("Cric"); //1
        list.add("Play"); //2
        list.add("Watch"); //3
        list.add("Glass"); //4
        list.add("Movie"); //5
        list.add("Girl"); //6

        System.out.println(list);
        Collections.swap(list, 2, 5);
        System.out.println("Results after swap operation:");
        for(String str: list){
            System.out.print(str + ' ');
        }
    }
}
