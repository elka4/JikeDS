package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _538_Tree_Convert_BST_to_Greater_Tree_M {
/*
Since this is a BST, we can do a reverse inorder traversal to traverse the nodes of the tree in descending order. In the process, we keep track of the running sum of all nodes which we have traversed thus far.
 */
    public class Solution {

        int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            convert(root);
            return root;
        }

        public void convert(TreeNode cur) {
            if (cur == null) return;
            convert(cur.right);
            cur.val += sum;
            sum = cur.val;
            convert(cur.left);
        }

    }
/*
Idea: Reversely traverse the tree and keep a sum of all previously visited values. Because its a BST, values seen before are all greater than current node.val . That's what we want according to the problem.
 */

    public class Solution2 {
        int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            if (root == null) return null;

            convertBST(root.right);

            root.val += sum;
            sum = root.val;

            convertBST(root.left);

            return root;
        }
    }

//    Reversed inorder traversal.

    public class Solution3 {
        public TreeNode convertBST(TreeNode root) {
            if(root == null) return null;
            DFS(root, 0);
            return root;
        }

        public int DFS(TreeNode root, int preSum){
            if(root.right != null) preSum = DFS(root.right, preSum);
            root.val = root.val + preSum;
            return (root.left != null) ? DFS(root.left, root.val) : root.val;
        }
    }
}
