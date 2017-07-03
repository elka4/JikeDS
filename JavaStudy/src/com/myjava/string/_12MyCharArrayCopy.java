package com.myjava.string;
 
public class _12MyCharArrayCopy {
 
    public static void main(String a[]){
     
        String str = "Copy chars from this string";
        char[] ch = new char[5];
        /**
         * The getChars() method accepts 4 parameters
         * first one is the start index from string
         * second one is the end index from string
         * third one is the destination char array
         * forth one is the start index to append in the
         * char array.
         */
        str.getChars(5, 10, ch, 0);
        System.out.println(ch);
    }
}

/*How to extract Char Array From String in java?

Description:
Below example shows how to copy range of characters from the given string to another character array. By suing getChars() method, you can copy range of characters from the given string.

- See more at: http://www.java2novice.com/java_string_examples/extract_char_array/#sthash.pZ9SNywE.dpuf*/