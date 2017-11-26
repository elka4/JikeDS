package HF.HF3_Algo_DS_II_3BinaryTree;

import lib.TreeNode;
import org.junit.Test;

//Binary Tree Flipping
public class _1BinaryTreeFlipping {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // Write your code here
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode new_root = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.right = null;
        root.left = null;
        return new_root;
    }

    @Test
    public void test01(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.print();
        upsideDownBinaryTree(root).print();

    }

//-------------------------------------------------------------------------

    // version: 高频题班
    TreeNode newRoot;

    void dfs(TreeNode cur) {
        if (cur.left == null) {
            newRoot = cur;
            return;
        }
        dfs(cur.left);
        cur.left.right = cur;
        cur.left.left = cur.right;
        cur.left = null;            // important
        cur.right = null;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        // Write your code here
        if (root == null) {
            return null;
        }
        dfs(root);
        return newRoot;
    }

    @Test
    public void test02(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.print();
        upsideDownBinaryTree2(root).print();

    }

//-------------------------------------------------------------------------------

}
/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Have you met this question in a real interview? Yes
Example
Given a binary tree {1,2,3,4,5}

    1
   / \
  2   3
 / \
4   5
return the root of the binary tree {4,5,2,#,#,3,1}.

   4
  / \
 5   2
    / \
   3   1
 */