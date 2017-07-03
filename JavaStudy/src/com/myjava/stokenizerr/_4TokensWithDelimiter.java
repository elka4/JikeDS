package com.myjava.stokenizerr;
 
import java.util.StringTokenizer;
 
public class _4TokensWithDelimiter {
    public static void main(String a[]){
        String msg = "http://10.123.43.67:80/";
        StringTokenizer st = new StringTokenizer(msg,"://.",true);
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }
}
/*Java StringTokenizer Delimiters In Tokens Sample Code

Description:
Below example shows how to make delimiter 
characters also be part of tokens. 
During creating string tokenizer object, 
in the constructor field, pass boolean parameter
 value as true.

- See more at: http://www.java2novice.com/stringtokenizer/delimiter-in-tokens/#sthash.ZW20GdBV.dpuf*/