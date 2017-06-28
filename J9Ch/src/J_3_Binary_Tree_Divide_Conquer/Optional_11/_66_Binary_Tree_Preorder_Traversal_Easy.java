package J_3_Binary_Tree_Divide_Conquer.Optional_11;
import java.util.*;
/**66 Binary Tree Preorder Traversal

 * Created by tianhuizhu on 6/28/17.
 */
public class _66_Binary_Tree_Preorder_Traversal_Easy {

    //Version 0: Non-Recursion (Recommend)
    /**
     * Definition for binary tree
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            List<Integer> preorder = new ArrayList<Integer>();

            if (root == null) {
                return preorder;
            }

            stack.push(root);
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                preorder.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return preorder;
        }
    }

    //Version 1: Traverse
    public class Solution2 {
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            traverse(root, result);
            return result;
        }
        // 把root为跟的preorder加入result里面
        private void traverse(TreeNode root, ArrayList<Integer> result) {
            if (root == null) {
                return;
            }

            result.add(root.val);
            traverse(root.left, result);
            traverse(root.right, result);
        }
    }

    //Version 2: Divide & Conquer
    public class Solution3 {
        public ArrayList<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // null or leaf
            if (root == null) {
                return result;
            }

            // Divide
            ArrayList<Integer> left = preorderTraversal(root.left);
            ArrayList<Integer> right = preorderTraversal(root.right);

            // Conquer
            result.add(root.val);
            result.addAll(left);
            result.addAll(right);
            return result;
        }
    }
}
