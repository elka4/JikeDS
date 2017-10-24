package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _654_Tree_Maximum_Binary_Tree_M {

    class Solution{
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            if(nums == null || nums.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(nums[0]);
            for(int i = 1; i < nums.length; i++) {
                root = insert(root, new TreeNode(nums[i]));
            }
            return root;
        }

        private TreeNode insert(TreeNode root, TreeNode node) {
            if(root == null) {
                return node;
            }
            if(node.val > root.val) {
                node.left = root;
                return node;
            }
            root.right = insert(root.right, node);
            return root;
        }
    }

    public class Solution2 {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            if (nums == null) return null;
            return build(nums, 0, nums.length - 1);
        }

        private TreeNode build(int[] nums, int start, int end) {
            if (start > end) return null;

            int idxMax = start;
            for (int i = start + 1; i <= end; i++) {
                if (nums[i] > nums[idxMax]) {
                    idxMax = i;
                }
            }

            TreeNode root = new TreeNode(nums[idxMax]);

            root.left = build(nums, start, idxMax - 1);
            root.right = build(nums, idxMax + 1, end);

            return root;
        }
    }

}
