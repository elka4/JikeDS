package J_3_Binary_Tree_Divide_Conquer.Optional_11;
import java.util.*;
/**
67
Binary Tree Inorder Traversal

 * Created by tianhuizhu on 6/28/17.
 */
public class _67_Binary_Tree_Inorder_Traversal_Easy {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public class Solution {
        /**
         * @param root: The root of binary tree.
         * @return: Inorder in ArrayList which contains node values.
         */
        public ArrayList<Integer> inorderTraversal(TreeNode root) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            ArrayList<Integer> result = new ArrayList<Integer>();
            TreeNode curt = root;
            while (curt != null || !stack.empty()) {
                while (curt != null) {
                    stack.add(curt);
                    curt = curt.left;
                }
                curt = stack.pop();
                result.add(curt.val);
                curt = curt.right;
            }
            return result;
        }
    }
}
