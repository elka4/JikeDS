package com.myjava.stokenizerr;
 
import java.util.StringTokenizer;
 
public class _3MyTokenCount {
    public static void main(String a[]){
        String msg = "This program gives sample code for String Tokenizer";
        StringTokenizer st = new StringTokenizer(msg," ");
        System.out.println("Count: "+st.countTokens());
    }
}
/*Java StringTokenizer Token Count Sample Code

Description:
Below example shows no of token count after breaking 
the string by delimiter. 
You can get the count by using countTokens() method.

- See more at: http://www.java2novice.com/stringtokenizer/token-count/#sthash.fs8t2TDO.dpuf*/