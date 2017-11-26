package _01_Array.Other_dbf_bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
LeetCode – Letter Combinations of a Phone Number (Java)

Given a digit string, return all possible letter combinations that
the number could represent. (Check out your cellphone to see the mappings)
Input:Digit string "23", Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Analysis

This problem can be solves by a typical DFS algorithm.
DFS problems are very similar and can be solved by using a simple recursion.
Check out the index page to see other DFS problems.
 */

public class Letter_Combinations_of_a_Phone_Number {

    public List<String> letterCombinations(String digits) {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        map.put(0, "");

        ArrayList<String> result = new ArrayList<String>();

        if(digits == null || digits.length() == 0)
            return result;

        ArrayList<Character> temp = new ArrayList<Character>();
        getString(digits, temp, result, map);

        return result;
    }

    public void getString(String digits, ArrayList<Character> temp,
                          ArrayList<String> result,
                          HashMap<Integer, String> map){
        if(digits.length() == 0){
            char[] arr = new char[temp.size()];
            for(int i=0; i<temp.size(); i++){
                arr[i] = temp.get(i);
            }
            result.add(String.valueOf(arr));
            return;
        }

        Integer curr = Integer.valueOf(digits.substring(0,1));
        String letters = map.get(curr);
        for(int i=0; i<letters.length(); i++){
            temp.add(letters.charAt(i));
            getString(digits.substring(1), temp, result, map);
            temp.remove(temp.size()-1);
        }
    }

//--------------------------------------------------------------------------------?????

    public List<String> letterCombinations2(String digits) {
        HashMap<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r','s'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});

        List<String> result = new ArrayList<String>();
        if(digits.equals(""))
            return result;

        helper(result, new StringBuilder(), digits, 0, map);

        return result;

    }

    public void helper(List<String> result, StringBuilder sb, String digits,
                       int index, HashMap<Character, char[]> map){

        if(index>=digits.length()){
            result.add(sb.toString());
            return;
        }

        char c = digits.charAt(index);
        char[] arr = map.get(c);

        for(int i=0; i<arr.length; i++){
            sb.append(arr[i]);
            helper(result, sb, digits, index+1, map);
            sb.deleteCharAt(sb.length()-1);
        }
    }



//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






//-------------------------------------------------------------------------------






}
