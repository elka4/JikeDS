package com.myjava.string;
 
public class _08MyStrLastIndexOf {
 
    public static void main(String a[]){
     
        String str = "Use this string for testing this";
        System.out.println("Basic lastIndexOf() example");
        System.out.println("Char 's' at last occurance: "+str.lastIndexOf('s'));
        System.out.println("String \"this\" at last occurance: "+str.lastIndexOf("this"));
        /**
         * Returns the last occurance from specified start index,
         * searching backward starting at the specified index.
         */
        System.out.println("first occurance of char 's' from 24th index backwards: "
                            +str.lastIndexOf('s',24));
        System.out.println("First occurance of String \"this\" from 26th index backwards: "
                            +str.lastIndexOf("this",26));
    }
}

/*Java String lastIndexOf() Sample Code

Description:
Below example shows how to get index of a given character or string from a string in the reverse order, means last occuring index. By using lastIndexOf() method you can get last occurence of the the reference string or character. Also lastIndexOf() method gives last occurence based on a specific position.

- See more at: http://www.java2novice.com/java_string_examples/last_index_of/#sthash.MJIuw8Uq.dpuf*/