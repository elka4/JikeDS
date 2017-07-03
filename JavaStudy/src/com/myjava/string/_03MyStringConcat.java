package com.myjava.string;
 
public class _03MyStringConcat {
    public static void main(String a[]){
        String b = "jump ";
        String c = "No jump";
        /**
         *  We can do string concatination by two ways.
         *  One is by using '+' operator, shown below.
         */
        String d = b+c;
        System.out.println(d);
        /**
         *  Another way is by using concat() method,
         *  which appends the specified string at the end.
         */
        d = b.concat(c);
        System.out.println(d);
    }
}

/*How to appent or concat two Strings in java?

Description:
Below example shows different ways of append or concat two string objects. You can append two strings by just using "+" sign. Also you can concatinate two string objects by calling concat() method.

- See more at: http://www.java2novice.com/java_string_examples/concat/#sthash.WWiFexGz.dpuf*/