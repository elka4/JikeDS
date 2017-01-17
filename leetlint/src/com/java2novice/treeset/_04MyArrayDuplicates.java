package com.java2novice.treeset;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
 
public class _04MyArrayDuplicates {
 
    public static void main(String a[]){
        String[] strArr = {"one","two","three","four","four","five"};
        //convert string array to list
        List<String> tmpList = Arrays.asList(strArr);
        //create a treeset with the list, which eliminates duplicates
        TreeSet<String> unique = new TreeSet<String>(tmpList);
        System.out.println(unique);
    }
}

/*Program: Write a program to remove duplicate entries from an array.

Description:
The easiest way to remove duplicate entries from the given array is, 
create TreeSet object and add array entries to the TreeSet. 
Since the set doesnot support duplicate entries, 
you will get only unique elements left with TreeSet.

- See more at: http://www.java2novice.com/java-collections-and-util/treeset/array-duplicate-values/#sthash.HSGqdZIq.dpuf*/