package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _404_Tree_Sum_of_Left_Leaves_E {
    public class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if(root == null || root.left == null && root.right == null) return 0;

            int res = 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()) {
                TreeNode curr = queue.poll();

                if(curr.left != null && curr.left.left == null && curr.left.right == null){
                    res += curr.left.val;
                }
                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            return res;
        }
    }
//----------------------------------------------------------------------------
    // 9Ch
public class Jiuzhang {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int sum = 0;

        if(root.left != null) {
            TreeNode left = root.left;
            if(left.left == null && left.right == null) {
                sum += left.val;
            }
            else {
                sum += sumOfLeftLeaves(left);
            }
        }

        if(root.right != null) {
            TreeNode right = root.right;
            sum += sumOfLeftLeaves(right);
        }
        return sum;
    }
}
}
/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */