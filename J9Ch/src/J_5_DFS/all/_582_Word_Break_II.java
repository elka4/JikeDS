package J_5_DFS.all;

import org.junit.Test;

import java.util.*;

/** 582 Word Break II
 * Created by tianhuizhu on 6/28/17.
 */
public class _582_Word_Break_II {

    // version 1:
    private void search(int index, String s, List<Integer> path,
                        boolean[][] isWord, boolean[] possible,
                        List<String> result) {
        if (!possible[index]) {
            return;
        }

        if (index == s.length()) {
            StringBuilder sb = new StringBuilder();
            int lastIndex = 0;
            for (int i = 0; i < path.size(); i++) {
                sb.append(s.substring(lastIndex, path.get(i)));
                if (i != path.size() - 1) sb.append(" ");
                lastIndex = path.get(i);
            }
            result.add(sb.toString());
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (!isWord[index][i]) {
                continue;
            }
            path.add(i + 1);
            search(i + 1, s, path, isWord, possible, result);
            path.remove(path.size() - 1);
        }
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        ArrayList<String> result = new ArrayList<String>();
        if (s.length() == 0) {
            return result;
        }

        boolean[][] isWord = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String word = s.substring(i, j + 1);
                isWord[i][j] = wordDict.contains(word);
            }
        }

        boolean[] possible = new boolean[s.length() + 1];
        possible[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (isWord[i][j] && possible[j + 1]) {
                    possible[i] = true;
                    break;
                }
            }
        }

        List<Integer> path = new ArrayList<Integer>();
        search(0, s, path, isWord, possible, result);
        return result;
    }

    /*
    Gieve s = lintcode,
    dict = ["de", "ding", "co", "code", "lint"].
    A solution is ["lint code", "lint co de"].

    s = "catsanddog",
    dict = ["cat", "cats", "and", "sand", "dog"].
    A solution is ["cats and dog", "cat sand dog"].
     */

    @Test
    public void test01(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"de", "ding", "co", "code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak(s, wordDict));
    }

    @Test
    public void test02(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "catsanddog";
        String[] arr = {"cat", "cats", "and", "sand", "dog"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak(s, wordDict));
    }

//-------------------------------------------------------------------------


    // version 2:

    public ArrayList<String> wordBreak2(String s, Set<String> dict) {
        // Note: The Solution object is instantiated
        // only once and is reused by each test case.

        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        return wordBreakHelper(s,dict,map);
    }

    public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String,
            ArrayList<String>> memo){
        if(memo.containsKey(s)) return memo.get(s);
        ArrayList<String> result = new ArrayList<String>();
        int n = s.length();
        if(n <= 0) return result;
        for(int len = 1; len <= n; ++len){
            String subfix = s.substring(0,len);
            if(dict.contains(subfix)){
                if(len == n){
                    result.add(subfix);
                }else{
                    String prefix = s.substring(len);
                    ArrayList<String> tmp = wordBreakHelper(prefix, dict, memo);
                    for(String item:tmp){
                        item = subfix + " " + item;
                        result.add(item);
                    }
                }
            }
        }
        memo.put(s, result);
        return result;
    }
    @Test
    public void test03(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"de", "ding", "co", "code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak2(s, wordDict));
    }

    @Test
    public void test04(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "catsanddog";
        String[] arr = {"cat", "cats", "and", "sand", "dog"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak2(s, wordDict));
    }

///////////////////////////////////////////////////////////////////////////////


    //version3, leetcode, memorized DFS
    public List<String> wordBreak3(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String,
            LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
    @Test
    public void test05(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"de", "ding", "co", "code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak3(s, wordDict));
    }

    @Test
    public void test06(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "catsanddog";
        String[] arr = {"cat", "cats", "and", "sand", "dog"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak3(s, wordDict));
    }

//////////////////////////////////////////////////////////////////////////////////

    //version 4
    public List<String> wordBreak4(String s, Set<String> dict) {
        List<String> result = new ArrayList<String>();
        for(int j = s.length() - 1; j >= 0; j--){
            if(dict.contains(s.substring(j)))
                break;
            else{
                if(j == 0)
                    return result;
            }
        }
        for(int i = 0; i < s.length()-1; i++)
        {
            if(dict.contains(s.substring(0,i+1)))
            {
                List<String> strs = wordBreak(s.substring(i+1,s.length()),dict);
                if(strs.size() != 0)
                    for(Iterator<String> it = strs.iterator();it.hasNext();)
                    {
                        result.add(s.substring(0,i+1)+" "+it.next());
                    }
            }
        }
        if(dict.contains(s)) result.add(s);
        return result;
    }
    @Test
    public void test07(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "lintcode";
        String[] arr = {"de", "ding", "co", "code", "lint"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak4(s, wordDict));
    }

    @Test
    public void test08(){
        //wordBreak(String s, Set<String> wordDict)
        String s = "catsanddog";
        String[] arr = {"cat", "cats", "and", "sand", "dog"};
        Set<String> wordDict =  new HashSet<>();
        wordDict.addAll(Arrays.asList(arr));
        System.out.println(wordBreak4(s, wordDict));
    }
}
