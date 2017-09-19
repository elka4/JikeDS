package HF.OA9;

import lib.*;
import org.junit.Test;

//树上DFS
//Maximum Subtree
public class _9MaximumSubtree {
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
    public void test01(){
        int[] input = {0,-5,3,1,-4,2,-5};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        findSubtree(root).print();
    }

    @Test
    public void test02(){
        int[] input = {0,8,-5,1,-4,2,-5};
        TreeNode root = TreeNode.createMinimalBST(input);
        root.print();
        findSubtree(root).print();
    }

////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////


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