package com.myjava.string;

public class _20MyHtmlTagRemover {
	 
    public static void main(String a[]){
        String text = "<B>I don't want this to be bold<\\B>";
        System.out.println(text);
        text = text.replaceAll("\\<.*?\\>", "");
        System.out.println(text);
    }
}
/*Program: How to remove html tags from a string?

Description:
In case if a string contains html tags, then below example helps to trim the html tags from the string. The example uses regular expression to trim the html tags from the string.

- See more at: http://www.java2novice.com/java_string_examples/remove-html-tags/#sthash.ofbL3EPn.dpuf*/