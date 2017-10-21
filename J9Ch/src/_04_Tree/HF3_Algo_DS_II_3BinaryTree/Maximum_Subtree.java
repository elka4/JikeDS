package _04_Tree.HF3_Algo_DS_II_3BinaryTree;

import lib.TreeNode;
import org.junit.Test;

/*
333. Largest BST Subtree
Maximum Subtree

 */
public class Maximum_Subtree {
    /**
     * @param root the root of binary tree
     * @return the maximum weight node
     */
    public TreeNode result = null;
    public int maximum_weight = Integer.MIN_VALUE;

    public TreeNode findSubtree(TreeNode root) {
        // Write your code here
        helper(root);

        return result;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_weight = helper(root.left);
        int right_weight = helper(root.right);

        if (result == null || left_weight + right_weight + root.val > maximum_weight) {
            maximum_weight = left_weight + right_weight + root.val;
            result = root;
        }

        return left_weight + right_weight + root.val;
    }


    @Test
    public void test(){
        int[] arr = {0,-5,3,1,-4,2,-5};
        TreeNode root = TreeNode.createMinimalBST(arr);
//        root.right.setLeftChild(new TreeNode(15));
//        root.right.setRightChild(new TreeNode(7));
        root.print();
        findSubtree(root).print();

//        List<List<Integer>> result = verticalOrder2(root);

//        System.out.println(result);
    }

    /*
                   1
                  / \
                 /   \
                 -5   2
                / \ / \
                0 3 -4 -5

                3
     */

/////////////////////////////////////////////////////////////////////////////////////////////
    int max = Integer.MAX_VALUE;
    public TreeNode result2 = null;
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        helper2(root);
        return result2;
    }

    private int helper2(TreeNode root){
        if(root == null) {
            return 0;
        }
        int left = helper2(root.left);
        int right = helper2(root.right);
        int currentTotal = root.val + left + right;
        if(result2 == null || currentTotal > max){
            result2 = root;
            max = currentTotal;
        }
        return currentTotal;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    // leetcode
    // 333. Largest BST Subtree



/////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////


}
/*
Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.

 Notice

LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.

Have you met this question in a real interview? Yes
Example
Given a binary tree:

     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
return the node with value 3.
 */

/*
给你一棵二叉树，找二叉树中的一棵子树，他的所有节点之和最大。

返回这棵子树的根节点。

 注意事项

LintCode 会把你返回的节点作为最优子树来打印。

数据保证有且仅有唯一的解。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出如下二叉树

     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
返回值为 3 的节点。
 */

/*
leetcode

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \
 1   8   7
The Largest BST Subtree in this case is the highlighted one.
The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */