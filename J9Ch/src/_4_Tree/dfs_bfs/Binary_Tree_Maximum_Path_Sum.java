package _4_Tree.dfs_bfs;

import lib.TreeNode;

/*
LeetCode – Binary Tree Maximum Path Sum (Java)

Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree. For example, given the below binary tree

       1
      / \
     2   3
the result is 6.


Analysis

1) Recursively solve this problem
2) Get largest left sum and right sum
2) Compare to the stored maximum
 */

public class Binary_Tree_Maximum_Path_Sum {

    //We can also use an array to store value for recursive methods.

    public int maxPathSum(TreeNode root) {
        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }

    public int calculateSum(TreeNode root, int[] max) {
        if (root == null)
            return 0;

        int left = calculateSum(root.left, max);
        int right = calculateSum(root.right, max);

        int current = Math.max(root.val, Math.max(root.val + left, root.val + right));

        max[0] = Math.max(max[0], Math.max(current, left + root.val + right));

        return current;
    }

///////////////////////////////////////////////////////////////////
/*    Accepted short solution in Java
    Here's my ideas:

    A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
    A recursive method maxPathDown(TreeNode node) (1) computes the maximum path sum with highest node is the input node, update maximum if necessary (2) returns the maximum sum of the path that can be extended to input node's parent.
    Code:*/

    public class Solution2 {
        int maxValue;

        public int maxPathSum(TreeNode root) {
            maxValue = Integer.MIN_VALUE;
            maxPathDown(root);
            return maxValue;
        }

        private int maxPathDown(TreeNode node) {
            if (node == null) return 0;
            int left = Math.max(0, maxPathDown(node.left));
            int right = Math.max(0, maxPathDown(node.right));
            maxValue = Math.max(maxValue, left + right + node.val);
            return Math.max(left, right) + node.val;
        }
    }


///////////////////////////////////////////////////////////////////
    //Elegant Java solution
    public class Solution3 {
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            helper(root);
            return max;
        }

        // helper returns the max branch
        // plus current node's value
        int helper(TreeNode root) {
            if (root == null) return 0;

            int left = Math.max(helper(root.left), 0);
            int right = Math.max(helper(root.right), 0);

            max = Math.max(max, root.val + left + right);

            return root.val + Math.max(left, right);
        }
    }


///////////////////////////////////////////////////////////////////

    //Sharing a simple JAVA solution

    class Solution4{
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            helper(root);
            return max;
        }

        public int helper(TreeNode root) {
            if(root == null)
                return Integer.MIN_VALUE;
            int left = Math.max(0, helper(root.left));
            int right = Math.max(0, helper(root.right));
            max = Math.max(max, root.val + left + right);
            return root.val + Math.max(left, right);
        }
    }

///////////////////////////////////////////////////////////////////

    //A recursive solution with comment

    class Solution5{
        // global max
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return max;
        }

        private int dfs(TreeNode root) {
            if (root == null) return 0;
            // 2 possible choices
            // 1.Already calculated in left or right child
            // 2.left max path + right max path + root
            int lMax = dfs(root.left);
            int rMax = dfs(root.right);
            if (lMax + rMax + root.val > max) max = lMax + rMax + root.val;
            // if the below path is negative, just make it 0 so that we could 'ignore' it
            return Math.max(0, root.val + Math.max(lMax, rMax));
        }
    }


///////////////////////////////////////////////////////////////////

    //My AC java recursive solution

/*    max1 is the max value of the current node to pass to the upper level node.

    max is the global max value that could be max1 or the sum of root and left max and right max*/

    public class Solution6 {

        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxPathSumR(root);
            return max;
        }

        public int maxPathSumR(TreeNode root) {

            if (root == null) return 0;
            int left = maxPathSumR(root.left);
            int right = maxPathSumR(root.right);

            int max1 = Math.max(root.val,
                    Math.max(root.val + left, root.val + right));

            max = Math.max(max, Math.max(max1, left + right + root.val));
            return max1;
        }
    }
///////////////////////////////////////////////////////////////////

/*        Accepted Java code [19 lines, simple and effective]

        The thought is bottom-up, first we need to get left and right tree max value and make sure that they need to be larger than 0. Next add them to current node value, compare to the total max value and update the total max value. Finally return current value with one of left or right max value (>=0).*/

        public class Solution7 {
            int max = 0;
            public int maxPathSum(TreeNode root) {
                if(root == null) return 0;
                max = root.val;
                helper(root);
                return max;
            }
            public int helper(TreeNode node)
            {
                if(node == null) return 0;
                int left = helper(node.left);
                int right = helper(node.right);
                left = left > 0 ? left : 0;
                right = right > 0 ? right : 0;
                int curMax = node.val + left + right;
                max = Math.max(max, curMax);
                return node.val + Math.max(left, right);
            }
        }

///////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////



}
