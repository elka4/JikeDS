package com.myjava.string;
 
import java.util.Locale;
 
public class _16MyStringFormatter {
 
    public static void main(String a[]){
     
        String str = "This is %s format example";
        System.out.println(String.format(str, "string"));
        String str1 = "We are displaying no %d";
        System.out.println(String.format(str1, 10));
        /**
         * String format by specifying Locale details
         */
        System.out.println("String format with Locale info:");
        System.out.println(String.format(Locale.US, str1, 10));
    }
}

/*How to format given string in java?

Description:
String.format() method helps us to format the given string. It replaces each format item in a specified string with the text equivalent of a corresponding object's value. Example can explain more:

- See more at: http://www.java2novice.com/java_string_examples/format/#sthash.xTYJx6xb.dpuf*/