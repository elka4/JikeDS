package java2novice.linkedlist;

import java.util.LinkedList;
/*Here we can see example for creating duplicate object of an LinkedList 
 * instance. we can done this by using clone() function. 
 * - See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/copy-clone/#sthash.aTnkX2Is.dpuf*/
public class _03MyLinkedListClone {
 
    public static void main(String a[]){
         
        LinkedList<String> arrl = new LinkedList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        System.out.println("Actual LinkedList:"+arrl);
        LinkedList<String> copy = (LinkedList<String>) arrl.clone();
        System.out.println("Cloned LinkedList:"+copy);

        System.out.println("===============");

        arrl.remove("First");
        System.out.println("Actual LinkedList:"+arrl);
        System.out.println("Cloned LinkedList:"+copy);
    }
}
