package java2novice.linkedlist;

import java.util.LinkedList;
/*Program: How to remove elements from LinkedList?

Description:
Below example shows how to remove or delete an element from LinkedList. LinkedList provides few methods to remove elements, those methods are:

remove(): Retrieves and removes the head (first element) of this list.
remove(index): Removes the element at the specified position in this list.
remove(object): Removes the first occurrence of the specified element from this list, if it is present.
removeFirst(): Removes and returns the first element from this list.
removeFirstOccurrence(object): Removes the first occurrence of the specified element in this list (when traversing the list from head to tail).
removeLast(): Removes and returns the last element from this list.
removeLastOccurrence(object): Removes the last occurrence of the specified element in this list (when traversing the list from head to tail).

- See more at: http://www.java2novice.com/java-collections-and-util/linkedlist/remove-methods/#sthash.8Yz0odK2.dpuf*/
public class _20MyAllRemoveOprs {
 
    public static void main(String a[]){
         
        LinkedList<String> arrl = new LinkedList<String>();
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        arrl.add("four");
        arrl.add("five");
        arrl.add("six");
        arrl.add("seven");
        arrl.add("eight");
        arrl.add("nine");
        System.out.println(arrl);

        System.out.println("Remov() method:"+arrl.remove());
        System.out.println("After remove() method call:");
        System.out.println(arrl);

        System.out.println("remove(index) method:"+arrl.remove(2));
        System.out.println("After remove(index) method call:");
        System.out.println(arrl);

        System.out.println("Remov(object) method:"+arrl.remove("six"));
        System.out.println("After remove(object) method call:");
        System.out.println(arrl);

        System.out.println("removeFirst() method:"+arrl.removeFirst());
        System.out.println("After removeFirst() method call:");
        System.out.println(arrl);

        System.out.println("removeFirstOccurrence() method:"
                            +arrl.removeFirstOccurrence("eight"));
        System.out.println("After removeFirstOccurrence() method call:");
        System.out.println(arrl);

        System.out.println("removeLast() method:"+arrl.removeLast());
        System.out.println("After removeLast() method call:");
        System.out.println(arrl);

        System.out.println("removeLastOccurrence() method:"
                            +arrl.removeLastOccurrence("five"));
        System.out.println("After removeLastOccurrence() method call:");
        System.out.println(arrl);
    }
}
