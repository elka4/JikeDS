package com.java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _07MyUsernameValidate {
 
    private static Pattern usrNamePtrn = Pattern.compile("^[a-z0-9_-]{6,14}$");
     
    public static boolean validateUserName(String userName){
         
        Matcher mtch = usrNamePtrn.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
     
    public static void main(String a[]){
        System.out.println("Is 'java2novice' a valid user name? "
                        +validateUserName("java2novice"));
        System.out.println("Is 'cric' a valid user name? "
                        +validateUserName("cric"));
        System.out.println("Is 'JAVA2NOVICE' a valid user name? "
                        +validateUserName("JAVA2NOVICE"));
        System.out.println("Is 'java.2.novice' a valid user name? "
                        +validateUserName("java.2.novice"));
        System.out.println("Is 'java_2-novice' a valid user name? "
                        +validateUserName("java_2-novice"));
    }
}

/*Program: How to validate user name using regular expression?

Description:
The below given example shows how to validate a user name. 
The regular expression pattern allows lower case alphanumeric 
characters, allows '-', '_'. Incase if you want to 
support uppercase characters then regular expression 
should be ^[a-zA-Z0-9_-]{6,14}$

- See more at: http://www.java2novice.com/java-collections-and-util/regex/valid-user-name/#sthash.JDEGdhMW.dpuf*/