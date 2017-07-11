package J_4_Breadth_First_Search.Optional_6;
import java.util.*;import lib.TreeNode;import lib.AssortedMethods;import org.junit.Test;
/**605 Sequence Reconstruction
 * Created by tianhuizhu on 6/28/17.
 */
public class _605_Sequence_Reconstruction {

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
/*
Given org = [1,2,3], seqs = [[1,2],[1,3]]
Return false
Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

Given org = [1,2,3], seqs = [[1,2]]
Return false
Explanation:
The reconstructed sequence can only be [1,2].

Given org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
Return true
Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

Given org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
Return true
 */
    @Test
    public void test01(){
        int[] org = {1,2,3};
        int[][] seqs = {{1,2},{1,3}};
        System.out.println(sequenceReconstruction(org, seqs));
    }

    @Test
    public void test02(){
        int[] org = {1,2,3};
        int[][] seqs = {{1,2}};
        System.out.println(sequenceReconstruction(org, seqs));
    }
    @Test
    public void test03(){
        int[] org = {1,2,3};
        int[][] seqs = {{1,2},{1,3},{2,3}};
        System.out.println(sequenceReconstruction(org, seqs));
    }
    @Test
    public void test04(){
        int[] org = {4,1,5,2,6,3};
        int[][] seqs = {{5,2,6,3},{4,1,5,2}};
        System.out.println(sequenceReconstruction(org, seqs));
    }


}
