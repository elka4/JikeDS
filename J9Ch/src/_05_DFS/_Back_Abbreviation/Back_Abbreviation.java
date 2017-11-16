package _05_DFS._Back_Abbreviation;
import org.junit.Test;
import java.util.*;
@SuppressWarnings("all")


/*
1.  Valid Word Abbreviation
2.  Unique Word Abbreviation
3.  Generalized Abbreviation            backtracking
4.  Minimum Unique Word Abbreviation    backtracking
5.  Word Abbreviation
 */

public class Back_Abbreviation {
/////////  1.  Valid Word Abbreviation， 1.  Valid Word Abbreviation， 1.  Valid Word Abbreviation，
//////Valid Word Abbreviation, Valid Word Abbreviation, Valid Word Abbreviation, Valid Word Abbreviation
//  408. Valid Word Abbreviation
//  https://leetcode.com/problems/valid-word-abbreviation/description/
//  http://www.lintcode.com/zh-cn/problem/check-word-abbreviation/
//  String

    //Simple Regex One-liner (Java, Python)
    //Much nicer, I just turn an abbreviation like "i12iz4n" into a regular expression like "i.{12}iz.{4}n". Duh.
    /*
    I turn each number into that many dots to get a regular expression. For example, when asked whether "3t2de" is a valid abbreviation for word "leetcode", I turn "3t2de" into "...t..de" and check whether that regular expression matches "leetcode", which it does. I also need to rule out the number "0" and leading zeros, which I do with another regular expression.
     */
    public boolean validWordAbbreviation2(String word, String abbr) {
        return word.matches(abbr.replaceAll("[1-9]\\d*", ".{$0}"));
    }
    @Test
    public void testReg(){
        System.out.println("324df".replaceAll("\\d*", ".{$0}"));
    }


//------------------------------------------------------------------------------------
    //Java straightforward, easy understand solution.
    public boolean validWordAbbreviation3(String word, String abbr) {
        if(word == null || abbr == null) return false;
        int num = 0;
        int idx = 0;

        for(char c: abbr.toCharArray()){
            if(c == '0' && num == 0) return false;
            if(c >= '0' && c <= '9'){
                num = num*10 + (c-'0');
            }else{//c是一个字母
                idx += num;
                if(idx >= word.length() || c != word.charAt(idx)) return false;
                num = 0;
                idx++;
            }
        }

        return idx+num == word.length();
    }
//------------------------------------------------------------------------------------

    //Short and easy to understand Java Solution
    public boolean validWordAbbreviation1(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                ++i;++j;
                continue;
            }
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                ++j;
            }
            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }

    @Test
    public void validWordAbbreviation1(){
        System.out.println(validWordAbbreviation1("internationalization", "i12iz4n"));
        System.out.println(validWordAbbreviation1("apple", "a2e"));
    }

/////////  1.  Valid Word Abbreviation， 1.  Valid Word Abbreviation， 1.  Valid Word Abbreviation，

//////  2. Unique Word Abbreviation, 2. Unique Word Abbreviation, 2. Unique Word Abbreviation, 2. Unique Word Abbreviation
//  288. Unique Word Abbreviation
//  https://leetcode.com/problems/unique-word-abbreviation/description/
//  Hash Table, Design

/*
An abbreviation of a word follows the form <first letter><number><last letter>.
Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)
     1
b) d|o|g                   --> d1g
              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n
              1
     1---5----0
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
---------------------------------------------------------------------------------------------------------------
Example:
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
---------------------------------------------------------------------------------------------------------------
 */

    //Approach #2 (Hash Table) [Accepted]
/*
Note that isUnique is called repeatedly for the same set of words in the dictionary each time.
We should pre-process the dictionary to speed it up.

Ideally, a hash table supports constant time look up. What should the key-value pair be?

Well, the idea is to group the words that fall under the same abbreviation together.
For the value, we use a Set instead of a List to guarantee uniqueness.

The logic in isUnique(word) is tricky. You need to consider the following cases:

Does the word's abbreviation exists in the dictionary? If not, then it must be unique.
If above is yes, then it can only be unique if the grouping of the abbreviation contains no other words except word.
*/
    public class ValidWordAbbr2 {
        private final Map<String, Set<String>> abbrDict = new HashMap<>();

        public ValidWordAbbr2(String[] dictionary) {
            for (String s : dictionary) {
                String abbr = toAbbr(s);
                Set<String> words = abbrDict.containsKey(abbr)
                        ? abbrDict.get(abbr) : new HashSet<>();
                words.add(s);
                abbrDict.put(abbr, words);
            }
        }

        public boolean isUnique(String word) {
            String abbr = toAbbr(word);
            Set<String> words = abbrDict.get(abbr);
            return words == null || (words.size() == 1 && words.contains(word));
        }

        private String toAbbr(String s) {
            int n = s.length();
            if (n <= 2) {
                return s;
            }
            return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
        }
    }

    @Test
    public void ValidWordAbbr2_1(){
        String[] dictionary = {"deer", "door", "cake", "card"};
        ValidWordAbbr2 vw = new ValidWordAbbr2(dictionary);
        System.out.println(vw.isUnique("dear") );   //false
        System.out.println(vw.isUnique("cart") );   //true
        System.out.println(vw.isUnique("cane") );   //false
        System.out.println(vw.isUnique("make") );   //true
    }

//---------------------------------------------------------------------------------------------------------------
    //Approach #3 (Hash Table) [Accepted]
    /*
    Let us consider another approach using a counter as the table's value. For example, assume the dictionary = ["door", "deer"], we have the mapping of {"d2r" -> 2}. However, this mapping alone is not enough, because we need to consider whether the word exists in the dictionary. This can be easily overcome by inserting the entire dictionary into a set.

    When an abbreviation's counter exceeds one, we know this abbreviation must not be unique because at least two different words share the same abbreviation. Therefore, we can further simplify the counter to just a boolean.
     */
    public class ValidWordAbbr3 {
        private final Map<String, Boolean> abbrDict = new HashMap<>();
        private final Set<String> dict;

        public ValidWordAbbr3(String[] dictionary) {
            dict = new HashSet<>(Arrays.asList(dictionary));
            for (String s : dict) {
                String abbr = toAbbr(s);
                abbrDict.put(abbr, !abbrDict.containsKey(abbr));
            }
        }

        public boolean isUnique(String word) {
            String abbr = toAbbr(word);
            Boolean hasAbbr = abbrDict.get(abbr);
            return hasAbbr == null || (hasAbbr && dict.contains(word));
        }

        private String toAbbr(String s) {
            int n = s.length();
            if (n <= 2) {
                return s;
            }
            return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
        }
    }

    @Test
    public void ValidWordAbbr3_1(){
        String[] dictionary = {"deer", "door", "cake", "card"};
        ValidWordAbbr2 vw = new ValidWordAbbr2(dictionary);
        System.out.println(vw.isUnique("dear") );   //false
        System.out.println(vw.isUnique("cart") );   //true
        System.out.println(vw.isUnique("cane") );   //false
        System.out.println(vw.isUnique("make") );   //true
    }

//////  2. Unique Word Abbreviation, 2. Unique Word Abbreviation, 2. Unique Word Abbreviation, 2. Unique Word Abbreviation

//////  3. Generalized Abbreviation, 3. Generalized Abbreviation, 3. Generalized Abbreviation, 3. Generalized Abbreviation,
//  320. Generalized Abbreviation
//  https://leetcode.com/problems/generalized-abbreviation/description/
//  Backtracking, Bit Manipulation


    //Approach #1 (Backtracking) [Accepted]
    public List<String> generateAbbreviations1(String word){
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), word, 0, 0);
        return ans;
    }

    // i is the current position
    // k is the count of consecutive abbreviated characters
    private void backtrack(List<String> ans, StringBuilder builder, String word, int i, int k){
        int len = builder.length(); // keep the length of builder
        if(i == word.length()){
            if (k != 0) builder.append(k); // append the last k if non zero
            ans.add(builder.toString());
        } else {
            // the branch that word.charAt(i) is abbreviated
            backtrack(ans, builder, word, i + 1, k + 1);

            // the branch that word.charAt(i) is kept
            if (k != 0) builder.append(k);
            builder.append(word.charAt(i));
            backtrack(ans, builder, word, i + 1, 0);
        }
        builder.setLength(len); // reset builder to the original state去掉len后面的
    }

    @Test
    public void generateAbbreviations1_01(){
        System.out.println(generateAbbreviations1("word"));
    }//[4, 3d, 2r1, 2rd, 1o2, 1o1d, 1or1, 1ord, w3, w2d, w1r1,
    // w1rd, wo2, wo1d, wor1, word]

    @Test
    public void generateAbbreviations1_02(){
        StringBuilder sb = new StringBuilder("1234");
        System.out.println(sb);
        sb.setLength(3);
        System.out.println(sb);
    }

//---------------------------------------------------------------------------------------------

    //Java backtracking solution
    //这个其实是DFS，不是backtracking
    public List<String> generateAbbreviations3(String word){
        List<String> ret = new ArrayList<String>();
        backtrack(ret, word, 0, "", 0);
        return ret;
    }

    private void backtrack(List<String> ret, String word, int pos, String cur, int count){
        if(pos==word.length()){
            if(count > 0) cur += count;
            ret.add(cur);
            return;
        }
        backtrack(ret, word, pos + 1, cur, count + 1);
        backtrack(ret, word, pos + 1, cur + (count>0 ? count : "") + word.charAt(pos), 0);
    }

    @Test
    public void generateAbbreviations3_1(){
        System.out.println(generateAbbreviations3("word"));
    }//[4, 3d, 2r1, 2rd, 1o2, 1o1d, 1or1, 1ord, w3, w2d, w1r1,
    // w1rd, wo2, wo1d, wor1, word]

//////  3. Generalized Abbreviation, 3. Generalized Abbreviation, 3. Generalized Abbreviation, 3. Generalized Abbreviation,

/////// 4. Minimum Unique Word Abbreviation, 4. Minimum Unique Word Abbreviation, 4. Minimum Unique Word Abbreviation

//  411. Minimum Unique Word Abbreviation
//  https://leetcode.com/problems/minimum-unique-word-abbreviation/
//  Backtracking, Bit Manipulation


/*
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 */

    public String minAbbreviation3(String target, String[] dictionary) {
        TrieNode root = new TrieNode();
        // add all words in the dictionary into the Trie
        for (String word : dictionary) {
            if (word.length() == target.length()) {
                insert(root, word);
            }
        }

//         // find the abbr. with shortest length & doesn't conflict with words in the Trie
//         // linear search ver.
//         for (int len = 1; len <= target.length(); len++) {
//             List<String> abbrs = new ArrayList<String>();
//             getAbbrs(0, len, new StringBuilder(), abbrs, target);

//             for (String abbr : abbrs) {
//                 if (!conflict(abbr, root, 0)) return abbr;
//             }
//         }


        // find the abbr. with shortest length & doesn't conflict with words in the Trie
        // binary search ver.         above commented code is linear search ver.
        int min = 1, max = target.length();
        String res = null;
        while (min <= max) {
            boolean conflicted = true;
            int mid = min + (max - min) / 2;
            List<String> abbrs = new ArrayList<String>();
            getAbbrs(0, mid, new StringBuilder(), abbrs, target);
            for (String abbr : abbrs) {
                if (!conflict(abbr, root, 0)) {
                    conflicted = false;
                    res = abbr;
                    break;
                }
            }
            if (conflicted) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return res;
    }

    /**
     * To check if a String "abbr" can be an abbreviation of a word in the Trie starting at TrieNode "node"
     * "abbr" can be a substring of the entire abbr. as we go from root to leave in the trie.
     * This method is called recursively with "abbr" and "node" both changing while we go down the trie.
     *
     * @param abbr - current substring of the entire abbreviation. (must be a latter portion)
     * @param node - current node we are at
     * @param num - current number to be skipped as we go through the trie. If "num" is not 0, we need to go to
     * the next level of the trie while decrementing the "num". If "num" is 0, then we can check if the first
     * char in "abbr" is in the current node.
     */
    boolean conflict(String abbr, TrieNode node, int num) {
        if (num > 0) { // we need to skip this char, and try all possible children TrieNodes
            for (TrieNode next : node.nodes) {
                if (next != null && conflict(abbr, next, num - 1)) return true;
            }
            return false;
        } else { // num == 0, check the start of the "abbr" string
            if (abbr.length() == 0) return node.isEnd;
            int i = 0;
            for (; i < abbr.length() && abbr.charAt(i) >= '0' && abbr.charAt(i) <= '9';i++) {
                num = num * 10 + (abbr.charAt(i) - '0');
            }
            if (i != 0) { // "abbr" starts with a number
                return conflict(abbr.substring(i), node, num);
            } else { // "abbr" starts with a char
                TrieNode next = node.nodes[abbr.charAt(0) - 'a'];
                if (next == null) return false;
                return conflict(abbr.substring(1), next, num);
            }
        }
    }

    /**
     * Insert a word to the tree
     */
    void insert (TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.nodes[c - 'a'] == null)
                curr.nodes[c - 'a'] = new TrieNode();
            curr = curr.nodes[c - 'a'];
            if (i == word.length() - 1)
                curr.isEnd = true;
        }
    }

    /**
     * Get all the abbreviations of length "len" starting from index "s" of String "target"
     * (i.e. targer.substring(s, target.length)), and store all the abbr.s in the List "abbrs"
     *
     * @param len - the length of abbr, defined in the problem
     * @param s - start index
     *            the method gets the abbr of target.substring(s, target.length()).
     *            Notice the previous part of the String, i.e. target.substring(0, s), has been dealt with
     *            in lower depth recursion calls of this method and the abbr. for that part has been stored
     *            in the StringBuilder "sb".
     *
     * The other 3 parameters can just be passed into deeper recursions
     */
    void getAbbrs(int s, int len, StringBuilder sb, List<String> abbrs, String target) {
        if (s >= target.length()) return;  // bad condition. No valid abbr.

        // if the previous position is a number (true), or a char (false)
        boolean prevNum = (sb.length() > 0 && sb.charAt(sb.length() - 1) >= '0' && sb.charAt(sb.length() - 1) <= '9');

        if (len == 1) { // this is the last position for this abbr.
            // if "s" is at the last index of the string, then it's valid to just append this last char.
            // if not, we have to abbreviate the following chars as a single number. (because there's only 1 position available)
            if (s == target.length() - 1) abbrs.add(sb.append(target.charAt(s)).toString());
            else if (!prevNum) { // if prevNum, then we cannot append another number here.
                abbrs.add(sb.append(target.length() - s).toString());
            }
        } else { // len > 1
            int endIdx = sb.length(); // actually is end index + 1. But works because of the "last index exclusive" rule

            // append the char at position "s", and call deeper recursions;
            sb.append(target.charAt(s));
            getAbbrs(s + 1, len - 1, sb, abbrs, target);
            sb.delete(endIdx, sb.length());

            for (int i = s + 1; i < target.length(); i++) {
                if (!prevNum) { // abbr. the substring from s to i into a number
                    sb.append(i - s);
                    getAbbrs(i, len - 1, sb, abbrs, target);
                    sb.delete(endIdx, sb.length());
                }
            }
        }
    }

    class TrieNode {
        TrieNode[] nodes;
        boolean isEnd;

        TrieNode() {
            nodes = new TrieNode[26];
            isEnd = false;
        }
    }
/////// 4. Minimum Unique Word Abbreviation, 4. Minimum Unique Word Abbreviation, 4. Minimum Unique Word Abbreviation

/////// 5. Word Abbreviation, 5. Word Abbreviation, 5. Word Abbreviation, 5. Word Abbreviation, 5. Word Abbreviation

//  527. Word Abbreviation
//  https://leetcode.com/problems/word-abbreviation/description/
//  String, Sort

/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

1. Begin with the first character and then the number of characters abbreviated, which followed by the last character.
2. If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
3. If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
 */


/////// 5. Word Abbreviation, 5. Word Abbreviation, 5. Word Abbreviation, 5. Word Abbreviation, 5. Word Abbreviation




////////////////////////////////////////////////////////////////////////////////////




}
