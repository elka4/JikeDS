package _TwoPointer.Reverse;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


//  345. Reverse Vowels of a String
//  https://leetcode.com/problems/reverse-vowels-of-a-string/description/

public class _345_TwoPointer_Reverse_Vowels_of_a_String_E {
    //Java Standard Two Pointer Solution
/*    In the inner while loop, don't forget the condition "start less than end" while incrementing start and decrementing end. This is my friend's google phone interview question. Cheers!
// update! May use a HashSet<Character> to reduce the look up time to O(1)*/

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

//    Same idea. But use statically declared String as the dictionary and use the indexOf function to avoid String comparison. This code run in 6ms.

    public class Solution2 {
        static final String vowels = "aeiouAEIOU";
        public String reverseVowels(String s) {
            int first = 0, last = s.length() - 1;
            char[] array = s.toCharArray();
            while(first < last){
                while(first < last && vowels.indexOf(array[first]) == -1){
                    first++;
                }
                while(first < last && vowels.indexOf(array[last]) == -1){
                    last--;
                }
                char temp = array[first];
                array[first] = array[last];
                array[last] = temp;
                first++;
                last--;
            }
            return new String(array);
        }
    }


    public static boolean[] vowels = new boolean[300];
    static{
        vowels['a'] = true;
        vowels['o'] = true;
        vowels['e'] = true;
        vowels['i'] = true;
        vowels['u'] = true;
        vowels['A'] = true;
        vowels['O'] = true;
        vowels['E'] = true;
        vowels['I'] = true;
        vowels['U'] = true;
    }
    public String reverseVowels3(String s) {
        if (s == null){
            return null;
        }

        char[] arr = s.toCharArray();
        int maxIndex = arr.length - 1;
        int i = maxIndex, j = 0;
        while (i > j) {
            while (i>=0 && !vowels[arr[i]]){
                i--;
            }

            while (j <= maxIndex && !vowels[arr[j]]){
                j++;
            }

            if (i>= 0 && j <= maxIndex && i > j ){
                char tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i--;
                j++;
            }
        }


        return new String(arr);
    }

//    Try this! But since we traverse the string twice, the running time doubles. Good Luck!

    public class Solution4 {
        public String reverseVowels(String s) {
            if(s == null || s.length()==0){
                return s;
            }
            HashSet<Character> vowels = new HashSet<>();
            vowels.add('a');
            vowels.add('e');
            vowels.add('i');
            vowels.add('o');
            vowels.add('u');

            vowels.add('A');
            vowels.add('E');
            vowels.add('I');
            vowels.add('O');
            vowels.add('U');

            // reverse the  vowels while popping up
            Stack<Character> vStack = new Stack<>();
            for(char c : s.toCharArray()){
                if(vowels.contains(c)){
                    vStack.push(c);
                }
            }

            StringBuilder sb = new StringBuilder();
            for(char c : s.toCharArray()){
                if(vowels.contains(c)){
                    sb.append(vStack.pop());
                }else{
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

//my solution is that use Set and 2 pointers

    public String reverseVowels5(String s) {
        if(s == null || s.length() == 0)
            return s;
        char[] sa = s.toCharArray();
        Set<Character> set = new HashSet<>();
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
        int left = 0, right = sa.length - 1;
        while(left < right) {
            if(!set.contains(sa[left]))
                left++;
            else if(!set.contains(sa[right]))
                right--;
            else {
                char temp = sa[left];
                sa[left] = sa[right];
                sa[right] = temp;
                left++;
                right--;
            }
        }
        return new String(sa);
    }

//        My 6ms solution
    class Solution6{

        public String reverseVowels(String s) {
            if (s.length() == 0) return s;
            char[] arr = s.toCharArray();
            int low = 0;
            int high = arr.length - 1;
            while (low < high) {
                if (!isVowel(arr[low]) && !isVowel(arr[high])) {
                    low++;
                    high--;
                } else if (!isVowel(arr[low])) {
                    low++;
                } else if (!isVowel(arr[high])) {
                    high--;
                } else {
                    char temp = arr[low];
                    arr[low] = arr[high];
                    arr[high] = temp;
                    low++;
                    high--;
                }
            }
            return new String(arr);
        }

        private boolean isVowel(char ch) {
            ch = Character.toLowerCase(ch);
            return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
        }
    }



//    Nice solution! Following solution with similar idea could be used to avoid checking i<j in inner while loops. I think that does not affect time complexity of solution. Correct me if I am wrong.
    class Solution7{
        public String reverseVowels(String s) {
            if (null==s) return s;

            final char[] word = s.toCharArray();

            int i = 0;
            int j = word.length - 1;

            while(i<j){
                if(!isVowel(word[i])){
                    i++;
                    continue;
                }
                if(!isVowel(word[j])){
                    j--;
                    continue;
                }
                swap(word, i++, j--);
            }

            return new String(word);
        }

        private void swap(final char[] word, final int i, final int j){
            final char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
        }

        private boolean isVowel(final char ch){
            switch(Character.toLowerCase(ch)){
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    return true;
                default: return false;
            }
        }

    }


    public class Solution8 {
        public String reverseVowels(String s) {
            Set<Character> se = new HashSet<Character>();

            se.add('a');
            se.add('e');
            se.add('i');
            se.add('o');
            se.add('u');
            se.add('A');
            se.add('E');
            se.add('I');
            se.add('O');
            se.add('U');
            StringBuilder vowel = new StringBuilder();
            for(char c: s.toCharArray())
            {
                if(se.contains(c))
                    vowel.append(c);
            }
            vowel.reverse();
            int i=0;
            StringBuilder output = new StringBuilder();

            for(char c: s.toCharArray())
            {
                if(se.contains(c))
                    output.append(vowel.charAt(i++));
                else
                    output.append(c);
            }
            return output.toString();
        }
    }
////////////////////////////////////////////////////////////////////////
    //jiuzhang
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

}
/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".


 */

/*

 */