package com.myjava.string;
 
public class _19MyNonAsciiString {
 
    public static void main(String a[]){
        String str = "Bj��rk����oacute�";
        System.out.println(str);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        System.out.println("After removing non ASCII chars:");
        System.out.println(str);
    }
}
/*Program: How to remove non-ascii characters from a string?

Description:
Some times we need to handle text data, wherein we have to handle only ascii characters. Below example shows how to remove non-ascii characters from the given string by using regular expression.

- See more at: http://www.java2novice.com/java_string_examples/remove-non-ascii-chars/#sthash.2cBEjEwQ.dpuf*/