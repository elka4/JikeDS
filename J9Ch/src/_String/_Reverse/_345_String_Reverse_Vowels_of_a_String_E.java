package _String._Reverse;
import java.util.*;
import org.junit.Test;

//  345. Reverse Vowels of a String
//  https://leetcode.com/problems/reverse-vowels-of-a-string/description/
//  http://www.lintcode.com/problem/reverse-vowels-of-a-string/
//
//
public class _345_String_Reverse_Vowels_of_a_String_E {
//------------------------------------------------------------------------------
//Java Standard Two Pointer Solution
//    In the inner while loop, don't forget the condition "start less than end" while incrementing start and decrementing end. This is my friend's google phone interview question. Cheers!
// update! May use a HashSet<Character> to reduce the look up time to O(1)

    public class Solution1 {
        public String reverseVowels(String s) {
            if(s == null || s.length()==0) return s;
            String vowels = "aeiouAEIOU";
            char[] chars = s.toCharArray();
            int start = 0;
            int end = s.length()-1;
            while(start<end){

                while(start<end && !vowels.contains(chars[start]+"")){
                    start++;
                }

                while(start<end && !vowels.contains(chars[end]+"")){
                    end--;
                }

                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;

                start++;
                end--;
            }
            return new String(chars);
        }
    }
//------------------------------------------------------------------------------

    //One pass Java Solution 13ms
    public class Solution2 {
        public String reverseVowels(String s) {
            char[] list=s.toCharArray();
            Set<Character> set=new HashSet<>();
            set.add('a');
            set.add('e');
            set.add('i');
            set.add('o');
            set.add('u');
            set.add('A');
            set.add('E');
            set.add('I');
            set.add('O');
            set.add('U');
            for (int i=0, j=list.length-1; i<j; ) {
                if (!set.contains(list[i])) {
                    i++;
                    continue;
                }
                if (!set.contains(list[j])) {
                    j--;
                    continue;
                }
                char temp=list[i];
                list[i]=list[j];
                list[j]=temp;
                i++;
                j--;
            }
            return String.valueOf(list);
        }
    }
    //We could also initilize the set like this:

    Set<Character> vowels = new HashSet<>(Arrays.asList(new Character[]{'a','e','i','o','u','A','E','I','O','U'}));

//------------------------------------------------------------------------------
    //Simple Java Solution using StringBuilder
    public class Solution3 {
        public String reverseVowels(String s) {
            StringBuilder sb = new StringBuilder();
            int j = s.length() - 1;
            for (int i = 0; i < s.length(); i++)
            {
                if ("AEIOUaeiou".indexOf(s.charAt(i)) != -1)
                {
                    while (j >= 0 && "AEIOUaeiou".indexOf(s.charAt(j)) == -1)
                    {
                        j--;
                    }
                    sb.append(s.charAt(j));
                    j--;
                }
                else
                    sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }


//------------------------------------------------------------------------------
    //9Ch
    public class Jiuzhang {
        public String reverseVowels(String s) {
            int[] pos = new int[s.length()];
            int cnt = 0;
            HashSet<Character> vowel = new HashSet<Character>();
            vowel.add('a');
            vowel.add('e');
            vowel.add('i');
            vowel.add('o');
            vowel.add('u');
            vowel.add('A');
            vowel.add('E');
            vowel.add('I');
            vowel.add('O');
            vowel.add('U');

            for (int i = 0; i < s.length(); i++) {
                if (vowel.contains(s.charAt(i))) {
                    pos[cnt] = i;
                    cnt++;
                }
            }

            char[] ans = new char[s.length()];
            ans = s.toCharArray();
            for (int i = 0; i < cnt; i++) {
                ans[pos[i]] = s.charAt(pos[cnt - i - 1]);
            }
            return String.valueOf(ans);
        }
    }

//------------------------------------------------------------------------------
}
/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

Seen this question in a real interview before?   Yes  No
Companies
Google

Related Topics
Two Pointers String

Similar Questions
Reverse String
 */