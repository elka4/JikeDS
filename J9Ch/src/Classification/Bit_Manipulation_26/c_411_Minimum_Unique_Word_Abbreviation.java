package Classification.Bit_Manipulation_26;
import java.util.*;

public class c_411_Minimum_Unique_Word_Abbreviation {
/*
    Trie + Bruteforce
    Abbreviation number is pretty like wild card and it can match all the characters appearing in the trie.
    There's 3 functions:
    addTrie: add string to the trie
    search: search a string to determine if that's the one in the trie (wild card mode)
    abbrGenerator: generate all the possible abbreviations given certain length (which is num parameter).

    PS: the search function is pretty ugly. hope someone can help it :P
*/

    class Trie{
        Trie[] next = new Trie[26];
        boolean isEnd = false;
    }
    Trie root = new Trie();
    List<String> abbrs;
    public String minAbbreviation(String target, String[] dictionary) {
        for(String s:dictionary) {
            addTrie(s);
        }
        for(int i=0; i<target.length(); i++) {
            abbrs = new ArrayList<>();
            abbrGenerator(target, 0, "", 0, i+1);
            for(String s:abbrs) {
                if(search(s, root, 0, 0)==false) return s;
            }
        }
        return "";
    }
    public void addTrie(String s) {
        Trie cur = root;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(cur.next[c-'a']==null) {
                cur.next[c-'a']=new Trie();
            }
            cur = cur.next[c-'a'];
        }
        cur.isEnd = true;
    }
    public boolean search(String target, Trie root, int i, int loop) {
        if(root==null) return false;

        if(loop!=0) {
            for(int a=0; a<26; a++) {
                if(search(target, root.next[a], i, loop-1)) return true;
            }
            return false;
        }
        if(i==target.length()) {
            if(root.isEnd) return true;
            return false;
        }
        if(Character.isDigit(target.charAt(i))) {
            int tmp = 0;
            while(i<target.length()&&Character.isDigit(target.charAt(i))) {
                tmp = tmp*10 + target.charAt(i)-'0';
                i++;
            }
            return search(target, root, i, tmp);
        } else {
            return search(target, root.next[target.charAt(i)-'a'], i+1, 0);
        }
    }
    public void abbrGenerator(String target, int i, String tmp, int abbr, int num) {
        if(i==target.length()) {
            if(num==0&&abbr==0) abbrs.add(tmp);
            if(num==1&&abbr!=0) abbrs.add(tmp+abbr);
            return;
        }
        if(num<=0) return;
        char cur = target.charAt(i);
        abbrGenerator(target, i+1, abbr==0?tmp+cur:tmp+abbr+cur, 0, abbr==0?num-1:num-2);
        abbrGenerator(target, i+1, tmp, abbr+1, num);
    }

////////////////////////////////////////////////////////////////////////////////
/*Java DFS+Trie+Binary Search 90ms
    Use Trie to build a dictionary with a function to check abbreviation.
    Use DFS with backtracking to generate the abbreviations of a given length.
    Use binary search to find the smallest possible length.*/
    public class Solution {
        class Node{ // Trie Node
            Node[] nodes;
            boolean isWord;
            Node(){
                nodes = new Node[26];
                isWord = false;
            }
            void add(String str){ // add a word to Trie
                if (str.length() == 0) isWord=true; // end of a word
                else {
                    int idx = str.charAt(0)-'a'; // insert a new node
                    if (nodes[idx] == null) nodes[idx] = new Node();
                    nodes[idx].add(str.substring(1));
                }
            }
            boolean isAbbr(String abbr, int num){
                if ( num > 0){ // number of '*'
                    for (Node node : nodes){
                        if (node != null && node.isAbbr(abbr, num-1)) return true;
                    }
                    return false; // not exist in the dictionary
                } else {
                    if (abbr.length()==0) return isWord; // at the end of the addr
                    int idx=0; // get the number of '*' at the start of the abbr
                    while (idx < abbr.length() && abbr.charAt(idx) >='0' && abbr.charAt(idx) <='9' ) {
                        num = (num*10) + (abbr.charAt(idx++)-'0');
                    }
                    if (num>0) return isAbbr(abbr.substring(idx),num); // start with number
                    else { // start with non-number
                        if (nodes[abbr.charAt(0)-'a'] != null )
                            return nodes[abbr.charAt(0)-'a'].isAbbr(abbr.substring(1), 0);
                        else return false; // not exist in the dictionary
                    }
                }
            }
        }

        void getAbbs(char[] cc, int s, int len, StringBuilder sb, List<String> abbs){ //DFS with backtracking
            boolean preNum = (sb.length() > 0 ) && (sb.charAt(sb.length()-1) >= '0') && (sb.charAt(sb.length()-1) <= '9');
            if (len == 1)  {
                if ( s  < cc.length) {
                    if (s==cc.length-1) abbs.add(sb.toString() + cc[s]); // add one char
                    if (! preNum ) abbs.add(sb.toString() + (cc.length-s) ); // add a number
                }
            } else if (len > 1 ) {
                int last = sb.length();
                for (int i=s+1; i < cc.length; i++ ){
                    if (! preNum) { // add a number
                        sb.append(i-s);
                        getAbbs(cc, i, len-1, sb, abbs);
                        sb.delete(last, sb.length());
                    }
                    if (i==s+1) { // add one char
                        sb.append(cc[s]);
                        getAbbs(cc, i, len-1, sb, abbs);
                        sb.delete(last, sb.length());
                    }
                }
            }
        }

        public String minAbbreviation(String target, String[] dictionary) {
            List<String> dict = new ArrayList();
            int len = target.length();
            for (String str : dictionary) if (str.length() == len ) dict.add(str);
            if (dict.isEmpty()) return ""+len;
            Node root = new Node();
            for (String str : dict) root.add(str);
            char[] cc = target.toCharArray();
            String ret = null;

            int min = 1, max = len;
            while (max >= min) {
                int mid = min+( (max-min)/2 );
                List<String> abbs = new ArrayList();
                getAbbs(cc, 0, mid, new StringBuilder(), abbs);
                boolean conflict = true;
                for (String abbr: abbs){
                    if ( ! root.isAbbr(abbr,0) ) {
                        conflict = false;
                        ret = abbr;
                        break;
                    }
                }
                if (conflict) {
                    min = mid+1;
                } else {
                    max = mid-1;
                }
            }
            return ret;
        }
    }

////////////////////////////////////////////////////////////////////////////////
//https://discuss.leetcode.com/topic/61799/java-bit-mask-dfs-with-pruning

    private int minLen;
    private int result;

    public String minAbbreviation3(String target, String[] dictionary) {
        // only keep words whose length == target in new dict, then compute their bit masks
        Set<Integer> maskSet = new HashSet<>();
        for(String s: dictionary){
            if(target.length() == s.length()){
                maskSet.add(getBitMask(s,target));
            }
        }

        // dfs with pruning
        minLen = target.length()+1;
        result = -1;

        dfs(target,maskSet,0,0,0);

        if(minLen > target.length()){
            return "";
        }

        // convert result to word
        int zeroCnt = 0;
        String res = "";
        for (int i = target.length()-1; i>=0; i--) {
            //遇到0要累加连续零个数,遇到1填原char
            int digit = (result & 1);
            if(digit == 0){
                ++zeroCnt;
            } else {
                if(zeroCnt > 0){
                    res = zeroCnt + res;
                    zeroCnt =0;
                }
                res = target.charAt(i) + res;
            }
            result >>= 1;
        }
        if(zeroCnt > 0) res = zeroCnt + res;
        return res;
    }

    /**
     *
     * @param target
     * @param maskSet masks of words in dict
     * @param start idx at target
     * @param curLen current abbr's length
     */
    private void dfs(String target,Set<Integer> maskSet,int start,int curLen,int curResult){
        // pruning, no need to continue, already not min length
        if(curLen >= minLen) return;

        if(start == target.length()){
            // check whether curResult mask conflicts with words in dict
            for(int mask:maskSet){
                /**
                 * 单词manipulation的缩写m2ip6n可以转化为100110000001
                 *  m a n i p u l a t i o n
                 m  2  i p      6      n
                 1 0 0 1 1 0 0 0 0 0 0 1
                 * 0代表随意不care,如果这个mask和dict中某个mask的所有1重合代表在意的位置完全相同,
                 * 说明这个mask和dict中那个词冲突
                 * 我们要找的是不冲突的mask
                 */
                if((curResult & mask) == curResult){
                    return; // conflict
                }
            }
            // no conflict happens, can use
            if(minLen > curLen){
                minLen = curLen;
                result = curResult;
            }
            return;
        }

        // case 1: replace chars from start in target with number
        for (int i = start; i < target.length(); i++) {
            //被replace掉的char位置由0代替所以是curResult<<(i+1-start),没replace掉的这里不管,我们只管到i,之后的由backtrack内决定
            //注意:不允许word => w11d这种用数字代替但含义不同
            if(curLen == 0 || (curResult &1) == 1){
                //后者即上一次是保留了字母
                dfs(target,maskSet,i+1,curLen+1,curResult<<(i+1-start));
            }
        }

        // case 2: no replace from start (curResult << 1)+1代表新的这位保留了char,所以是加一
        dfs(target,maskSet,start+1,curLen+1,(curResult << 1)+1);
    }

    // 比如apple 和 amper 字母相同设1,不同设0,所以得到10100
    private int getBitMask(String s1,String s2){
        int mask = 0;
        for (int i = 0; i < s1.length(); i++) {
            mask <<= 1;
            if(s1.charAt(i) == s2.charAt(i)){
                mask += 1;
            }
        }
        return mask;
    }

////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////


}
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