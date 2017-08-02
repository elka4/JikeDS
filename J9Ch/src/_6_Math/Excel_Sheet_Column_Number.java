package _6_Math;
import java.util.*;
/*
LeetCode – Excel Sheet Column Number (Java)

Problem

Given a column title as appear in an Excel sheet, return its corresponding column number. For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
    AAA -> 703

 */
public class Excel_Sheet_Column_Number {
/*
Java Solution 1

This problem is related to Excel Sheet Column Title. The key is to use a hashmap to store the mapping between character and integer. Starting from the right-hand side, the converted value for each character is the mapping integer * 26 to the t-th power, where t starts from 0.
 */
public int titleToNumber(String s) {
    if(s==null || s.length() == 0){
        throw new IllegalArgumentException("Input is not valid!");
    }

    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    char c = 'A';
    for(int i=1; i<=26; i++){
        map.put(c, i);
        c += 1;
    }

    int result = 0;
    int i = s.length()-1;
    int t = 0;
    while(i >= 0){
        char curr = s.charAt(i);
        result = result + (int) Math.pow(26, t) * map.get(curr);
        t++;
        i--;
    }

    return result;
}

    /*
    Java Solution 2

Actually using a hashmap is not necessary here.
     */

    public int titleToNumber2(String s) {
        if(s==null || s.length() == 0){
            throw new IllegalArgumentException("Input is not valid!");
        }

        int result = 0;
        int i = s.length()-1;
        int t = 0;
        while(i >= 0){
            char curr = s.charAt(i);
            result = result + (int) Math.pow(26, t) * (curr-'A'+1);
            t++;
            i--;
        }

        return result;
    }
}
