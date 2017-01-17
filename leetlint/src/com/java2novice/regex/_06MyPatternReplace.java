package com.java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _06MyPatternReplace {
 
    public String replaceWithPattern(String str,String replace){
         
        Pattern ptn = Pattern.compile("\\s+");
        Matcher mtch = ptn.matcher(str);
        return mtch.replaceAll(replace);
    }
     
    public static void main(String a[]){
        String str = "My    name    is  kingkon.  ";
        _06MyPatternReplace mpr = new _06MyPatternReplace();
        System.out.println(mpr.replaceWithPattern(str, " "));
    }
}

/*Program: How to remove multiple spaces with a single 
 * space with in a string?
 

Description:
Below example gives sample code for replacing multiple
 spaces into single space. 
It covers tab, new line, any kind of spaces replaces 
with single space.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/multiple-spaces/#sthash.f7UesiWW.dpuf*/