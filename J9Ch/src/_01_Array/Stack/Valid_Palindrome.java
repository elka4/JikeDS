package _01_Array.Stack;

import java.util.Stack;

/*
LeetCode – Valid Palindrome (Java)

Given a string, determine if it is a palindrome,
considering only alphanumeric characters and ignoring cases.

For example, "Red rum, sir, is murder" is a palindrome,
while "Programcreek is awesome" is not.

Note:
Have you consider that the string might be empty?
This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

Thoughts

From start and end loop though the string, i.e., char array.
If it is not alpha or number, increase or decrease pointers.
Compare the alpha and numeric characters.
The solution below is pretty straightforward.
 */


public class Valid_Palindrome {

    //Java Solution 1 - Naive
    public  boolean isPalindrome(String s) {

        if(s == null) return false;
        if(s.length() < 2) return true;

        char[] charArray = s.toCharArray();
        int len = s.length();

        int i=0;
        int j=len-1;

        while(i<j){
            char left = ' ', right=' ';
            //char left = ' ', right=' ';   ????

            while(i<len-1 && !isAlpha(left) && !isNum(left)){
                i++;
                left =  charArray[i];
            }

            while(j>0 && !isAlpha(right) && !isNum(right)){
                j--;
                right = charArray[j];
            }

            if(i >= j)
                break;

            left =  charArray[i];
            right = charArray[j];

            if(!isSame(left, right)){
                return false;
            }

            i++;
            j--;
        }
        return true;
    }

    public  boolean isAlpha(char a){
        if((a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z')){
            return true;
        }else{
            return false;
        }
    }

    public  boolean isNum(char a){
        if(a >= '0' && a <= '9'){
            return true;
        }else{
            return false;
        }
    }

    public  boolean isSame(char a, char b){
        if(isNum(a) && isNum(b)){
            return a == b;
        }else if(Character.toLowerCase(a) == Character.toLowerCase(b)){
            return true;
        }else{
            return false;
        }
    }

////////////////////////////////////

    //Java Solution 2 - Using Stack

    //This solution removes the special characters first. (Thanks to Tia)

    public boolean isPalindrome2(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int len = s.length();
        if (len < 2)
            return true;

        Stack<Character> stack = new Stack<Character>();

        int index = 0;
        while (index < len / 2) {
            stack.push(s.charAt(index));
            index++;
        }

        if (len % 2 == 1)
            index++;

        while (index < len) {
            if (stack.empty())
                return false;

            char temp = stack.pop();
            if (s.charAt(index) != temp)
                return false;
            else
                index++;
        }

        return true;
    }


//////////////////////////////////////////

    //Java Solution 3 - Using Two Pointers

    //In the discussion below, April and Frank use two pointers to solve this problem. This solution looks really simple.
    public  boolean isValidPalindrome3(String s){
        if(s==null||s.length()==0) return false;

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        System.out.println(s);

        for(int i = 0; i < s.length() ; i++){
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
                return false;
            }
        }

        return true;
    }




//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////






//-------------------------------------------------------------------------//////////////////







}
