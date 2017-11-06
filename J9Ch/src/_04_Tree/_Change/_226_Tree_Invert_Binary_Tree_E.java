package _04_Tree._Change;

import lib.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


//
//
//
public class _226_Tree_Invert_Binary_Tree_E {


    public class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            final TreeNode left = root.left, right = root.right;
            root.left = invertTree(right);
            root.right = invertTree(left);
            return root;
        }
    }


    public class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            final Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                final TreeNode node = stack.pop();
                final TreeNode left = node.left;
                node.left = node.right;
                node.right = left;
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
            return root;
        }
    }



    public class Solution3 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) { return null;
            }
            final Queue<TreeNode> queue = new LinkedList<>(); queue.offer(root);
            while(!queue.isEmpty()) {
                final TreeNode node = queue.poll(); final TreeNode left = node.left; node.left = node.right;
                node.right = left;
                if(node.left != null) { queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right); }
            }
            return root;
        }
    }
}
/*

 */
/*

 */
