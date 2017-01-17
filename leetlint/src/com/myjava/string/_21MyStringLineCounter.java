package com.myjava.string;

public class _21MyStringLineCounter {
	 
    public static int getLineCount(String text){
         
        return text.split("[\n|\r]").length;
    }
     
    public static void main(String a[]){
     
        String str = "line1\nline2\nline3\rline4";
        System.out.println(str);
        int count = getLineCount(str);
        System.out.println("line count: "+count);
    }
}
/*Program: How to get line count from a string?

Description:
This example shows how to get line count from a string. Assuming that we have read the file and keeping the content in string. We are using String.split() method with the use of regular expression [\n|\r]. It will split the string based on the new line char and carriage return char. After the split, we will get string array, and returning length of the array.

- See more at: http://www.java2novice.com/java_string_examples/line-count/#sthash.vkmWXogU.dpuf*/