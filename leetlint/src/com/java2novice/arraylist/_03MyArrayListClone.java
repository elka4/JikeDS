package com.java2novice.arraylist;

import java.util.ArrayList;
/*
 * Here we can see example for creating duplicate object of an ArrayList 
 * instance. we can done this by using clone() function. 
 * - See more at: http://www.java2novice.com/java-collections-and-util/arraylist/copy-clone/#sthash.AW6RFsul.dpuf
 */
public class _03MyArrayListClone {
 
    public static void main(String a[]){
        ArrayList<String> arrl = new ArrayList<String>();
        //adding elements to the end
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        System.out.println("Actual ArrayList:"+arrl);
        ArrayList<String> copy = (ArrayList<String>) arrl.clone();
        System.out.println("Cloned ArrayList:"+copy);
    }
}
