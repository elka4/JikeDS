package J_4_BFS.other.Word_Ladder;

import org.junit.Test;

import java.util.*;

/** 121. Word Ladder II
 * Hard

 * Created by tianhuizhu on 6/28/17.
 */
//Backtracking
// Depth First Search
// Breadth First Search


public class _121_Word_Ladder_II {

    //version 1,
    public List<List<String>> findLadders(String start, String end,
                                          Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);

        bfs(map, distance, start, end, dict);

        System.out.println("map " + map);
        System.out.println("distance " + distance);

        List<String> path = new ArrayList<String>();

        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
             String start, Map<String, Integer> distance,
             Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) &&
                        distance.get(crt) == distance.get(next) + 1) {

                    dfs(ladders, path, next, start, distance, map);
                }
            }
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
             String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);

            for (String next : nextList) {

                map.get(next).add(crt);
                if (!distance.containsKey(next)) {

                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }
    // get the list contains word with one char difference
    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }

    @Test
    public void test_expand(){
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>();
        String[] strs = {"hot","dot","dog","lot","log"};
        dict.addAll(Arrays.asList(strs));
        System.out.println(expand("hot", dict));
        System.out.println(expand("dot", dict));
        System.out.println(expand("dog", dict));

    }
    /*
    start = "hit"
    end = "cog"
    dict = ["hot","dot","dog","lot","log"]
    Return
      [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
      ]

     */

    @Test
    public void test01(){
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>();
        String[] strs = {"hot","dot","dog","lot","log"};
        dict.addAll(Arrays.asList(strs));
        System.out.println(findLadders(start, end, dict));
    }

///////////////////////////////////////////////////////////////////////////////////////



}
