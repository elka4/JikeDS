package com.java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _03MyPatternMatchCaseInsensitive {
 
    public boolean isPatternMatching(String line){
         
        Pattern ptn = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        Matcher mtch = ptn.matcher(line);
        if(mtch.find()){
            return true;
        }
        return false;
    }
     
    public static void main(String a[]){
        _03MyPatternMatchCaseInsensitive msp = new _03MyPatternMatchCaseInsensitive();
        System.out.println(msp.isPatternMatching("This line contains java, lets see"));
        System.out.println(msp.isPatternMatching("Here JAVA is in capital leters, lets see"));
        System.out.println(msp.isPatternMatching("Here C++ is in capital leters, lets see"));
    }
}

/*Program: Java regex with case insensitive.

Description:
We need to pass Pattern.CASE_INSENSITIVE constant to 
Pattern.compile() method, 
along with regular expression. 
This makes case insensitive matches.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/case-insensitive/#sthash.PnV6AEOz.dpuf*/