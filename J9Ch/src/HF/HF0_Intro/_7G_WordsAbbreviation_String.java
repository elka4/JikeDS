package HF.HF0_Intro;
import org.junit.Test;

import java.util.*;

//  Words Abbreviation
//  http://www.lintcode.com/zh-cn/problem/words-abbreviation/

//  527. Word Abbreviation
//  https://leetcode.com/problems/word-abbreviation/description/

//  String Sort
//  Valid Word Abbreviation/
//  Minimum Unique Word Abbreviation
//  4:2
//
public class _7G_WordsAbbreviation_String {
//-------------------------------------------------------------------------
    //2
    // version: 高频题班
    public List<String> wordsAbbreviation(List<String> dict) {
        int len = dict.size();
        String[] result = new String[len];
        int[] prefix = new int[len];
        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < len; i++) {
            prefix[i] = 1;
            result[i] = getAbbr(dict.get(i), 1);
            count.put(result[i], count.getOrDefault(result[i], 0) + 1);
        }

        while (true) {
            boolean unique = true;
            for (int i = 0; i < len; i++) {
                if (count.get(result[i]) > 1) {
                    prefix[i]++;
                    result[i] = getAbbr(dict.get(i), prefix[i]);
                    count.put(result[i], count.getOrDefault(result[i], 0) + 1);
                    unique = false;
                }
            }
            if (unique) break;
        }
        return Arrays.asList(result);
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

        List<String> result = wordsAbbreviation(Arrays.asList(strs));
        System.out.println(result);
//        for (String s:result) {
//            System.out.print(s + ", ");
//        }
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
    public String[] wordsAbbreviation1(String[] dict) {
        if (dict == null || dict.length == 0) {
            return new String[0];
        }

        int n = dict.length;
        String[] result = new String[n];
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < dict.length; i++) {
            results.add(i);
        }
        int len = 2;
        while (!results.isEmpty()) {
            Map<String, Set<Integer>> map = new HashMap<String, Set<Integer>>();
            for (int i : results) {
                String curr = dict[i];

                String abbr = curr.length() <= (len + 1) ?
                        curr : curr.substring(0, len - 1) + (curr.length() - len)
                        + curr.charAt(curr.length() - 1);

                if (map.containsKey(abbr)) {
                    map.get(abbr).add(i);
                } else {
                    Set<Integer> set = new HashSet<Integer>();
                    set.add(i);
                    map.put(abbr, set);
                }
            }

            List<Integer> next = new ArrayList<Integer>();
            for (String word : map.keySet()) {
                if (map.get(word).size() == 1) {
                    int index = -1;
                    for (int i : map.get(word)) {
                        index = i;
                    }
                    result[index] = word;
                } else {
                    for (int i : map.get(word)) {
                        next.add(i);
                    }
                }
            }
            results = next;
            len++;
        }
        return result;
    }


    @Test
    public void test1(){
        String[] strs = {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"};
        String[] result = wordsAbbreviation1(strs);
        for (String s:result) {
            System.out.print(s + ", ");
        }
        //l2e, god, internal, me, i6t, interval, inte4n, f2e, intr4n
    }



//-------------------------------------------------------------------------
    //3
    //Really simple and straightforward Java solution
    public List<String> wordsAbbreviation3(List<String> dict) {
        int len=dict.size();
        String[] ans=new String[len];
        int[] prefix=new int[len];
        for (int i=0;i<len;i++) {
            prefix[i]=1;
            // make abbreviation for each string
            ans[i]=makeAbbr(dict.get(i), 1);
        }
        for (int i=0;i<len;i++) {
            while (true) {
                HashSet<Integer> set=new HashSet<>();
                for (int j=i+1;j<len;j++) {
                    // check all strings with the same abbreviation
                    if (ans[j].equals(ans[i])) set.add(j);
                }
                if (set.isEmpty()) break;
                set.add(i);
                for (int k: set){
                    // increase the prefix
                    ans[k]=makeAbbr(dict.get(k), ++prefix[k]);
                }

            }
        }
        return Arrays.asList(ans);
    }

    //这个recursion的写法还挺有意思
    private String makeAbbr(String s, int k) {
        if (k>=s.length()-2) return s;
        StringBuilder builder=new StringBuilder();
        builder.append(s.substring(0, k));
        builder.append(s.length()-1-k);
        builder.append(s.charAt(s.length()-1));
        return builder.toString();
    }

    @Test
    public void test3(){
        List<String> list = Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion");
        System.out.println(wordsAbbreviation3(list));
    }
    @Test
    public void test33(){
        System.out.println(makeAbbr("internal", 1));//i6l
        System.out.println(makeAbbr("internal", 2));//in5l
        System.out.println(makeAbbr("internal", 3));//int4l
        System.out.println(makeAbbr("internal", 4));//inte3l
    }
//--------------------------------------------------------------------------------
    //4
    //Verbose Java Solution, HashMap(s)
    public List<String> wordsAbbreviation2(List<String> dict) {
        Map<String, String> wordToAbbr = new HashMap<>();
        Map<Integer, List<String>> groups = new HashMap<>();

        // Try to group words by their length.
        // Because no point to compare words with different length.
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
                // Tick: use reversed abbreviation to word map to check
                // if there is any duplicated abbreviation
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

    @Test
    public void test4(){
        String[] strs = {"like", "lcce", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"};
        List<String> result = wordsAbbreviation(Arrays.asList(strs));
        for (String s:result) {
            System.out.println(s);
        }
    }
//-------------------------------------------------------------------------------
}
/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
 注意事项

Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
您在真实的面试中是否遇到过这个题？ Yes
样例
Given dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
return ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
标签-------------------------------------------------------------------------------
 */

/*
639. 单词缩写

给出一组 n 个不同的非空字符串，您需要按以下规则为每个单词生成 最小 的缩写。
1. 从第一个字符开始，然后加上中间缩写掉的字符的长度，后跟最后一个字符。
2. 如果有冲突，就是多个单词共享相同的缩写，使用较长的前缀，而不是仅使用第一个字符，直到使单词的缩写的映射变为唯一。 换句话说，最终得到的缩写不能映射到多个原始单词。
3. 如果缩写不会使单词更短，则不进行缩写，保持原样。

 注意事项

n 和每个单词的长度均不会超过 400。
每个单词的长度大于 1。
这些词只包括小写英文字母。
返回答案应该与原始数组保持相同的顺序。

样例
给出 dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
返回 ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
标签
排序 字符串处理 谷歌 Snapchat
相关题目
容易 检查缩写字
-------------------------------------------------------------------------------
 */