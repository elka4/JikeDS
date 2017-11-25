package _04_Tree.HF3_Algo_DS_II_2BST;

import lib.TreeNode;
import org.junit.Test;
import java.util.*;
/*
• DFS的两种理解方式:
1. 按照实际执行顺序模拟 (适合枚举型DFS，下节课内容) 2. 按照DFS的定义宏观理解 (适合分治型DFS，本节课内容)
 */

//Convert BST to Greater Tree
public class _1ConvertBSTtoGreaterTree {
    /**
     * @param root the root of binary tree
     * @return the new root
     */
    public int sum = 0;

    void helper(TreeNode root){
        if (root == null) {
            return;
        }
        if (root.right != null) {
            helper(root.right);
        }

        root.val = (sum += root.val);

        if (root.left != null) {
            helper(root.left);
        }
    }

    public TreeNode convertBST(TreeNode root) {
        // Write your code here
        helper(root);
        return root;
    }

    @Test
    public void test01(){
        int[] input = {2,5,13};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        convertBST(root).print();

        /*
         5
        / \
        2 13

         18
        / \
        20 13
         */
    }

//---------------------------------/////////////////////

    // version: 高频题班
    /**
     * @param root the root of binary tree
     * @return the new root
     */
    int sum2 = 0;

    void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.right);
        sum2 += cur.val;
        cur.val = sum2;
        dfs(cur.left);
    }

    public TreeNode convertBST2(TreeNode root) {
        // Write your code here
        dfs(root);
        return root;
    }
//---------------------------------//////////////////

    @Test
    public void test02(){
        int[] input = {2,5,13};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        convertBST2(root).print();

    }
//------------------------------------------------------------------------------
/*The basic idea is to do a reversed inorder traversal. When we visit a node we add the sum of all previous nodes (to the right) to its value and also update the sum.

    recursive method*/

    private int sum3 = 0;
    public TreeNode convertBST3(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        int tmp = root.val;
        root.val += sum3;
        sum3 += tmp;
        convertBST(root.left);
        return root;
    }

//    iterative method using stack

    public TreeNode convertBST4(TreeNode root) {
        if (root == null) return null;
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            int tmp = cur.val;
            cur.val += sum;
            sum += tmp;
            cur = cur.left;
        }
        return root;
    }

//    Morris Traversal

    public TreeNode convertBST5(TreeNode root) {
        TreeNode cur= root;
        int sum = 0;
        while (cur != null) {
            if (cur.right == null) {
                int tmp = cur.val;
                cur.val += sum;
                sum += tmp;
                cur = cur.left;
            } else {
                TreeNode prev = cur.right;
                while (prev.left != null && prev.left != cur)
                    prev = prev.left;
                if (prev.left == null) {
                    prev.left = cur;
                    cur = cur.right;
                } else {
                    prev.left = null;
                    int tmp = cur.val;
                    cur.val += sum;
                    sum += tmp;
                    cur = cur.left;
                }
            }
        }
        return root;
    }


//------------------------------------------------------------------------------

//    Reversed inorder traversal.

    public class Solution {
        public TreeNode convertBST(TreeNode root) {
            if(root == null) return null;
            DFS(root, 0);
            return root;
        }

        public int DFS(TreeNode root, int preSum){
            if(root.right != null) {
                preSum = DFS(root.right, preSum);
            }
            root.val = root.val + preSum;

            return (root.left != null) ? DFS(root.left, root.val) : root.val;
        }
    }


//------------------------------------------------------------------------------
}
/*
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Have you met this question in a real interview? Yes
Example
Given a binary search Tree ｀{5,2,3}｀:

              5
            /   \
           2     13
Return the root of new tree

             18
            /   \
          20     13
 */