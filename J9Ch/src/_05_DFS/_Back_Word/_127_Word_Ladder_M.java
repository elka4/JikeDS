package _05_DFS._Back_Word;
import org.junit.Test;

import java.util.*;

//  127. Word Ladder
//  https://leetcode.com/problems/word-ladder/description/
//  http://www.lintcode.com/zh-cn/problem/word-ladder/
//  BFS
public class _127_Word_Ladder_M {

//-------------------------------------------------------------------------///////
/*    Two-end BFS in Java 31ms.

100
    M Moriarty
    Reputation:  118
    Modified from Share my two-end BFS in C++ 80ms.*/

    public class Solution1 {

        public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
            Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

            int len = 1;
            int strLen = beginWord.length();
            HashSet<String> visited = new HashSet<String>();

            beginSet.add(beginWord);
            endSet.add(endWord);
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                if (beginSet.size() > endSet.size()) {
                    Set<String> set = beginSet;
                    beginSet = endSet;
                    endSet = set;
                }

                Set<String> temp = new HashSet<String>();
                for (String word : beginSet) {
                    char[] chs = word.toCharArray();

                    for (int i = 0; i < chs.length; i++) {
                        for (char c = 'a'; c <= 'z'; c++) {
                            char old = chs[i];
                            chs[i] = c;
                            String target = String.valueOf(chs);

                            if (endSet.contains(target)) {
                                return len + 1;
                            }

                            if (!visited.contains(target) && wordList.contains(target)) {
                                temp.add(target);
                                visited.add(target);
                            }
                            chs[i] = old;
                        }
                    }
                }

                beginSet = temp;
                len++;
            }

            return 0;
        }
    }
//-------------------------------------------------------------------------///////
    //2
/*Java Solution using Dijkstra's algorithm, with explanation

            55
    J Joshua924
    Reputation:  293*/
    public int ladderLength2(String beginWord, String endWord, Set<String> wordDict) {
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);
        wordDict.add(endWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<String>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
        }
        return distance;
    }
  /*  Basically I keep two sets of words, one set reached that represents the borders that have been reached with "distance" steps; another set wordDict that has not been reached. In the while loop, for each word in the reached set, I give all variations and check if it matches anything from wordDict, if it has a match, I add that word into toAdd set, which will be my "reached" set in the next loop, and remove the word from wordDict because I already reached it in this step. And at the end of while loop, I check the size of toAdd, which means that if I can't reach any new String from wordDict, I won't be able to reach the endWord, then just return 0. Finally if the endWord is in reached set, I return the current steps "distance".

    The idea is that reached always contain only the ones we just reached in the last step, and wordDict always contain the ones that haven't been reached. This is pretty much what Dijkstra's algorithm does, or you can see this as some variation of BFS.

            ps: I get TLE at the first two submissions, because when I check if wordDict has any matches with reached set, I use two for loops and determine if any pair of words differ by one. That's a huge slow-down because it'll takes m (size of reached) * n (size of wordDict) * l (length of words) time, while in this solution, it takes 26 * l * m time. So when n is huge, this solution will be (n/26) times faster.*/



//    I think we can use a queue to replace the reached set, by which we can avoid duplicate check?

    public class Solution22 {
        public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            wordList.add(endWord);
            wordList.remove(beginWord);
            int level = 1;
            while(!queue.isEmpty()){
                int size = queue.size();
                for(int i=0;i<size;i++){
                    String str = queue.poll();
                    if(str.equals(endWord))return level;
                    for(String neighbor : neighbors(str,wordList)){
                        queue.offer(neighbor);
                    }
                }
                level++;
            }
            return 0;
        }

        public List<String> neighbors(String s, Set<String> wordList){
            List<String> res = new LinkedList<>();
            for(int i=0;i<s.length();i++){
                char [] chars = s.toCharArray();
                for(char ch = 'a'; ch <= 'z'; ch++){
                    chars[i] = ch;
                    String word = new String(chars);
                    if(wordList.remove(word)){
                        res.add(word);
                    }
                }
            }
            return res;
        }
    }
//-------------------------------------------------------------------------///////
    //3
/*Another accepted Java solution (BFS)

50
    jeantimex
    Reputation:  4,433*/
    public int ladderLength3(String start, String end, Set<String> dict) {
        // Use queue to help BFS
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        queue.add(null);

        // Mark visited word
        Set<String> visited = new HashSet<String>();
        visited.add(start);

        int level = 1;

        while (!queue.isEmpty()) {
            String str = queue.poll();

            if (str != null) {
                // Modify str's each character (so word distance is 1)
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();

                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;

                        String word = new String(chars);

                        // Found the end word
                        if (word.equals(end)) return level + 1;

                        // Put it to the queue
                        if (dict.contains(word) && !visited.contains(word)) {
                            queue.add(word);
                            visited.add(word);
                        }
                    }
                }
            } else {
                level++;

                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }

        return 0;
    }



//    Adding and polling null is not necessary, if you can check the queue size in BFS. I modified the codes a bit and it works as well:

    public int ladderLength33(String b, String e, Set<String> dict) {
        if(b.equals(e)) return 1;

        Queue<String> q = new LinkedList<String>();
        q.add(b);
        dict.remove(b);

        int level=2;

        while(!q.isEmpty()) {
            int sz = q.size();

            for(int i=0; i<sz; i++) {
                String tmp = q.poll();

                for(int j=0; j<tmp.length(); j++) {
                    char[] chars = tmp.toCharArray();

                    for(char c='a'; c<='z'; c++) {
                        chars[j] = c;
                        String tmp2 = new String(chars);

                        if(tmp2.equals(e)) return level;

                        if(dict.remove(tmp2)) {
                            q.add(tmp2);
                        }
                    }
                }
            }

            level++;
        }

        return 0;
    }
//-------------------------------------------------------------------------///////
//4
/*    Simple Java BFS solution with explanation

34
    G gschengcong
    Reputation:  137
    The first intuition for this problem is to build a graph whose nodes represent strings and edges connect strings that are only 1 character apart, and then we apply BFS from the startWord node. If we find the endWord, we return the level count of the bfs. This intuition is correct, but there are some places that we can save time.

    When we build adjacency list graph, we don't use two loops to check every pair of string to see if they are 1 character apart. Instead, we make changes to current string to obtain all the strings we can reach from current node, and see if it is in the wordList. Thus, there are currentString.length() * 25 case we need to check for every node. This is faster when the wordList set is large, since the check-every-pair method need wordList.size() * currentString.length() for each node. Otherwise, your may exceed the running time limit.

    For the strings we visited, we remove it from the wordList. This way we don't need to mark visited using another HashSet or something.

    Actually, we don't even need to build the adjacency list graph explicitly using a HashMap<String, ArrayList<String>>, since we keep all the nodes we can reach in the queue of each level of BFS. This can be seen as the keys of the HashMap are the strings that in the queue, and values are the strings that satisfy the 1 character apart in the wordList. Thus, we avoid the time cost of build map for those nodes we don't need to visit.*/
    public class Solution4 {

        public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
            wordList.add(endWord);
            Queue<String> queue = new LinkedList<String>();
            queue.add(beginWord);
            int level = 0;
            while(!queue.isEmpty()){
                int size = queue.size();
                for(int i = 0; i < size; i++){
                    String cur = queue.remove();
                    if(cur.equals(endWord)){ return level + 1;}
                    for(int j = 0; j < cur.length(); j++){
                        char[] word = cur.toCharArray();
                        for(char ch = 'a'; ch < 'z'; ch++){
                            word[j] = ch;
                            String check = new String(word);
                            if(!check.equals(cur) && wordList.contains(check)){
                                queue.add(check);
                                wordList.remove(check);
                            }
                        }
                    }
                }
                level++;
            }
            return 0;
        }
    }



//    With recent update from set to list, you probably need to make a few optimizations to pass test cases. (see code line 1 - 2).

    public int ladderLength44(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        HashSet<String> set = new HashSet<String>(wordList);
        Queue<String> q = new LinkedList<String>();
        int length = 0;
        set.add(endWord);
        q.add(beginWord);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                if (w.equals(endWord)) return length + 1;
                wordMatch(w, set, q);
            }
            length++;
        }
        return 0;
    }

    public void wordMatch(String w, Set<String> set, Queue<String> q) {
        for (int i = 0; i < w.length(); i++) {
            char[] word = w.toCharArray();
            for (int j = 0; j < 26; j++) {
                char c = (char) ('a' + j);
                if (word[i] == c) continue;
                word[i] = c;
                String s = String.valueOf(word);
                if (set.contains(s)) {
                    set.remove(s);
                    q.offer(s);
                }
            }
        }
    }

//-------------------------------------------------------------------------///////
    //5
/*Super fast Java solution using two-end BFS

19
    jeantimex
    Reputation:  4,433
    Thanks to prime_tang!*/

    public class Solution5 {
        public int ladderLength(String start, String end, Set<String> dict) {
            Set<String> set1 = new HashSet<String>();
            Set<String> set2 = new HashSet<String>();

            set1.add(start);
            set2.add(end);

            return helper(dict, set1, set2, 1);
        }

        int helper(Set<String> dict, Set<String> set1, Set<String> set2, int level) {
            if (set1.isEmpty()) return 0;

            if (set1.size() > set2.size()) return helper(dict, set2, set1, level);

            // remove words from both ends
            for (String word : set1) { dict.remove(word); };
            for (String word : set2) { dict.remove(word); };

            // the set for next level
            Set<String> set = new HashSet<String>();

            // for each string in the current level
            for (String str : set1) {
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();

                    // change letter at every position
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);

                        // found the word in other end(set)
                        if (set2.contains(word)) {
                            return level + 1;
                        }

                        // if not, add to the next level
                        if (dict.contains(word)) {
                            set.add(word);
                        }
                    }
                }
            }

            return helper(dict, set2, set, level + 1);
        }
    }
//-------------------------------------------------------------------------///////
//6

/*
    Concise JAVA solution based on BFS

4
    ChengZhang
    Reputation:  1,240
    Explanation

    The basic idea is to build a graph based on characters of word, and then set 'a' to 'z' as neighbors of each character in the word. Replace one character with 'a' to 'z' one time, using level by level BFS to find the shortest transformation sequence path. As the following:
*/

    public int ladderLength6(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();// Visited words

        int length = 1;
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int count = queue.size();// Level by level BFS

            for (int k = 0; k < count; k++) {
                char[] chs = queue.poll().toCharArray();

                for (int i = 0 ; i < chs.length; i++) {// Outer loop should be string
                    for (char ch = 'a'; ch <= 'z'; ch++) {// Inner loop should be 'a' - 'z'
                        char chOld = chs[i];
                        if (chs[i] == ch)
                            continue;

                        chs[i] = ch;
                        String cur = String.valueOf(chs);
                        if (endWord.equals(cur))
                            return length + 1;

                        if (!visited.contains(cur) && wordList.contains(cur)) {
                            queue.offer(cur);
                            visited.add(cur);
                        }
                        chs[i] = chOld;
                    }
                }
            }
            length++; // Next round of longer transformation sequence
        }
        return 0;
    }



//-------------------------------------------------------------------------///////
    // 9Ch
    // version: LeetCode
    public int ladderLength1(String start, String end, List<String> wordList) {
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

/*
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
*/

    @Test
    public void test01(){
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        dict.add("cog");
        List<String> wordlist = new ArrayList<>();
        wordlist.addAll(dict);
        System.out.println(ladderLength1(start, end, wordlist));
        //[[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
    }//5

//-------------------------------------------------------------------------///////
    // 9Ch
    // version: LintCode ( Set<String> )
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

    @Test
    public void test02(){
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        dict.add("cog");
        List<String> wordlist = new ArrayList<>();
        wordlist.addAll(dict);
        System.out.println(ladderLength(start, end, dict));
        //[[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
    }//5

//-------------------------------------------------------------------------///////
}
/*
lint   BFS

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


