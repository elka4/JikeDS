package java2novice.treeset;

import java.util.TreeSet;

public class _05MyDuplicateEntry {
 
    public static void main(String a[]){
        String[] strArr = {"one","two","three","four","four","five"};
        TreeSet<String> unique = new TreeSet<String>();
        for(String str:strArr){
            if(!unique.add(str)){
                System.out.println("Duplicate Entry is: "+str);
            }
        }
    }
}
/*Program: Write a program to find duplicate value from an array.

Description:
The easiest way to find duplicate entries from the given array is,
 create TreeSet object and add array entries to the TreeSet. 
 Since the set doesnot support duplicate entries, 
 you can easily findout duplicate entries. 
 Below example add each element to the set, and checks the returns status.

- See more at: http://www.java2novice.com/java-collections-and-util/treeset/array-duplicate/#sthash.2GyEymau.dpuf*/