package com.myjava.stokenizerr;
 
import java.util.StringTokenizer;
 
public class _2MyStringTokens {
    public static void main(String a[]){
        String msg = "http://10.123.43.67:80/";
        StringTokenizer st = new StringTokenizer(msg,"://.");
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }
}
/*Java StringTokenizer With Multiple De-limiters Sample Code

Description:
Below example shows how to break a string based on 
multiple delimiters. 
Each character in the constructors delimiter field 
acts as one delimiter.

- See more at: http://www.java2novice.com/stringtokenizer/multiple-delimiters/#sthash.NVpgqKWM.dpuf*/