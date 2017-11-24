package j_3_BianryTree.video;

import lib.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

//root -> depth Divide & Conquer
@SuppressWarnings("all")

public class _03Maximum_Depth_of_Binary_Tree {
  public int maxDepth(TreeNode root) {
	  //出口
        if (root == null) {
            return 0;
        }
        //不管三七二十，先分成左右子树
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        //conquer
        return Math.max(left, right) + 1;
    }

//---------------------------------//////////////


    //version 2: Traverse
    @SuppressWarnings("all")

    class _02Maximum_Depth_of_Binary_Tree_Traverse {
        /**
         * @param root: The root of binary tree.
         * @return: An integer.
         */
        private int depth;//用了一个全局变量，不鼓励这么做

        public int maxDepth(TreeNode root) {
            depth = 0;
            helper(root, 1);
            return depth;
        }

        private void helper(TreeNode node, int curtDepth) {
            if (node == null) {
                return;
            }
            if (curtDepth > depth) {
                depth = curtDepth;
            }
            helper(node.left, curtDepth + 1);
            helper(node.right, curtDepth + 1);
        }
    }

//---------------------------------//////////////
//non-recursion
@SuppressWarnings("all")

public class _02Maximum_Depth_of_Binary_Tree_NonRecursion {
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size-- > 0) {
                TreeNode cur = stack.pop();
                if (cur.left != null){
                    stack.addLast(cur.left);
                }
                if (cur.right != null){
                    stack.addLast(cur.right);
                }
            }
            count++;

        }

        return count;
    }
}
//---------------------------------//////////////
}

/*
 * Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest
 path from the root node down to the farthest leaf node.

Example: Given a binary tree as follow:
  1
 / \ 
2   3
   / \
  4   5
The maximum depth is 3.
Tags: Divide and Conquer, Recursion, Binary Tree, Uber
Related Problems: Easy Minimum Depth of Binary Tree 31 %
 * */
