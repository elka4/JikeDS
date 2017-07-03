package com.myjava.string;
 
public class _05MyStringComp {
 
    public static void main(String a[]){
     
        String c = "We are comparing the content with a StringBuffer content";
        StringBuffer sb =
            new StringBuffer("We are comparing the content with a StringBuffer content");
        /**
         * We can use contentEquals() method to compare content with a StringBuffer.
         * It returns boolean value.
         */
        if(c.contentEquals(sb)){
            System.out.println("The content is equal");
        } else {
            System.out.println("The content is not equal");
        }
        StringBuffer asb =
            new StringBuffer("You cannot compare the content with a String content");
        if(c.contentEquals(asb)){
            System.out.println("The content is equal");
        } else {
            System.out.println("The content is not equal");
        }
    }
}
/*How to compare StringBuffer object to String object in java?

Description:
The below example shows how to compare StringBuffer object with String object. String object provides contentEquals() method to compare content with a StringBuffer object.

- See more at: http://www.java2novice.com/java_string_examples/stringbuffer_compare_strings/#sthash.hJjjnmuk.dpuf*/