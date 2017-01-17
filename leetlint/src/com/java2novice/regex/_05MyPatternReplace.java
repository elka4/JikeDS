package com.java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _05MyPatternReplace {
 
    public String replaceWithPattern(String str,String replace){
         
        Pattern ptn = Pattern.compile("\\s+");
        Matcher mtch = ptn.matcher(str);
        return mtch.replaceAll(replace);
    }
     
    public static void main(String a[]){
        String str = "My    name    is  kingkon.  ";
        _05MyPatternReplace mpr = new _05MyPatternReplace();
        System.out.println(mpr.replaceWithPattern(str, " "));
    }
}

/*Program: How to replace a pattern using regular expression in java?

Description:
Below example gives sample code for replacing given regular expression
 pattern with a text.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/replace-all/#sthash.kBZVFiCk.dpuf*/