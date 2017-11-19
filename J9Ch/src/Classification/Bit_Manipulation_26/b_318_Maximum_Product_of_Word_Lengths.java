package Classification.Bit_Manipulation_26;
import java.util.*;

public class b_318_Maximum_Product_of_Word_Lengths {

    //JAVA----------Easy Version To Understand!!!!!!!!!!!!!!!!!
    public  int maxProduct(String[] words) {
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

//-------------------------------------------------------------------------///////////
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
//-------------------------------------------------------------------------///////////
public int maxProduct3(String[] words) {
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
//-------------------------------------------------------------------------///////////

//-------------------------------------------------------------------------///////////

//-------------------------------------------------------------------------///////////

//-------------------------------------------------------------------------///////////

}


/*

Given a string array words, find the maximum value of length(word[i]) * length(word[j])
 where the two words do not share common letters. You may assume that each word will
  contain only lower case letters. If no such two words exist, return 0.

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

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */