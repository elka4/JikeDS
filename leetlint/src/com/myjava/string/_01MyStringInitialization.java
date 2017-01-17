package com.myjava.string;
 
public class _01MyStringInitialization {
    public static void main(String a[]){
        String abc = "This is a string object";
        String bcd = new String("this is also string object");
        char[] c = {'a','b','c','d'};
        String cdf = new String(c);
        String junk = abc+" This is another String object";
    }
}
/*String Initialization Sample Code

Description:
Here you can see example for different ways of initializing string. You can create and initialize string object by calling its constructor, and pass the value of the string. Also you can pass character array to the string constructor. You can also directly assign string value to the string reference, which is equals to creating string object by calling constructor. The empty string constructor will create string object with empty value.

- See more at: http://www.java2novice.com/java_string_examples/initialization_sample_code/#sthash.ORVNECgR.dpuf*/