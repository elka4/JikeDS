package java2novice.regex;
 
public class _01MyStringMatches {
 
    public void isStringNumber(String no){
 
        System.out.println(no.matches("(\\d+)"));
    }
     
    public static void main(String a[]){
        _01MyStringMatches msm = new _01MyStringMatches();
        msm.isStringNumber("345");
        msm.isStringNumber("34we");
        msm.isStringNumber("345w12");
        msm.isStringNumber("956");
    }
}

/*Program: Simple regex pattern matching using string matches().

Description:
String.matches() method tells whether or not this string matches 
the given regular expression.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/simple-matches/#sthash.ZauC7JPe.dpuf*/