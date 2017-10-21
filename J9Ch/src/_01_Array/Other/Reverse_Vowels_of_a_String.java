package _01_Array.Other;

import java.util.ArrayList;
/*
LeetCode – Reverse Vowels of a String (Java)

Write a function that takes a string as input and reverse only the vowels of a string.

Java Solution

this is a simple problem which can be solved by using two pointers scanning from beginning and end of the array.
 */

public class Reverse_Vowels_of_a_String {
    public String reverseVowels(String s) {
        ArrayList<Character> vowList = new ArrayList<Character>();
        vowList.add('a');
        vowList.add('e');
        vowList.add('i');
        vowList.add('o');
        vowList.add('u');
        vowList.add('A');
        vowList.add('E');
        vowList.add('I');
        vowList.add('O');
        vowList.add('U');

        char[] arr = s.toCharArray();

        int i=0;
        int j=s.length()-1;

        while(i<j){
            if(!vowList.contains(arr[i])){
                i++;
                continue;
            }

            if(!vowList.contains(arr[j])){
                j--;
                continue;
            }

            char t = arr[i];
            arr[i]=arr[j];
            arr[j]=t;

            i++;
            j--;
        }

        return new String(arr);
    }

/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////
}
