package java2novice.collections;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class _16MySubListIndex {
 
    public static void main(String a[]){
         
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("c");
        list.add("c++");
        list.add("unix");
        list.add("perl");
        list.add("php");
        list.add("javascript");
        list.add("ruby");
        list.add(".net");
        list.add("java2novice/jdbc");
        list.add("servlets");
        List<String> subList = new ArrayList<String>();
        subList.add("php");
        subList.add("javascript");
        subList.add("ruby");
        System.out.println("Index of sublist: "+Collections.indexOfSubList(list, subList));
    }
}
/*Program: How to get index of a sub list from another list?

Description:
Collections.indexOfSubList() method returns the starting position of the first occurrence 
of the specified target list within the specified source list,
 or -1 if there is no such occurrence.

- See more at: http://www.java2novice.com/java-collections-and-util/collections/sub-list-index/#sthash.7YF50wVp.dpuf*/