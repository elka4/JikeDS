package S_2_DS_I.Required_12;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** 473 Add and Search Word


 * Created by tianhuizhu on 6/28/17.
 */
public class _473_Add_and_Search_Word3 {

    //version 3: use HashMap and bfs
    class TrieNode{
        public Map<Character,TrieNode> children;
        public boolean hasWord;
        public TrieNode(){
            children=new HashMap<>();
            hasWord=false;
        }
    }

    public class WordDictionary {
        TrieNode root;
        public WordDictionary(){
            root=new TrieNode();
        }

        // Adds a word into the data structure.
        public void addWord(String word) {
            // Write your code here
            TrieNode cur=root;
            for(int i=0;i<word.length();++i){
                char c=word.charAt(i);
                TrieNode nextNode=cur.children.get(c);
                if(nextNode==null){
                    nextNode=new TrieNode();
                    cur.children.put(c,nextNode);
                }
                cur=nextNode;
            }
            cur.hasWord=true;
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            // Write your code here
            Queue<TrieNode> nexts=new LinkedList<>();
            nexts.add(root);
            int index=0;
            while(!nexts.isEmpty()){
                int size=nexts.size();
                char c=word.charAt(index);
                boolean flag=false;
                for(int i=0;i<size;++i){
                    TrieNode cur=nexts.poll();
                    if(c=='.'){
                        for(TrieNode tempNode:cur.children.values()){
                            nexts.add(tempNode);
                            flag|=tempNode.hasWord;
                        }
                    } else if(cur.children.containsKey(c)){
                        TrieNode nextNode=cur.children.get(c);
                        flag|=nextNode.hasWord;
                        nexts.add(nextNode);
                    }
                }
                index++;
                if(index>=word.length()) return flag;
            }
            return false;
        }
    }

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
}
