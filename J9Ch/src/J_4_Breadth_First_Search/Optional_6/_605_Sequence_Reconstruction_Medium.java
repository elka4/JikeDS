package J_4_Breadth_First_Search.Optional_6;
import java.util.*;
/**605 Sequence Reconstruction
 * Created by tianhuizhu on 6/28/17.
 */
public class _605_Sequence_Reconstruction_Medium {

    public class Solution {
        /**
         * @param org a permutation of the integers from 1 to n
         * @param seqs a list of sequences
         * @return true if it can be reconstructed only one or false
         */
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            // Write your code here
            Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
            Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();

            for (int num : org) {
                map.put(num, new HashSet<Integer>());
                indegree.put(num, 0);
            }

            int n = org.length;
            int count = 0;
            for (int[] seq : seqs) {
                count += seq.length;
                if (seq.length >= 1 && (seq[0] <= 0 || seq[0] > n))
                    return false;
                for (int i = 1; i < seq.length; i++) {
                    if (seq[i] <= 0 || seq[i] > n)
                        return false;
                    if (map.get(seq[i - 1]).add(seq[i]))
                        indegree.put(seq[i], indegree.get(seq[i]) + 1);
                }
            }

            // case: [1], []
            if (count < n)
                return false;

            Queue<Integer> q = new ArrayDeque<Integer>();
            for (int key : indegree.keySet())
                if (indegree.get(key) == 0)
                    q.add(key);

            int cnt = 0;
            while (q.size() == 1) {
                for (int next : map.get(q.poll())) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) q.add(next);
                }
                cnt++;
            }

            return cnt == org.length;
        }
    }
}
