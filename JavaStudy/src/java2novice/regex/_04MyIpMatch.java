package java2novice.regex;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class _04MyIpMatch {
 
    public static boolean isValidIP(String ipAddr){
         
        Pattern ptn = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher mtch = ptn.matcher(ipAddr);
        return mtch.find();
    }
 
    public static void main(String a[]){
        System.out.println("10.23.45.12 is valid? "+_04MyIpMatch.isValidIP("10.23.45.12"));
        System.out.println("10.2a.56.32 is valid? "+_04MyIpMatch.isValidIP("10.2a.56.32"));
        System.out.println("10.23.45 is valid? "+_04MyIpMatch.isValidIP("10.23.45"));
    }
}
/*Program: How to validate IP address using regular expression?

Description:
Below example gives sample code for validation IP address using regular expression.

- See more at: http://www.java2novice.com/java-collections-and-util/regex/ip-validation/#sthash.R7ssznkA.dpuf*/