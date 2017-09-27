package S_2_DS_I.Required_12;

import java.util.HashMap;
import java.util.Map;

/** 473 Add and Search Word


 * Created by tianhuizhu on 6/28/17.
 */
public class _473_Add_and_Search_Word2 {


    // Version 2 use HashMap and dfs
    class TrieNode {
        // Initialize your data structure here.
        public HashMap<Character, TrieNode> children;
        public boolean hasWord;

        // Initialize your data structure here.
        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            hasWord = false;
        }
    }


    public class WordDictionary {
        private TrieNode root;

        public WordDictionary(){
            root = new TrieNode();
        }

        // Adds a word into the data structure.
        public void addWord(String word) {
            // Write your code here
            TrieNode now = root;
            for(int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (!now.children.containsKey(c)) {
                    now.children.put(c, new TrieNode());
                }
                now = now.children.get(c);
            }
            now.hasWord = true;
        }

        boolean find(String word, int index, TrieNode now) {
            if(index == word.length()){
                if(now.children.size()==0)
                    return true;
                else
                    return false;
            }

            Character c = word.charAt(index);
            if (now.children.containsKey(c)) {
                if(index == word.length()-1 && now.children.get(c).hasWord){
                    return true;
                }
                return find(word, index+1, now.children.get(c)) ;
            }else if(c == '.'){
                boolean result = false;
                for(Map.Entry<Character, TrieNode> child: now.children.entrySet()){
                    if(index == word.length()-1 && child.getValue().hasWord){
                        return true;
                    }

                    //if any path is true, set result to be true;
                    if(find(word, index+1, child.getValue()) ){
                        result = true;
                    }
                }

                return result;
            }else{
                return false;
            }
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            // Write your code here
            return find(word, 0, root);
        }
    }

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

}
