package _05_DFS._Backtracking;
import java.util.*;

//  BFS
public class Word_Ladder {




//////////////////////////////////////////////////////////////////////////////////////////

    // version: LintCode ( Set<String> )
    public class Jiuzhang1 {
        public int ladderLength(String start, String end, Set<String> dict) {
            if (dict == null) {
                return 0;
            }

            if (start.equals(end)) {
                return 1;
            }

            dict.add(start);
            dict.add(end);

            HashSet<String> hash = new HashSet<String>();
            Queue<String> queue = new LinkedList<String>();
            queue.offer(start);
            hash.add(start);

            int length = 1;
            while(!queue.isEmpty()) {
                length++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String word = queue.poll();
                    for (String nextWord: getNextWords(word, dict)) {
                        if (hash.contains(nextWord)) {
                            continue;
                        }
                        if (nextWord.equals(end)) {
                            return length;
                        }

                        hash.add(nextWord);
                        queue.offer(nextWord);
                    }
                }
            }
            return 0;
        }

        // replace character of a string at given index to a given character
        // return a new string
        private String replace(String s, int index, char c) {
            char[] chars = s.toCharArray();
            chars[index] = c;
            return new String(chars);
        }

        // get connections with given word.
        // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
        // it will return ['hit', 'hog']
        private ArrayList<String> getNextWords(String word, Set<String> dict) {
            ArrayList<String> nextWords = new ArrayList<String>();
            for (char c = 'a'; c <= 'z'; c++) {
                for (int i = 0; i < word.length(); i++) {
                    if (c == word.charAt(i)) {
                        continue;
                    }
                    String nextWord = replace(word, i, c);
                    if (dict.contains(nextWord)) {
                        nextWords.add(nextWord);
                    }
                }
            }
            return nextWords;
        }
    }

    // version: LeetCode
    public class Jiuzhang2 {
        public int ladderLength(String start, String end, List<String> wordList) {
            Set<String> dict = new HashSet<>();
            for (String word : wordList) {
                dict.add(word);
            }

            if (start.equals(end)) {
                return 1;
            }

            HashSet<String> hash = new HashSet<String>();
            Queue<String> queue = new LinkedList<String>();
            queue.offer(start);
            hash.add(start);

            int length = 1;
            while (!queue.isEmpty()) {
                length++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String word = queue.poll();
                    for (String nextWord: getNextWords(word, dict)) {
                        if (hash.contains(nextWord)) {
                            continue;
                        }
                        if (nextWord.equals(end)) {
                            return length;
                        }

                        hash.add(nextWord);
                        queue.offer(nextWord);
                    }
                }
            }

            return 0;
        }

        // replace character of a string at given index to a given character
        // return a new string
        private String replace(String s, int index, char c) {
            char[] chars = s.toCharArray();
            chars[index] = c;
            return new String(chars);
        }

        // get connections with given word.
        // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
        // it will return ['hit', 'hog']
        private ArrayList<String> getNextWords(String word, Set<String> dict) {
            ArrayList<String> nextWords = new ArrayList<String>();
            for (char c = 'a'; c <= 'z'; c++) {
                for (int i = 0; i < word.length(); i++) {
                    if (c == word.charAt(i)) {
                        continue;
                    }
                    String nextWord = replace(word, i, c);
                    if (dict.contains(nextWord)) {
                        nextWords.add(nextWord);
                    }
                }
            }
            return nextWords;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////



}

/*
 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]

As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

UPDATE (2017/1/20):
The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */
/*
lint

给出两个单词（start和end）和一个字典，找到从start到end的最短转换序列

比如：

    每次只能改变一个字母。
    变换过程中的中间单词必须在字典中出现。

注意事项

    如果没有转换序列则返回0。
    所有单词具有相同的长度。
    所有单词都只包含小写字母。

您在真实的面试中是否遇到过这个题？
样例

给出数据如下：

start = "hit"

end = "cog"

dict = ["hot","dot","dog","lot","log"]

一个最短的变换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog"，

返回它的长度 5

 */