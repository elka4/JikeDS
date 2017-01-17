package com.java2novice.arraylist;

import java.util.ArrayList;
/*Here we can see example for copying all content of ArrayList to an array. You can get this done by calling toArray() method. 
 * - See more at: http://www.java2novice.com/java-collections-and-util/arraylist/to-array/#sthash.XnN0CiWb.dpuf*/
public class _07MyArrayListArray {
 
    public static void main(String a[]){
        ArrayList<String> arrl = new ArrayList<String>();
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        System.out.println("Actual ArrayList:"+arrl);
        String[] strArr = new String[arrl.size()];
        arrl.toArray(strArr);
        System.out.println("Created Array content:");
        for(String str:strArr){
            System.out.println(str);
        }
    }
}
