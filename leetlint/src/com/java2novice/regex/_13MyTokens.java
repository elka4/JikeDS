package com.java2novice.regex;

import java.util.regex.Pattern;
 
public class _13MyTokens {
 
    public static void main(String a[]){
         
        String str = "I have a cat. I play cricket with bat. I dont like rat,"+
            "i hate mats, now break it";
        Pattern ptn = Pattern.compile("(cat|rat|mat|bat)");
        String[] parts = ptn.split(str);
        for(String p:parts){
            System.out.println(p);
        }
    }
}
/*Program: How to split a string using regular expression?

Description:
We can split a string using regular expression pattern. 
Example shows how to split the string.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/split/#sthash.dk6kaDdF.dpuf*/