package _05_DFS._DFS_Tree_Leaves;

import lib.TreeNode;

//129. Sum Root to Leaf Numbers

public class _129_DFS_Sum_Root_to_Leaf_Numbers_M {

    public class Solution {
        public int sumNumbers(TreeNode root) {
            if (root == null)
                return 0;
            return sumR(root, 0);
        }
        public int sumR(TreeNode root, int x) {
            if (root.right == null && root.left == null)
                return 10 * x + root.val;
            int val = 0;
            if (root.left != null)
                val += sumR(root.left, 10 * x + root.val);
            if (root.right != null)
                val += sumR(root.right, 10 * x + root.val);
            return val;
        }
    }


    //    Short Java solution. Recursion.
//I use recursive solution to solve the problem.
    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode n, int s){
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s*10 + n.val;
        return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }




//-------------------------------------------------------------------------///
public class Jiuzhang {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prev){
        if(root == null) {
            return 0;
        }

        int sum = root.val + prev * 10;
        if(root.left == null && root.right == null) {
            return sum;
        }

        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}



//-------------------------------------------------------------------------///






}
/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
 */