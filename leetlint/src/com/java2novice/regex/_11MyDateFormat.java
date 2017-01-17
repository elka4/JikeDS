package com.java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _11MyDateFormat {
 
    private static Pattern dateFrmtPtrn =
            Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
     
    public static boolean validateDateFormat(String userName){
         
        Matcher mtch = dateFrmtPtrn.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
     
    public static void main(String a[]){
        System.out.println("Is '03/04/2012' a valid date format? "
                        +validateDateFormat("03/04/2012"));
        System.out.println("Is '12/23/2012' a valid date format? "
                        +validateDateFormat("12/23/2012"));
        System.out.println("Is '12/12/12' a valid date format? "
                        +validateDateFormat("12/12/12"));
        System.out.println("Is '3/4/2012' a valid date format? "
                        +validateDateFormat("3/4/2012"));
    }
}
/*Program: How to validate date format using regular expression in java?

Description:
The below given example shows how to validate date format using regular expression. 
This example checks for dd/mm/yyyy date format.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/valid-date-format/#sthash.lj32pyZp.dpuf*/