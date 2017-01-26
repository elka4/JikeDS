package com.myjava.string;
 
public class _06MyStringBytes {
 
    public static void main(String a[]){
     
        String str = "core java api";
        byte[] b = str.getBytes();
        System.out.println("String length: "+str.length());
        System.out.println("Byte array length: "+b.length);
        System.out.println(b[6]);
    }
}
/*How to get byte array from a string object in java?

Description:
Some times we have to convert string object into byte array. You can use getBytes() methode to convert string object to byte array.

- See more at: http://www.java2novice.com/java_string_examples/get_byte_array/#sthash.B1GBQ7G3.dpuf*/