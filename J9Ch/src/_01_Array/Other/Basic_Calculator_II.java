package _01_Array.Other;

/*
LeetCode – Basic Calculator II (Java)

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, /
operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
 */

public class Basic_Calculator_II {

    public int calculate(String s) {
        int md=-1; // 0 is m, 1 is d
        int sign=1; // 1 is +, -1 is -
        int prev=0;
        int result=0;

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int num = c-'0';
                while(++i<s.length() && Character.isDigit(s.charAt(i))){
                    num = num*10+s.charAt(i)-'0';
                }
                i--; // back to last digit of number

                if(md==0){
                    prev = prev * num;
                    md=-1;
                }else if(md==1){
                    prev = prev / num;
                    md=-1;
                }else{
                    prev = num;
                }
            }else if(c=='/'){
                md=1;
            }else if(c=='*'){
                md=0;
            }else if(c=='+'){
                result = result + sign*prev;
                sign=1;
            }else if(c=='-'){
                result = result + sign*prev;
                sign=-1;
            }
        }

        result = result + sign*prev;
        return result;
    }


//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






//-----------------------------------------------------------------------------//






}
