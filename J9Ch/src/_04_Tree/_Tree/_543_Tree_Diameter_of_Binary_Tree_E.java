package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _543_Tree_Diameter_of_Binary_Tree_E {

    public class Solution {
        int max = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return max;
        }

        private int maxDepth(TreeNode root) {
            if (root == null) return 0;

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            max = Math.max(max, left + right);

            return Math.max(left, right) + 1;
        }
    }


    class Solution2{
        public int DiameterOfBinaryTree(TreeNode root) {
            return DFS(root)[0];
        }

        // int[2] = [best, height]
        private int[] DFS(TreeNode node)
        {
            if (node == null) return new int[] { 0, 0 };
            int[] left = DFS(node.left);
            int[] right = DFS(node.right);

            int best = Math.max(left[1] + right[1], Math.max(left[0], right[0]));
            int height = 1 + Math.max(left[1], right[1]);
            return new int[] { best, height };
        }
    }

    public class Solution3 {
        int max;
        public int diameterOfBinaryTree(TreeNode root) {
            max = 0;
            height(root);
            return max;
        }
        int height(TreeNode root){
            if(root==null)return -1;
            int leftH = height(root.left);
            int rightH = height(root.right);
            int height = Math.max(leftH,rightH)+1;
            max = Math.max(max,leftH+rightH+2);
            return height;
        }
    }
}
