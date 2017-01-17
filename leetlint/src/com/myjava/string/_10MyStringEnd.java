package com.myjava.string;
 
public class _10MyStringEnd {
 
    public static void main(String a[]){
     
        String str = "This is a java string example";
        if(str.endsWith("example")){
            System.out.println("This String ends with example");
        } else {
            System.out.println("This String is not ending with example");
        }
        if(str.endsWith("java")){
            System.out.println("This String ends with java");
        } else {
            System.out.println("This String is not ending with java");
        }
    }
}
/*How to find a string ends with another string value in java?

Description:
Below example shows how to find whether a string value ends with another string value. By using endsWith() method, you can get whether the string ends with the given string or not. Also this method tells that the string occurence at a specific position.

- See more at: http://www.java2novice.com/java_string_examples/ends_with/#sthash.NyqWNRFe.dpuf*/