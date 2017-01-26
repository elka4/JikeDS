package com.myjava.string;
 
public class _09MyStrStartsWith {
 
    public static void main(String a[]){
     
        String str = "This is an example string.";

        System.out.println("Is this string starts with \"This\"? "
                        +str.startsWith("This"));

        System.out.println("Is this string starts with \"is\"? "
                        +str.startsWith("is"));

        System.out.println("Is this string starts with \"is\" after index 5? "
                        +str.startsWith("is", 5));
    }
}

/*How to find a string start with another string value in java?

Description:
Below example shows how to find whether a string value start with another string value. By using startsWith() method, you can get whether the string starts with the given string or not. Also this method tells that the string occurence at a specific position.

- See more at: http://www.java2novice.com/java_string_examples/starts_with/#sthash.88gC10iR.dpuf*/