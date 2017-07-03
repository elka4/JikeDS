package com.myjava.string;
 
public class _02MyArrayCopy {
    public static void main(String a[]){
        char ch[] = {'M','y',' ','J','a','v','a',' ','e','x','a','m','p','l','e'};
        /**
         * We can copy a char array to a string by using
         * copyValueOf() method.
         */
        String chStr = String.copyValueOf(ch);
        System.out.println(chStr);
        /**
         * We can also copy only range of charactors in a
         * char array by copyValueOf() method.
         */
        String subStr = String.copyValueOf(ch,3,4);
        System.out.println(subStr);
    }
}
/*How to convert Character array to String object?

Description:
Below example shows how to convert character array to a string object. By using String.copyValueOf() method you can convert char array to string object. Also you can copy range of character array to string.

- See more at: http://www.java2novice.com/java_string_examples/copy_char_array/#sthash.KYfuFhFp.dpuf*/