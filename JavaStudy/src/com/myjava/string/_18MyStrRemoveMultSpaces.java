package com.myjava.string;
 
import java.util.StringTokenizer;
 
public class _18MyStrRemoveMultSpaces {
    public static void main(String a[]){
        String str = "String    With Multiple      Spaces";

        StringTokenizer st = new StringTokenizer(str, " ");

        StringBuffer sb = new StringBuffer();

        while(st.hasMoreElements()){
            sb.append(st.nextElement()).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
/*How to remove multiple spaces in a string in Java?

Description:
Below example shows how to remove multiple spaces from the given string.

- See more at: http://www.java2novice.com/java_string_examples/remove-multiple-spaces/#sthash.hRqnbz5W.dpuf*/