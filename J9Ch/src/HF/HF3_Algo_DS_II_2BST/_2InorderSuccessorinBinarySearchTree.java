package HF.HF3_Algo_DS_II_2BST;

import lib.TreeNode;
import org.junit.Test;

/*
 小技巧总结:
• 遇到BST上操作的问题，可以拿给定的节点(区间)与root做比较，分
类讨论、分而治之
 扩展问题:
• BST上求一段的和
 */

//Inorder Successor in Binary Search Tree
public class _2InorderSuccessorinBinarySearchTree {

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode successor = null;
            while (root != null && root != p) {
                if (root.val > p.val) {
                    successor = root;
                    root = root.left;
                } else {
                    root = root.right;
                }
            }

            if (root == null) {
                return null;
            }

            if (root.right == null) {
                return successor;
            }

            root = root.right;
            while (root.left != null) {
                root = root.left;
            }

            return root;
        }


//-------------------------------------------------------------------------

    // version: 高频题班
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessor2(root.right, p);
        } else {
            TreeNode left = inorderSuccessor2(root.left, p);
            return (left != null) ? left : root;
        }
    }

    @Test
    public void test02(){
        int[] input = {1,2};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        inorderSuccessor2(root, new TreeNode(1)).print();

    }

    @Test
    public void test02_2(){
        int[] input = {1,2,3};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        inorderSuccessor2(root, new TreeNode(2)).print();

    }
//-------------------------------------------------------------------------

}
/*
Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.

 Notice

It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p)

Have you met this question in a real interview? Yes
Example
Given tree = [2,1] and node = 1:

  2
 /
1
return node 2.

Given tree = [2,1,3] and node = 2:

  2
 / \
1   3
return node 3.
 */