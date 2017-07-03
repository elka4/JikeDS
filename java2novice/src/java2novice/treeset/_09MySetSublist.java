package java2novice.treeset;


import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class _09MySetSublist {

   public static void main(String a[]){
        
       TreeSet<String> ts = new TreeSet<String>(new MyStrComp());
       ts.add("RED");
       ts.add("ORANGE");
       ts.add("BLUE");
       ts.add("GREEN");
       ts.add("WHITE");
       ts.add("BROWN");
       ts.add("YELLOW");
       ts.add("BLACK");
       System.out.println(ts);
       Set<String> subSet = ts.subSet("GREEN", "WHITE");
       System.out.println("sub set: "+subSet);
       subSet = ts.subSet("GREEN", true, "WHITE", true);
       System.out.println("sub set: "+subSet);
       subSet = ts.subSet("GREEN", false, "WHITE", true);
       System.out.println("sub set: "+subSet);
   }
}

class MyStrComp implements Comparator<String>{

   @Override
   public int compare(String str1, String str2) {
       return str1.compareTo(str2);
   }
    
}
/*Program: How to get subset from sorted set?

Description:
To implement your own sorting functionality with 
TreeSet on user defined objects, you have to pass Comparator
 object along with TreeSet constructor call.
  The Comparator implementation holds the sorting logic. 
  You have to override compare() method to provide the sorting logic on
   user defined objects. Now you can get range of object from the treeset
    by calling subSet() method.

- See more at: http://www.java2novice.com/java-collections-and-util/treeset/subset/#sthash.0FOCWGw7.dpuf*/