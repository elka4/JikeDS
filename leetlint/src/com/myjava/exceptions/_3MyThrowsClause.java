package com.myjava.exceptions;
 
public class _3MyThrowsClause {
    public static void main(String a[]){
        _3MyThrowsClause mytc = new _3MyThrowsClause();
        try{
            for(int i=0; i<5; i++){
                mytc.getJunk();
                System.out.println(i);
            }
        } catch (InterruptedException iex){
            iex.printStackTrace();
        }
    }
     
    public void getJunk() throws InterruptedException {
        Thread.sleep(1000);
    }
}

/*Throws Clause Examples
The 'throws' clause in java programming language is belongs to a method to specify that the method raises particular type of exception while being executed.
The 'throws' clause takes arguments as a list of the objects of type 'Throwables' class.
Anybody calling a method with a throws clause is needed to be enclosed within the try catch blocks.
- See more at: http://www.java2novice.com/java_exception_handling_examples/throws_clause/#sthash.7Vilpxa5.dpuf*/