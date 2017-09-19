package HF.HF3_Algorithms_DS_II._3BinaryTree;

import java.util.*;

//Binary Tree Leaves Order Traversal
public class _2BinaryTreeOrderTraversal {
    public class Solution {
        /**
         * @param root the root of binary tree
         * @return collect and remove all leaves
         */
        public List<List<Integer>> findLeaves(TreeNode root) {
            // Write your code here
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            dfs(root, results);
            return results;
        }

        int dfs(TreeNode root, List<List<Integer>> results) {
            if (root == null) {
                return 0;
            }
            int level = Math.max(dfs(root.left, results), dfs(root.right, results)) + 1;
            int size = results.size();
            if (level > size) {
                results.add(new ArrayList<Integer>());
            }
            results.get(level - 1).add(root.val);
            return level;
        }
    }

////////////////////////////////////////////////////////////////

    // version: 高频题班
    public class Solution2 {
        /**
         * @param root the root of binary tree
         * @return collect and remove all leaves
         */
        Map<Integer, List<Integer>> depth = new HashMap<>();

        int dfs(TreeNode cur) {
            if (cur == null) {
                return 0;
            }
            int d = Math.max(dfs(cur.left), dfs(cur.right)) + 1;

            depth.putIfAbsent(d, new ArrayList<>());
            depth.get(d).add(cur.val);
            return d;
        }

        public List<List<Integer>> findLeaves(TreeNode root) {
            // Write your code here
            List<List<Integer>> ans = new ArrayList<>();

            int max_depth = dfs(root);

            for (int i = 1; i <= max_depth; i++) {
                ans.add(depth.get(i));
            }
            return ans;
        }
    }
}
/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Have you met this question in a real interview? Yes
Example
Given binary tree:

          1
         / \
        2   3
       / \
      4   5
Returns [[4, 5, 3], [2], [1]].


 */