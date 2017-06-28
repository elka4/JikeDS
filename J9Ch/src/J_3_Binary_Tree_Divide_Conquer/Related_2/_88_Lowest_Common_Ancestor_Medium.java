package J_3_Binary_Tree_Divide_Conquer.Related_2;
import J_3_Binary_Tree_Divide_Conquer.Optional_11._619_Binary_Tree_Longest_Consecutive_Sequence_III_Medium;

import java.util.*;
/**
88
 Lowest Common Ancestor
 * Created by tianhuizhu on 6/28/17.
 */
public class _88_Lowest_Common_Ancestor_Medium {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //Version : Divide & Conquer

    public class Solution {
        // 在root为根的二叉树中找A,B的LCA:
        // 如果找到了就返回这个LCA
        // 如果只碰到A，就返回A
        // 如果只碰到B，就返回B
        // 如果都没有，就返回null
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
            if (root == null || root == node1 || root == node2) {
                return root;
            }

            // Divide
            TreeNode left = lowestCommonAncestor(root.left, node1, node2);
            TreeNode right = lowestCommonAncestor(root.right, node1, node2);

            // Conquer
            if (left != null && right != null) {
                return root;
            }
            if (left != null) {
                return left;
            }
            if (right != null) {
                return right;
            }
            return null;
        }
    }
}
