package com.java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _09MyPasswordValidate {
 
private static Pattern pswNamePtrn =
        Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
     
    public static boolean validatePassword(String userName){
         
        Matcher mtch = pswNamePtrn.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
     
    public static void main(String a[]){
        System.out.println("Is 'java2novice' a valid password? "
                            +validatePassword("java2novice"));
        System.out.println("Is 'gabbarsingh' a valid password? "
                            +validatePassword("gabbarsingh"));
        System.out.println("Is 'Java2NOVICE$' a valid password? "
                            +validatePassword("Java2NOVICE$"));
        System.out.println("Is '234aBc#' a valid password? "
                            +validatePassword("234aBc#"));
    }
}

/*Program: How to validate password using regular expression?

Description:
The below given example shows how to validate a password using regular expression.
 Here this regular expression allows must contain one 
 digit, one lower case char, one upper case char, 
 some special chars, length should be within 6 to 15 
 chars.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/valid-password/#sthash.n1AVxrnb.dpuf*/