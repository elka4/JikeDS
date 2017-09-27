package J_4_BFS.Optional_6;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
/**
 * 624 Remove Substrings
 * Created by tianhuizhu on 6/28/17.
 */
public class _624_Remove_Substrings {

    /**
     * @param s a string
     * @param dict a set of n substrings
     * @return the minimum length
     */
    public int minLength(String s, Set<String> dict) {
        // Write your code here
        Queue<String> que = new LinkedList<String>();
        Set<String> hash = new HashSet<String>();

        int min = s.length();
        que.offer(s);
        hash.add(s);

        while (!que.isEmpty()) {
            s = que.poll();
            for (String sub : dict) {
                int found = s.indexOf(sub);
                while (found != -1) {
                    String new_s = s.substring(0, found) +
                            s.substring(found + sub.length(), s.length());
                    if (!hash.contains(new_s)) {
                        if (new_s.length() < min)
                            min = new_s.length();
                        que.offer(new_s);
                        hash.add(new_s);
                    }
                    found = s.indexOf(sub, found + 1);
                }
            }
        }
        return min;
    }

    @Test
    public void test01() {
        String s = "ccdaabcdbb";
        Set<String> dict = new HashSet<>();
        dict.add("ab");
        dict.add("cd");
        System.out.println(minLength(s, dict));
    }
}
