package HF.HF4_Search.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
• Company Tags: Google
• 考点:
• DFS过程中的剪枝 • Hash Trie树的应用
 */


/*
能力维度:
2. 代码基础功力
3. 基础数据结构/算法
4. 逻辑思维/算法优化能力
5. 细节处理(corner case)
 */

//Word Squares
public class _3WordSquares {
    public class Solution {
        class TrieNode {
            List<String> startWith;
            TrieNode[] children;

            TrieNode() {
                startWith = new ArrayList<>();
                children = new TrieNode[26];
            }
        }

        class Trie {
            TrieNode root;

            Trie(String[] words) {
                root = new TrieNode();
                for (String w : words) {
                    TrieNode cur = root;
                    for (char ch : w.toCharArray()) {
                        int idx = ch - 'a';
                        if (cur.children[idx] == null)
                            cur.children[idx] = new TrieNode();
                        cur.children[idx].startWith.add(w);
                        cur = cur.children[idx];
                    }
                }
            }

            List<String> findByPrefix(String prefix) {
                List<String> ans = new ArrayList<>();
                TrieNode cur = root;
                for (char ch : prefix.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null)
                        return ans;

                    cur = cur.children[idx];
                }
                ans.addAll(cur.startWith);
                return ans;
            }
        }

        public List<List<String>> wordSquares(String[] words) {
            List<List<String>> ans = new ArrayList<>();
            if (words == null || words.length == 0)
                return ans;
            int len = words[0].length();
            Trie trie = new Trie(words);
            List<String> ansBuilder = new ArrayList<>();
            for (String w : words) {
                ansBuilder.add(w);
                search(len, trie, ans, ansBuilder);
                ansBuilder.remove(ansBuilder.size() - 1);
            }

            return ans;
        }

        private void search(int len, Trie tr, List<List<String>> ans,
                            List<String> ansBuilder) {
            if (ansBuilder.size() == len) {
                ans.add(new ArrayList<>(ansBuilder));
                return;
            }

            int idx = ansBuilder.size();
            StringBuilder prefixBuilder = new StringBuilder();
            for (String s : ansBuilder)
                prefixBuilder.append(s.charAt(idx));
            List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
            for (String sw : startWith) {
                ansBuilder.add(sw);
                search(len, tr, ans, ansBuilder);
                ansBuilder.remove(ansBuilder.size() - 1);
            }
        }
    }

/////////////////////////////////////////////////////////////

    // version: 高频题班
    public class Solution2 {
        /**
         * @param words a set of words without duplicates
         * @return all word squares
         */
        int wordLen;
        Map<String, List<String>> prefix = new HashMap<>();
        List<String> squares = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();

        void initPrefix(String[] words) {
            for (String item : words) {
                prefix.putIfAbsent("", new ArrayList<>());
                prefix.get("").add(item);

                String pre = "";
                for (char c : item.toCharArray()) {
                    pre += c;
                    prefix.putIfAbsent(pre, new ArrayList<>());
                    prefix.get(pre).add(item);
                }
            }
        }

        boolean checkPrefix(int l, String nextWord) {
            for (int j = l + 1; j < wordLen; j++) {
                String pre = "";
                for (int k = 0; k < l; k++) {
                    pre = pre + squares.get(k).charAt(j);
                }
                pre += nextWord.charAt(j);
                if (!prefix.containsKey(pre)) {
                    return false;
                }
            }
            return true;
        }

        void dfs(int l) {
            if (l == wordLen) {
                ans.add(new ArrayList<>(squares));
                return;
            }
            String pre = "";
            for (int i = 0; i < l; i++)
                pre += squares.get(i).charAt(l);
            List<String> w = prefix.get(pre);

            for (String item : w) {
                if (!checkPrefix(l, item)) {
                    continue;
                }
                squares.add(item);
                dfs(l + 1);
                squares.remove(squares.size() - 1);
            }
        }

        public List<List<String>> wordSquares(String[] words) {
            // Write your code here
            if (words.length == 0) {
                return ans;
            }
            initPrefix(words);
            wordLen = words[0].length();
            dfs(0);
            return ans;
        }
    }
}
/*
Given a set of words without duplicates, find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
 注意事项

There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
您在真实的面试中是否遇到过这个题？ Yes
样例
Given a set ["area","lead","wall","lady","ball"]
return [["wall","area","lead","lady"],["ball","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Given a set ["abat","baba","atan","atal"]
return [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

 */