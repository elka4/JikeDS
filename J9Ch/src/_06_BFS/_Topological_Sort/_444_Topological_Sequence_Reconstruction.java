package _06_BFS._Topological_Sort;
import java.util.*;
import org.junit.Test;
public class _444_Topological_Sequence_Reconstruction {
//    Java Solution using BFS Topological Sort
    public class Solution {
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            Map<Integer, Integer> indegree = new HashMap<>();

            for(int[] seq: seqs) {
                if(seq.length == 1) {
                    if(!map.containsKey(seq[0])) {
                        map.put(seq[0], new HashSet<>());
                        indegree.put(seq[0], 0);
                    }
                } else {
                    for(int i = 0; i < seq.length - 1; i++) {
                        if(!map.containsKey(seq[i])) {
                            map.put(seq[i], new HashSet<>());
                            indegree.put(seq[i], 0);
                        }

                        if(!map.containsKey(seq[i + 1])) {
                            map.put(seq[i + 1], new HashSet<>());
                            indegree.put(seq[i + 1], 0);
                        }

                        if(map.get(seq[i]).add(seq[i + 1])) {
                            indegree.put(seq[i + 1], indegree.get(seq[i + 1]) + 1);
                        }
                    }
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for(Map.Entry<Integer, Integer> entry: indegree.entrySet()) {
                if(entry.getValue() == 0) queue.offer(entry.getKey());
            }

            int index = 0;
            while(!queue.isEmpty()) {
                int size = queue.size();
                if(size > 1) return false;
                int curr = queue.poll();
                if(index == org.length || curr != org[index++]) return false;
                for(int next: map.get(curr)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if(indegree.get(next) == 0) queue.offer(next);
                }
            }
            return index == org.length && index == map.size();
        }
    }




/*    Java O(n) time,O(n) space AC solution 14ms like count sort
    The basic idea is to count how many numbers are smaller(self include) than the current number.
    We then compare this count to the org.
    It is pretty like the idea of count sort.*/

    public class Solution2 {
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            int len = org.length;
            int[] map = new int[len + 1];//map number to its index
            Arrays.fill(map, -1);
            int[] memo = new int[org.length];//count how many numbers are smaller(on the right)
            for (int i = 0; i < len; i++) {
                map[org[i]] = i;
            }
            for (int[] seq : seqs) {
                if (seq.length == 0) continue;
                int prev = seq[0];
                if (prev <= 0 || prev > len || map[prev] == -1) return false;
                for (int i = 1; i < seq.length; i++) {
                    int curr = seq[i];
                    if (curr <= 0 || curr > len || map[curr] == -1) return false;
                    memo[map[prev]] = Math.max(memo[map[prev]], len - map[curr] + 1);
                    prev = curr;
                }
                memo[map[prev]] = Math.max(memo[map[prev]], 1);
            }
            for (int i = 0; i < memo.length; i++) {
                if (memo[i] != len - i) return false;
            }
            return true;
        }
    }

    //Java 145ms with comments
    class Solution3{
        public boolean sequenceReconstruction(int[] org, int[][] seqs) {
            if(seqs.length == 0 && org.length > 0)
                return false;

            Map<Integer, Set<Integer>> map = new HashMap<>();
            Map<Integer,Integer> times = new HashMap<Integer, Integer>();
            for(int[] seq: seqs) {
                for(int i: seq) {
                    times.putIfAbsent(i, 0);
                    map.putIfAbsent(i, new HashSet<Integer>());
                }
            }

            for(int i = 0; i < seqs.length; i++) {
                for(int j = 0; j < seqs[i].length - 1; j++) {
                    int cur = seqs[i][j];
                    int next = seqs[i][j+1];

                    map.get(cur).add(next);
                    // count the num of each element(the head element should not occur)
                    times.put(next, times.get(next) + 1);
                }
            }
            // num of element does not match
            if(times.size() != org.length)  return false;

            //  -------- find the head first -------
            int head = -1;
            for(Map.Entry<Integer, Integer> entry: times.entrySet()) {
                int value = entry.getValue();
                int key = entry.getKey();
                if(value == 0 && head != -1)   // multiple head, return false
                    return false;
                if(value == 0)
                    head = key;
            }

            if(head == -1)       // no head, return false
                return false;


            Queue<Integer> que = new LinkedList<Integer>();
            // result array -- level[2] = 3 means 3->2
            int[] level = new int[org.length + 1];
            level[head] = -1;
            que.offer(head);

            HashSet<Integer> visited = new HashSet<Integer>();
            while(!que.isEmpty()) {
                int cur = que.poll();
                visited.add(cur);
                for(int next: map.get(cur)) {
                    level[next] = cur;
                    if(!visited.contains(next)) {
                        que.offer(next);
                    }
                }
            }
            // org: 1 2 3 4, check level[2] == 1 && level[3] == 2 && level[4] == 3
            for(int i = 1; i < org.length; i++) {
                if(level[org[i]] != org[i - 1])
                    return false;
            }

            return true;
        }
    }
//-------------------------------------------------------------------------
    // 9Ch
public class Jiuzhang {
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
            int ele = q.poll();
            for (int next : map.get(ele)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) q.add(next);
            }
            if (ele != org[cnt]) {
                return false;
            }
            cnt++;
        }

        return cnt == org.length;
    }
}
//-------------------------------------------------------------------------

//-------------------------------------------------------------------------


}
/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true
UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.
 */

/*
判断是否序列 org 能唯一地由 seqs重构得出. org是一个由从1到n的正整数排列而成的序列，1 ≤ n ≤ 10^4。 重构表示组合成seqs的一个最短的父序列 (意思是，一个最短的序列使得所有 seqs里的序列都是它的子序列). 判断是否有且仅有一个能从 seqs重构出来的序列，并且这个序列是org。


 */