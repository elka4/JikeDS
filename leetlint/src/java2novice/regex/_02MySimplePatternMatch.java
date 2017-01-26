package java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _02MySimplePatternMatch {
 
    public boolean isPatternMatching(String line){
     
        Pattern ptn = Pattern.compile("\\d{2}$");
        Matcher mtch = ptn.matcher(line);
        if(mtch.find()){
            return true;
        }
        return false;
    }
     
    public static void main(String a[]){
        _02MySimplePatternMatch msp = new _02MySimplePatternMatch();
        System.out.println("Is 'ddfsa12' ends with min 2 digits? "
                    +msp.isPatternMatching("ddfsa12"));
        System.out.println("Is 'ddfsa12adf1' ends with min 2 digits? "
                    +msp.isPatternMatching("ddfsa12adf1"));
    }
}

/*Program: Simple java regex using Pattern and Matcher classes.

Description:
Patter is a compiled representation of a regular expression. 
Matcher is an engine that performs match operations on 
a character sequence by interpreting a Pattern.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/pattern-matcher/#sthash.V1JErVzM.dpuf*/