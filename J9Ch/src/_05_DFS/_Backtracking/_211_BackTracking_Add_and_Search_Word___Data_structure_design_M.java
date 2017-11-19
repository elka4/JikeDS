package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
//211. Add and Search Word - Data structure design

public class _211_BackTracking_Add_and_Search_Word___Data_structure_design_M {
//My JAVA Trie based solution


    public class WordDictionary {
        WordNode root = new WordNode();
        public void addWord(String word) {
            char chars[] = word.toCharArray();
            addWord(chars, 0, root);
        }

        private void addWord(char[] chars, int index, WordNode parent) {
            char c = chars[index];
            int idx = c-'a';
            WordNode node = parent.children[idx];
            if (node == null){
                node = new WordNode();
                parent.children[idx]=node;
            }
            if (chars.length == index+1){
                node.isLeaf=true;
                return;
            }
            addWord(chars, ++index, node);
        }


        public boolean search(String word) {
            return search(word.toCharArray(), 0, root);
        }

        private boolean search(char[] chars, int index, WordNode parent){
            if (index == chars.length){
                if (parent.isLeaf){
                    return true;
                }
                return false;
            }
            WordNode[] childNodes = parent.children;
            char c = chars[index];
            if (c == '.'){
                for (int i=0;i<childNodes.length;i++){
                    WordNode n = childNodes[i];
                    if (n !=null){
                        boolean b = search(chars, index+1, n);
                        if (b){
                            return true;
                        }
                    }
                }
                return false;
            }
            WordNode node = childNodes[c-'a'];
            if (node == null){
                return false;
            }
            return search(chars, ++index, node);
        }



        private class WordNode{
            boolean isLeaf;
            WordNode[] children = new WordNode[26];
        }
    }
//-------------------------------------------------------------------------/////////////////

//    My simple and clean Java code
//    Using backtrack to check each character of word to search.

    public class WordDictionary2 {
        public class TrieNode {
            public TrieNode[] children = new TrieNode[26];
            public String item = "";
        }

        private TrieNode root = new TrieNode();

        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.item = word;
        }

        public boolean search(String word) {
            return match(word.toCharArray(), 0, root);
        }

        private boolean match(char[] chs, int k, TrieNode node) {
            if (k == chs.length) return !node.item.equals("");
            if (chs[k] != '.') {
                return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
            } else {
                for (int i = 0; i < node.children.length; i++) {
                    if (node.children[i] != null) {
                        if (match(chs, k + 1, node.children[i])) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

//-------------------------------------------------------------------------/////////////////
    //Java Solution, easy understand
    public class WordDictionary3 {

        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        // Adds a word into the data structure.
        public void addWord(String word) {
            int index = word.length();
            if(!map.containsKey(index)){
                List<String> list = new ArrayList<String>();
                list.add(word);
                map.put(index, list);
            }else{
                map.get(index).add(word);
            }

        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            int index = word.length();
            if(!map.containsKey(index)){
                return false;
            }
            List<String> list = map.get(index);
            if(isWords(word)){
                return list.contains(word);
            }
            for(String s : list){
                if(isSame(s, word)){
                    return true;
                }
            }
            return false;
        }

        boolean isWords(String s){
            for(int i = 0; i < s.length(); i++){
                if(!Character.isLetter(s.charAt(i))){
                    return false;
                }
            }
            return true;
        }

        boolean isSame(String a, String search){
            if(a.length() != search.length()){
                return false;
            }
            for(int i = 0; i < a.length(); i++){
                if(search.charAt(i) != '.' && search.charAt(i) != a.charAt(i)){
                    return false;
                }
            }
            return true;
        }
    }
//-------------------------------------------------------------------------/////////////////



}
/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */