package _String._Abbreviation;
import java.util.*;
import org.junit.Test;


//  527. Word Abbreviation
//  https://leetcode.com/problems/word-abbreviation/description/
//  String, Sort
//  Valid Word Abbreviation/
//  Minimum Unique Word Abbreviation
//
//
//
public class _527_Word_Abbreviation_H {
//-------------------------------------------------------------------------
    //2
    // version: 高频题班
    public String[] wordsAbbreviation2(String[] dict) {
        int len = dict.length;
        String[] result = new String[len];
        int[] prefix = new int[len];
        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < len; i++) {
            prefix[i] = 1;
            result[i] = getAbbr(dict[i], 1);
            count.put(result[i], count.getOrDefault(result[i], 0) + 1);
        }

        while (true) {
            boolean unique = true;
            for (int i = 0; i < len; i++) {
                if (count.get(result[i]) > 1) {
                    prefix[i]++;
                    result[i] = getAbbr(dict[i], prefix[i]);
                    count.put(result[i], count.getOrDefault(result[i], 0) + 1);
                    unique = false;
                }
            }
            if (unique) {
                break;
            }
        }
        return result;
    }

    String getAbbr(String s, int p) {
        if (p >= s.length() - 2) {
            return s;
        }
        String ans;
        ans = s.substring(0, p) + (s.length() - 1 - p) + s.charAt(s.length() - 1);
        return ans;
    }

    @Test
    public void test2(){
        String[] strs = {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"};
        String[] result = wordsAbbreviation2(strs);
        for (String s:result) {
            System.out.print(s + ", ");
        }
    }//l2e, god, internal, me, i6t, interval, inte4n, f2e, intr4n

    @Test
    public void test22(){
        System.out.println(getAbbr("internal", 1));//i6l
        System.out.println(getAbbr("internal", 2));//in5l
        System.out.println(getAbbr("internal", 3));//int4l
        System.out.println(getAbbr("internal", 4));//inte3l
    }
//-------------------------------------------------------------------------
    //1
 //Really simple and straightforward Java solution
    //Make abbreviation for each word.
    //Then, check each word, if there are some strings which have same abbreviation with it, increase the prefix.

    public List<String> wordsAbbreviation(List<String> dict) {
        int len=dict.size();
        String[] ans=new String[len];
        int[] prefix=new int[len];
        for (int i=0;i<len;i++) {
            prefix[i]=1;
            ans[i]=makeAbbr(dict.get(i), 1); // make abbreviation for each string
        }
        for (int i=0;i<len;i++) {
            while (true) {
                HashSet<Integer> set=new HashSet<>();
                for (int j=i+1;j<len;j++) {
                    if (ans[j].equals(ans[i])) set.add(j); // check all strings with the same abbreviation
                }
                if (set.isEmpty()) break;
                set.add(i);
                for (int k: set)
                    ans[k]=makeAbbr(dict.get(k), ++prefix[k]); // increase the prefix
            }
        }
        return Arrays.asList(ans);
    }

    private String makeAbbr(String s, int k) {
        if (k>=s.length()-2) return s;
        StringBuilder builder=new StringBuilder();
        builder.append(s.substring(0, k));
        builder.append(s.length()-1-k);
        builder.append(s.charAt(s.length()-1));
        return builder.toString();
    }
//----------------------------------------------------------------------------
    //https://www.youtube.com/watch?v=yAQMcGY4c90
//----------------------------------------------------------------------------
    //2
    //Verbose Java Solution, HashMap(s)
    public class Solution2 {
        public List<String> wordsAbbreviation(List<String> dict) {
            Map<String, String> wordToAbbr = new HashMap<>();
            Map<Integer, List<String>> groups = new HashMap<>();

            // Try to group words by their length. Because no point to compare words with different length.
            // Also no point to look at words with length < 4.
            for (String word : dict) {
                int len = word.length();
                if (len < 4) {
                    wordToAbbr.put(word, word);
                }
                else {
                    List<String> g = groups.getOrDefault(len, new ArrayList<String>());
                    g.add(word);
                    groups.put(len, g);
                }
            }

            // For each group of words with same length, generate a result HashMap.
            for (int len : groups.keySet()) {
                Map<String, String> res = getAbbr(groups.get(len));
                for (String word : res.keySet()) {
                    wordToAbbr.put(word, res.get(word));
                }
            }

            // Generate the result list
            List<String> result = new ArrayList<>();
            for (String word : dict) {
                result.add(wordToAbbr.get(word));
            }

            return result;
        }

        private Map<String, String> getAbbr(List<String> words) {
            Map<String, String> res = new HashMap<>();
            int len = words.get(0).length();

            // Try to abbreviate a word from index 1 to len - 2
            for (int i = 1; i < len - 2; i++) {
                Map<String, String> abbrToWord = new HashMap<>();
                for (String s : words) {
                    if (res.containsKey(s)) continue;
                    // Generate the current abbreviation
                    String abbr = s.substring(0, i) + (len - 1 - i) + s.charAt(len - 1);
                    // Tick: use reversed abbreviation to word map to check if there is any duplicated abbreviation
                    if (!abbrToWord.containsKey(abbr)) {
                        abbrToWord.put(abbr, s);
                    }
                    else {
                        abbrToWord.put(abbr, "");
                    }
                }

                // Add unique abbreviations find during this round to result HashMap
                for (String abbr : abbrToWord.keySet()) {
                    String s = abbrToWord.get(abbr);
                    // Not a unique abbreviation
                    if (s.length() == 0) continue;
                    res.put(s, abbr);
                }
            }

            // Add all words that can't be shortened.
            for (String s : words) {
                if (!res.containsKey(s)) {
                    res.put(s, s);
                }
            }

            return res;
        }
    }
//----------------------------------------------------------------------------
    //3
//HashMap + Trie => O(nL) solution
//The basic idea is to group all conflicted words, and then resolve the conflicts using Trie. The time complexity will be O(nL) for building trie, O(nL) to resolve conflicts, O(n) to group words. So the time complexity will be O(n(2L + 1). n is the number of words, and L is the average length of each words.
//
//I added the comments in code, so you can directly see the code. Please correct me if I make some mistakes and welcome to make my code concise.

    public class Solution3 {

        public List<String> wordsAbbreviation(List<String> dict) {
            Map<String, List<Integer>> abbrMap = new HashMap<>();
            // 1) create result set
            List<String> res = new ArrayList<>(Collections.nCopies(dict.size(), null));
            // 2) Group all words with the same shortest abbreviation. For example:
            // "internal", "interval" => grouped by "i6l"
            // "intension", "intrusion" => grouped by "i7n"
            // "god" => grouped by "god"
            // we can notice that only words with the same length and the same start
            // and end letter could be grouped together
            for (int i = 0; i < dict.size(); i ++) {
                String word = dict.get(i);
                String st = getShortestAbbr(word);
                List<Integer> pos = abbrMap.get(st);
                if (pos == null) {
                    pos = new ArrayList<>();
                    abbrMap.put(st, pos);
                }
                pos.add(i);
            }
            // 3) Resolve conflicts in each group
            for (Map.Entry<String, List<Integer>> entry : abbrMap.entrySet()) {
                String abbr = entry.getKey();
                List<Integer> pos = entry.getValue();
                resolve(dict, res, abbr, pos);
            }
            return res;
        }

        /**
         * To resolve conflicts in a group, we could build a trie for all the words
         * in the group. The trie node will contain the count of words that has the
         * same prefix. Then we could use this trie to determine when we could resolve
         * a conflict by identifying that the count of words in that trie node will only
         * have one word has the prefix.
         */
        private void resolve(List<String> dict, List<String> res, String abbr, List<Integer> pos) {
            if (pos.size() == 1) {
                res.set(pos.get(0), abbr);
            } else {
                Trie trie = buildTrie(dict, pos);
                for (int p : pos) {
                    String w = dict.get(p);
                    Trie cur = trie;
                    int i = 0;
                    int n = w.length();
                    // while loop to find the trie node which only has the 1 word which has
                    // the prefix. That means in that position, only current word has that
                    // specific character.
                    while (i < n && cur.next.get(w.charAt(i)).cnt > 1) {
                        cur = cur.next.get(w.charAt(i));
                        i ++;
                    }
                    if (i >= n - 3) {
                        res.set(p, w);
                    } else {
                        String pre = w.substring(0, i+1);
                        String st = pre + (n - i - 2) + "" + w.charAt(n - 1);
                        res.set(p, st);
                    }
                }
            }
        }

        /**
         * Get the shortest abbreviation for a word
         */
        private String getShortestAbbr(String s) {
            if (s.length() <= 3) {
                return s;
            } else {
                return s.charAt(0) + "" + (s.length() - 2) + "" + s.charAt(s.length() - 1);
            }
        }

        /**
         * Standard way to build the trie, but we record each trie node with the information
         * of the count of words with the same prefix.
         */
        private Trie buildTrie(List<String> dict, List<Integer> pos) {
            Trie root = new Trie();
            for (int p : pos) {
                String w = dict.get(p);
                Trie cur = root;
                for (int i = 0; i < w.length(); i ++) {
                    char c = w.charAt(i);
                    if (cur.next.containsKey(c)) {
                        cur = cur.next.get(c);
                    } else {
                        Trie next = new Trie();
                        cur.next.put(c, next);
                        cur = next;
                    }
                    cur.cnt ++;
                }
            }
            return root;
        }

        private class Trie {
            int cnt = 0;
            Map<Character, Trie> next = new HashMap<>();
        }
    }
//----------------------------------------------------------------------------
    //4
    /*Java DFS+Trie+Binary Search 90ms

    Use Trie to build a dictionary with a function to check abbreviation.
    Use DFS with backtracking to generate the abbreviations of a given length.
    Use binary search to find the smallest possible length.*/
    public class Solution4 {
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

//----------------------------------------------------------------------------
}
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
