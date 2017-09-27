package _5_Bit;

import java.util.Arrays;
import java.util.Comparator;
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
public class Maximum_Product_of_Word_Lengths {
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



/////////////////////////////////////////////////////////////////////////////

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
}
