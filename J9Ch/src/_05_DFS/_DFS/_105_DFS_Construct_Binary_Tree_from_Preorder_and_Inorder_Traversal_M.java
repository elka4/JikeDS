package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _105_DFS_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_M {

    class Solution{
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return helper(0, 0, inorder.length - 1, preorder, inorder);
        }

        public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
            if (preStart > preorder.length - 1 || inStart > inEnd) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[preStart]);
            int inIndex = 0; // Index of current root in inorder
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == root.val) {
                    inIndex = i;
                }
            }
            root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
            root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
            return root;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */