package java2novice.arraylist;

import java.util.ArrayList;
import java.util.Collections;
 /*This example shows how to reverse ArrayList content. 
  * You can reverse the content by calling Collections.reverse() method. 
  * You have to pass ArrayList instance to this method, 
  * which reverses the content. 
  * - See more at: http://www.java2novice.com/java-collections-and-util/arraylist/content-reverse/#sthash.2piVyc6i.dpuf*/
public class _10MyArrayListReverse {
 
    public static void main(String a[]){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Java");
        list.add("Cric");
        list.add("Play");
        list.add("Watch");
        list.add("Glass");
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
      
        /*System.out.println("Results after reverse operation:");
        for(String str: list){
            System.out.println(str);
        }*/
    }
}
