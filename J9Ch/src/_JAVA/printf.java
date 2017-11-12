package _JAVA;

import org.junit.Test;

public class printf {
    //https://alvinalexander.com/programming/printf-format-cheat-sheet

    @Test
    public void test01(){
        System.out.printf("the %s jumped over the %s, %d times", "cow", "moon", 2);
        //the cow jumped over the moon, 2 times
    }

    @Test
    public void test02(){
        System.out.format("the %s jumped over the %s, %d times", "cow", "moon", 2);
        System.err.format("the %s jumped over the %s, %d times", "cow", "moon", 2);
        String result = String.format("the %s jumped over the %s, %d times", "cow", "moon", 2);
    }
/*
%c	character
%d	decimal (integer) number (base 10)
%e	exponential floating-point number
%f	floating-point number
%i	integer (base 10)
%o	octal number (base 8)
%s	a string of characters
%u	unsigned decimal (integer) number
%x	number in hexadecimal (base 16)
%%	print a percent sign
\%	print a percent sign
 */
}
