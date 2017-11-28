package _String._WordLadder;

import org.junit.Test;

import java.util.*;

/**120 Word Ladder
 * Created by tianhuizhu on 6/28/17.
 */
public class _120_Word_Ladder_0 {

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

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);

        int length = 1;

        while(!queue.isEmpty()) {
            //只要queue还不空，就需要再走至少一步，length就++
            length++;
            System.out.println("length " + length);

            int size = queue.size();
            System.out.println("queue.size() " + size);
            System.out.println("queue: " + queue);

            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                // word和dict里每个与word相差一个char的nextWord比较
                //如果这个nextword和end相同，达成目标，return length
                //否则将nextWord加入queue
                for (String nextWord: getNextWords(word, dict)) {
                    System.out.println("word: " + word);
                    System.out.println("nextWord: " + nextWord);

                    if (nextWord.equals(end)) {
                        return length;
                    }

                    queue.offer(nextWord);
                }

            }
            System.out.println("====================");
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

    /*
    start = "hit"
    end = "cog"
    dict = ["hot","dot","dog","lot","log"]
    As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.
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
        System.out.println(ladderLength(start, end, dict));
    }



}
