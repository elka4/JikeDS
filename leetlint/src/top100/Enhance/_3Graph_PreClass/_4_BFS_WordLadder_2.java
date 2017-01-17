package top100.Enhance._3Graph_PreClass;

import java.util.*;

public class _4_BFS_WordLadder_2 {
	private int biBFSHelper(String begin, String end, Set<String> dic) {        
        int steps = 0;
        Set<String> visited = new HashSet<String>();
        visited.add(begin);
        visited.add(end);

        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();
        beginSet.add(begin);
        endSet.add(end);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            steps++;
            //always start from a smaller one
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> nextLevel = new HashSet<String>();
            for (String curStr : beginSet) {
            for (int i = 0; i < curStr.length(); i++) {
                  for (char j = 'a'; j <= 'z'; j++) {
                        String toCheck = replace(curStr, i, j);

                        if (endSet.contains(toCheck)) {
                            return steps + 1;
                        }

                        if (dic.contains(toCheck) && visited.add(toCheck)) {
                            nextLevel.add(toCheck);
                        }
                    }
                }
            }

            beginSet = nextLevel;
        }


        return 0;
	}
	private String replace(String str, int i, char c) {
	    char[] chs = str.toCharArray();
	    chs[i] = c;
	    return new String(chs);
	}
}
