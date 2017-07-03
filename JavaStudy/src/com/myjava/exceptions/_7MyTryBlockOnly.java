package com.myjava.exceptions;
 
import java.net.MalformedURLException;
import java.net.URL;
 
public class _7MyTryBlockOnly {
    public static void main(String a[]) throws MalformedURLException{
        try{
            URL url = new URL("http://www.google.com");
        } finally{
            System.out.println("In finally block");
        }
    }
}
/*Java Exception Handling - without cache block example
You can handle exceptions still without having catch blocks also, only thing you need to do is
declare the throws clause in your method signature, so that the calling function would handle the exception.
Before throwing exception, it executes the finally block.

- See more at: http://www.java2novice.com/java_exception_handling_examples/without_catch_block/#sthash.BwZA1QSx.dpuf*/