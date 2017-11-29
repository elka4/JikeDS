package HF.HF0_Intro;
import java.util.ArrayList;
import java.util.List;

//  411. Minimum Unique Word Abbreviation
//  https://leetcode.com/problems/minimum-unique-word-abbreviation/
//  Backtracking, Bit Manipulation
//  3:
public class _411_BackTracking_Minimum_Unique_Word_Abbreviation_M {
//------------------------------------------------------------------------------

    //Trie + Bruteforce
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

//------------------------------------------------------------------------------

/*    Java DFS+Trie+Binary Search 90ms
    Use Trie to build a dictionary with a function to check abbreviation.
    Use DFS with backtracking to generate the abbreviations of a given length.
    Use binary search to find the smallest possible length.*/
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

    public String minAbbreviation2(String target, String[] dictionary) {
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

//------------------------------------------------------------------------------

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
            if (curr.nodes[c - 'a'] == null) curr.nodes[c - 'a'] = new TrieNode();
            curr = curr.nodes[c - 'a'];
            if (i == word.length() - 1) curr.isEnd = true;
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

//----------------------------------------------------------------------------
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