package com.myjava.stokenizerr;
 
import java.util.StringTokenizer;
 
public class _1MyStringTokenizer {
    public static void main(String a[]){
        String msg = "This program gives sample code for String Tokenizer";
        StringTokenizer st = new StringTokenizer(msg," ");
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }
}
/*Java Simple StringTokenizer Sample Code

Description:
The string tokenizer class allows an application 
to break a string into tokens. 
The tokenization method is much simpler than the 
one used by the StreamTokenizer class. 
The StringTokenizer methods do not distinguish among
 identifiers, numbers, and quoted strings, nor do
  they recognize and skip comments. 
  The set of delimiters (the characters that separate
   tokens) may be specified either at creation time or 
   on a per-token basis.

- See more at: http://www.java2novice.com/stringtokenizer/tokens-by-delimiters/#sthash.zzmQzA8Q.dpuf*/