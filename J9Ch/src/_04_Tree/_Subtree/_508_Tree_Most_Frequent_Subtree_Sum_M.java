package _04_Tree._Subtree;
import lib.TreeNode;

import java.util.*;


//  508. Most Frequent Subtree Sum
//  https://leetcode.com/problems/most-frequent-subtree-sum/description/
//
public class _508_Tree_Most_Frequent_Subtree_Sum_M {
    //Verbose Java solution, postOrder traverse, HashMap (18ms)
    public class Solution {
        Map<Integer, Integer> sumToCount;
        int maxCount;

        public int[] findFrequentTreeSum(TreeNode root) {
            maxCount = 0;
            sumToCount = new HashMap<Integer, Integer>();

            postOrder(root);

            List<Integer> res = new ArrayList<>();
            for (int key : sumToCount.keySet()) {
                if (sumToCount.get(key) == maxCount) {
                    res.add(key);
                }
            }

            int[] result = new int[res.size()];
            for (int i = 0; i < res.size(); i++) {
                result[i] = res.get(i);
            }
            return result;
        }

        private int postOrder(TreeNode root) {
            if (root == null) return 0;

            int left = postOrder(root.left);
            int right = postOrder(root.right);

            int sum = left + right + root.val;
            int count = sumToCount.getOrDefault(sum, 0) + 1;
            sumToCount.put(sum, count);

            maxCount = Math.max(maxCount, count);

            return sum;
        }
    }

////////////////////////////////////////////////////////////////
    //Short Easy Java
    public class Solution2 {
        int max = 0;
        public int[] findFrequentTreeSum(TreeNode root) {
            if(root==null) return new int[0];
            Map<Integer, Integer> map = new HashMap<>();
            helper(root, map);
            List<Integer> res = new LinkedList<>();
            for(Map.Entry<Integer, Integer> me: map.entrySet()){
                if(me.getValue()==max) res.add(me.getKey());
            }
            return res.stream().mapToInt(i->i).toArray();
        }

        private int helper(TreeNode n, Map<Integer, Integer> map){
            int left = (n.left==null) ? 0 : helper(n.left, map);
            int right = (n.right==null) ? 0 : helper(n.right, map);
            int sum = left + right + n.val;
            map.put(sum, map.getOrDefault(sum,0)+1);
            max = Math.max(max, map.get(sum));
            return sum;
        }
    }
////////////////////////////////////////////////////////////////
    //Java divide and conquer
    public class Solution3 {
        int maxFreq = 0;
        int count = 0;
        public int[] findFrequentTreeSum(TreeNode root) {
            Map<Integer, Integer> map = new HashMap<>();
            traverse(root, map);
            int[] res = new int[count];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == maxFreq) {
                    res[i++] = entry.getKey();
                }
            }
            return res;
        }

        private int traverse(TreeNode root, Map<Integer, Integer> map) {
            if (root == null) {
                return 0;
            }

            int left = traverse(root.left, map);
            int right = traverse(root.right, map);

            int sum = left + right + root.val;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            if (map.get(sum) > maxFreq) {
                maxFreq = map.get(sum);
                count = 1;
            } else if (map.get(sum) == maxFreq) {
                count++;
            }
            return sum;
        }
    }

//////////////////////////////////////////////////////////////////////
    public class Solution0 {
        Map<Integer, Integer> sumToCount;
        int maxCount;

        public int[] findFrequentTreeSum(TreeNode root) {
            maxCount = 0;
            sumToCount = new HashMap<Integer, Integer>();

            postOrder(root);

            List<Integer> res = new ArrayList<>();
            for (int key : sumToCount.keySet()) {
                if (sumToCount.get(key) == maxCount) {
                    res.add(key);
                }
            }

            int[] result = new int[res.size()];
            for (int i = 0; i < res.size(); i++) {
                result[i] = res.get(i);
            }
            return result;
        }

        private int postOrder(TreeNode root) {
            if (root == null) return 0;

            int left = postOrder(root.left);
            int right = postOrder(root.right);

            int sum = left + right + root.val;
            int count = sumToCount.getOrDefault(sum, 0) + 1;
            sumToCount.put(sum, count);

            maxCount = Math.max(maxCount, count);

            return sum;
        }
    }
//////////////////////////////////////////////////////////////////////

}
/*

 */
/*
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */