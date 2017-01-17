package top100.Enhance._3Graph_PreClass;

import java.util.*;

public class _4_BFS_WordLadder_1 {
	private int bfsHelper(String beginWord, String endWord, Set<String> wordList) {

        Deque<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        int steps = 0;

        queue.offerLast(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()) {

            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {                
                String curStr = queue.pollFirst();

                for (int j = 0; j < curStr.length(); j++) {
                    for (char k = 'a'; k <= 'z'; k++) {
                        String toStr = replace(curStr, j, k);

                        if (toStr.equals(endWord)) {
                            return ++steps;
                        }
                        if (visited.add(toStr) && wordList.contains(toStr)) {
                            queue.offerLast(toStr);
                        }    
                    }

                }

            }

        }

        return 0;
}
public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict == null)
            return 0;
        if (dict.size() == 0)
            return 0;

        return bfsHelper(start, end, dict);
}
private String replace(String str, int i, char c) {
        char[] chs = str.toCharArray();
        chs[i] = c;
        return new String(chs);
}
}
