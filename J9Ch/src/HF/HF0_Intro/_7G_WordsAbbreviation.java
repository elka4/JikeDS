package HF.HF0_Intro;

import org.junit.Test;

import java.util.*;

// Words Abbreviation
public class _7G_WordsAbbreviation {
    /**
     * @param dict an array of n distinct non-empty strings
     * @return an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        // Write your code here
        if (dict == null || dict.length == 0) {
            return new String[0];
        }

        int n = dict.length;
        String[] res = new String[n];
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
                    res[index] = word;
                } else {
                    for (int i : map.get(word)) {
                        next.add(i);
                    }
                }
            }
            results = next;
            len++;
        }
        return res;
    }

    /*
    Given dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
          return ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
     */
    @Test
    public void test1(){
        String[] strs = {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"};
        String[] result = wordsAbbreviation(strs);
        for (String s:result) {
            System.out.println(s);
        }
        /*
        l2e
        god
        internal
        me
        i6t
        interval
        inte4n
        f2e
        intr4n

         */
    }
//////////////////////////////////////////////////////////////////////////////////////

// version: 高频题班

    /**
     * @param dict an array of n distinct non-empty strings
     * @return an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation2(String[] dict) {
        int len = dict.length;
        String[] ans = new String[len];
        int[] prefix = new int[len];
        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < len; i++) {
            prefix[i] = 1;
            ans[i] = getAbbr(dict[i], 1);
            count.put(ans[i], count.getOrDefault(ans[i], 0) + 1);
        }

        while (true) {
            boolean unique = true;
            for (int i = 0; i < len; i++) {
                if (count.get(ans[i]) > 1) {
                    prefix[i]++;
                    ans[i] = getAbbr(dict[i], prefix[i]);
                    count.put(ans[i], count.getOrDefault(ans[i], 0) + 1);
                    unique = false;
                }
            }
            if (unique) {
                break;
            }
        }
        return ans;
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
            System.out.println(s);
        }
    }

//////////////////////////////////////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////

    //Verbose Java Solution, HashMap(s)
    public List<String> wordsAbbreviation2(List<String> dict) {
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

    @Test
    public void test4(){
        String[] strs = {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"};
        String[] result = wordsAbbreviation2(strs);
        for (String s:result) {
            System.out.println(s);
        }
    }
///////////////////////////////////////////////////////////////
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
标签
 */