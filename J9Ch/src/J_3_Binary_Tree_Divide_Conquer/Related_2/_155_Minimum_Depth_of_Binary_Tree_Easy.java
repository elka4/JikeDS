package J_3_Binary_Tree_Divide_Conquer.Related_2;
import java.util.*;
/**
155
Minimum Depth of Binary Tree

 * Created by tianhuizhu on 6/28/17.
 */
public class _155_Minimum_Depth_of_Binary_Tree_Easy {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return getMin(root);
        }

        public int getMin(TreeNode root){
            if (root == null) {
                return Integer.MAX_VALUE;
            }

            if (root.left == null && root.right == null) {
                return 1;
            }

            return Math.min(getMin(root.left), getMin(root.right)) + 1;
        }
    }
}
