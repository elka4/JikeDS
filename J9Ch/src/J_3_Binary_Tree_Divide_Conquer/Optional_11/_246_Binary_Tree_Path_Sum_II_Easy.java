package J_3_Binary_Tree_Divide_Conquer.Optional_11;
import java.util.*;
/**
 246
 Binary Tree Path Sum II
 * Created by tianhuizhu on 6/28/17.
 */
public class _246_Binary_Tree_Path_Sum_II_Easy {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public class Solution {
        /**
         * @param root the root of binary tree
         * @param target an integer
         * @return all valid paths
         */
        public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
            // Write your code here
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            ArrayList<Integer> buffer = new ArrayList<Integer>();
            if (root == null)
                return results;
            findSum(root, target, buffer, 0, results);
            return results;
        }

        public void findSum(TreeNode head, int sum, ArrayList<Integer> buffer, int level, List<List<Integer>> results) {
            if (head == null) return;
            int tmp = sum;
            buffer.add(head.val);
            for (int i = level;i >= 0; i--) {
                tmp -= buffer.get(i);
                if (tmp == 0) {
                    List<Integer> temp = new ArrayList<Integer>();
                    for (int j = i; j <= level; ++j)
                        temp.add(buffer.get(j));
                    results.add(temp);
                }
            }
            findSum(head.left, sum, buffer, level + 1, results);
            findSum(head.right, sum, buffer, level + 1, results);
            buffer.remove(buffer.size() - 1);
        }
    }
}
