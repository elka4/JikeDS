package com.myjava.string;
 
public class _11MyStrSplit {
 
    public static void main(String a[]){
     
        String str = "This program splits a string based on space";
        String[] tokens = str.split(" ");
        for(String s:tokens){
            System.out.println(s);
        }
        str = "This     program  splits a string based on space";
        tokens = str.split("\\s+");
    }
}

/*How to brake or split a string with a delimiter in java?

Description:
Below example shows how to split or brake a string. The split() method splits the string based on the given regular expression or delimiter, and returns the tokens in the form of array. Below example shows splitting string with space, and second split is based on any kind of spaces, that includes tab, enter, line breaks, etc.

- See more at: http://www.java2novice.com/java_string_examples/Split_sample_code/#sthash.AIlcZaWs.dpuf*/