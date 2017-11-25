package _08_Bit;
import java.util.*;
import org.junit.Test;

//  318. Maximum Product of Word Lengths
//  https://leetcode.com/problems/maximum-product-of-word-lengths/description/
//  8:
//
public class _318_Bit_Maximum_Product_of_Word_Lengths_M {
//------------------------------------------------------------------------------
    //1
    //JAVA----------Easy Version To Understand!!!!!!!!!!!!!!!!!
    public static int maxProduct1(String[] words) {
        if (words == null || words.length == 0)
            return 0;
        int len = words.length;
        int[] value = new int[len];
        for (int i = 0; i < len; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                value[i] |= 1 << (tmp.charAt(j) - 'a');
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++) {
                if ((value[i] & value[j]) == 0 &&
                        (words[i].length() * words[j].length() > maxProduct))

                    maxProduct = words[i].length() * words[j].length();
            }
        return maxProduct;
    }

//------------------------------------------------------------------------------
    //2
    //32ms Java AC solution
    public class Solution2 {
        public int maxProduct(String[] words) {
            int max = 0;

            Arrays.sort(words, new Comparator<String>(){
                public int compare(String a, String b){

                    return b.length() - a.length();
                }
            });

            int[] masks = new int[words.length]; // alphabet masks

            for(int i = 0; i < masks.length; i++){
                for(char c: words[i].toCharArray()){
                    masks[i] |= 1 << (c - 'a');
                }
            }

            for(int i = 0; i < masks.length; i++){
                if(words[i].length() * words[i].length() <= max) break; //prunning
                for(int j = i + 1; j < masks.length; j++){
                    if((masks[i] & masks[j]) == 0){
                        max = Math.max(max, words[i].length() * words[j].length());
                        break; //prunning
                    }
                }
            }

            return max;
        }
    }
//------------------------------------------------------------------------------
    //3
    //Bit manipulation Java O(n^2)
    public class Solution3 {
        public int maxProduct(String[] words) {
            int max = 0;
            int[] bytes = new int[words.length];
            for(int i=0;i<words.length;i++){
                int val = 0;
                for(int j=0;j<words[i].length();j++){
                    val |= 1<<(words[i].charAt(j)-'a');
                }
                bytes[i] = val;
            }
            for(int i=0; i<bytes.length; i++){
                for(int j=i+1; j<bytes.length; j++){
                    if((bytes[i] & bytes[j])==0)max =
                            Math.max(max,words[i].length()*words[j].length());
                }
            }
            return max;
        }
    }
//------------------------------------------------------------------------------
    //4
    //Pre-process the word, use bit to represent the words.
    // We can do this because we only need to compare if two words contains the same characters.

    //Java Solution with comments
    public class Solution4 {
        /**
         * @param words
         * @return
         *
         * 		The soultion is calcuated by doing a product of the length of
         *         each string to every other string. Anyhow the constraint given is
         *         that the two strings should not have any common character. This
         *         is taken care by creating a unique number for every string. Image
         *         a an 32 bit integer where 0 bit corresponds to 'a', 1st bit
         *         corresponds to 'b' and so on.
         *
         *         Thus if two strings contain the same character when we do and
         *         "AND" the result will not be zero and we can ignore that case.
         */

        public int maxProduct(String[] words) {
            int[] checker = new int[words.length];
            int max = 0;
            // populating the checker array with their respective numbers
            for (int i = 0; i < checker.length; i++) {
                int num = 0;
                for (int j = 0; j < words[i].length(); j++) {
                    num |= 1 << (words[i].charAt(j) - 'a');
                }
                checker[i] = num;
            }

            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    //checking if the two strings have common character
                    if ((checker[i] & checker[j]) == 0)
                        max = Math.max(max, words[i].length() * words[j].length());
                }
            }
            return max;
        }

    }
//------------------------------------------------------------------------------
    //5
    //Java solution using bit manipulation
    public class Solution5 {
        public int maxProduct(String[] words) {
            int[] mask = new int[words.length];
            for(int i = 0; i < words.length; i++) {
                for(int j = 0; j < words[i].length(); j++) {
                    mask[i] |= 1 << (words[i].charAt(j) - 'a');
                }
            }
            int max = 0;
            for(int i = 0; i < words.length; i++) {
                for(int j = i + 1; j < words.length; j++) {
                    if((mask[i] & mask[j]) == 0) {
                        max = Math.max(words[i].length() * words[j].length(), max);
                    }
                }
            }
            return max;
        }
    }
//------------------------------------------------------------------------------
    //6
    //My java solution (12ms) O(n*n)
    public class Solution6 {
        public int maxProduct(String[] words) {
            int len = words.length;
            int[] mark = new int[len];
            int[] leng = new int[len];
            for (int i = 0;i<len;i++) {
                int k = words[i].length();
                leng[i] = k;
                for (int j=0;j<k;j++) {
                    int p = (int)(words[i].charAt(j)-'a');
                    p = 1 << p;
                    mark[i] = mark[i] | p;
                }
            }
            int ans = 0;
            for (int i = 0;i<len;i++)
                for (int j = i+1;j<len;j++)
                    if ((mark[i]&mark[j])==0)
                        if (ans<leng[i]*leng[j])
                            ans=leng[i]*leng[j];
            return ans;
        }
    }
//    The array store the length is necessary. If we calculate the length every time we need it, it will be very slow.



//------------------------------------------------------------------------------
    //7
    public int maxProduct(String[] words) {
        if(words==null || words.length==0)
            return 0;

        int[] arr = new int[words.length];

        for(int i=0; i<words.length; i++){
            for(int j=0; j<words[i].length(); j++){
                char c = words[i].charAt(j);
                arr[i] |= (1<< (c-'a'));
            }
        }

        int result = 0;

        for(int i=0; i<words.length; i++){
            for(int j=i+1; j<words.length; j++){
                if((arr[i] & arr[j]) == 0){
                    result = Math.max(result, words[i].length()*words[j].length());
                }
            }
        }

        return result;
    }



//------------------------------------------------------------------------------
    //8
    //32ms Java AC solution
    public int maxProduct2(String[] words) {
        int max = 0;

        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b){
                return b.length() - a.length();
            }
        });

        int[] masks = new int[words.length]; // alphabet masks

        for(int i = 0; i < masks.length; i++){
            for(char c: words[i].toCharArray()){
                masks[i] |= 1 << (c - 'a');
            }
        }

        for(int i = 0; i < masks.length; i++){
            if(words[i].length() * words[i].length() <= max) break; //prunning
            for(int j = i + 1; j < masks.length; j++){
                if((masks[i] & masks[j]) == 0){
                    max = Math.max(max, words[i].length() * words[j].length());
                    break; //prunning
                }
            }
        }
        return max;
    }


//------------------------------------------------------------------------------
}
/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.


 */

/*
LeetCode – Maximum Product of Word Lengths

Given a string array words, find the
maximum value of length(word[i]) * length(word[j])
where the two words do not share common letters.

You may assume that each word will contain only lower case letters.
If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

 */

/*感觉上有点像个2D array，一维是word，二维是char

   这个算是使用了bit vector吧

arr, index代表word在words里的index
arr[i]， arr[i] |= (1<< (c-'a'))， 就是在第i个word有哪个char没有哪个char。

int是32位，c-'a'在26以内。就是用一个int代表了一个word关于它的char有无的信息。


arr[i] & arr[j]) == 0 就是这两个word没有相同char。

 */