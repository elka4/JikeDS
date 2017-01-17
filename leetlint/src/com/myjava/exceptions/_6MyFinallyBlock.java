package com.myjava.exceptions;
 
public class _6MyFinallyBlock {
    public static void main(String[] a){
        /**
         * Exception will occur here, after catch block
         * the contol will goto finally block.
         */
        try{
            int i = 10/0;
        } catch(Exception ex){
            System.out.println("Inside 1st catch Block");
        } finally {
            System.out.println("Inside 1st finally block");
        }
        /**
         * In this case exception won't, after executing try block
         * the contol will goto finally block.
         */
        try{
            int i = 10/10;
        } catch(Exception ex){
            System.out.println("Inside 2nd catch Block");
        } finally {
            System.out.println("Inside 2nd finally block");
        }
    }
}
/*Java Finally Block Examples
The finally block always executes immediately after try-catch block exits.
The finally block is executed incase even if an unexpected exception occurs.
The main usage of finally block is to do clean up job. Keeping cleanup code in a finally block is always a good practice, even when no exceptions are occured.
The runtime system always executes the code within the finally block regardless of what happens in the try block. So it is the ideal place to keep cleanup code.
- See more at: http://www.java2novice.com/java_exception_handling_examples/finally_block/#sthash.lFJj2Mco.dpuf*/