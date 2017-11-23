package HF.HF3_Algo_DS_II_2BST;

import lib.TreeNode;
import org.junit.Test;

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

//------------------------------------------------------------------------/

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
//-----------------------------------------------------------///

    @Test
    public void test02(){
        int[] input = {2,5,13};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        convertBST2(root).print();

    }

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