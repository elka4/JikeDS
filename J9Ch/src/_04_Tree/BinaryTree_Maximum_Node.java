package _04_Tree;

import lib.TreeNode;


// lintcode     BinaryTree Maximum Node
public class BinaryTree_Maximum_Node {




//---------------------------------///////////////////???
    // jiuzhang

    public TreeNode maxNode2(TreeNode root) {
        // Write your code here
        if (root == null)
            return root;

        TreeNode left = maxNode2(root.left);
        TreeNode right = maxNode2(root.right);
        return max(root, max(left, right));
    }

    TreeNode max(TreeNode a, TreeNode b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (a.val > b.val) {
            return a;
        }
        return b;
    }
//---------------------------------///////////////////
//---------------------------------///////////////////
//---------------------------------///////////////////
}
/*
在二叉树中寻找值最大的节点并返回。

您在真实的面试中是否遇到过这个题？ Yes
样例
给出如下一棵二叉树：

     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
返回值为 3 的节点。


 */