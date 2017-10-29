package _BinarySearch;
import java.util.*;
import org.junit.Test;
import lib.*;


public class _222_BinarySearch_Count_Complete_Tree_Nodes_M {

//    Concise Java solutions O(log(n)^2)
//    Main Solution - 572 ms

    class Solution {
        int height(TreeNode root) {
            return root == null ? -1 : 1 + height(root.left);
        }
        public int countNodes(TreeNode root) {
            int h = height(root);
            return h < 0 ? 0 :
                    height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                            : (1 << h-1) + countNodes(root.left);
        }
    }


    class Solution2 {
        public int countNodes(TreeNode root) {
            if (root == null)
                return 0;
            TreeNode left = root, right = root;
            int height = 0;
            while (right != null) {
                left = left.left;
                right = right.right;
                height++;
            }
            if (left == null)
                return (1 << height) - 1;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    class Solution3{
        public int countNodes(TreeNode root) {
            if (root == null)
                return 0;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

//    Accepted Easy Understand Java Solution
    public class Solution4 {

        public int countNodes(TreeNode root) {

            int leftDepth = leftDepth(root);
            int rightDepth = rightDepth(root);

            if (leftDepth == rightDepth)
                return (1 << leftDepth) - 1;
            else
                return 1+countNodes(root.left) + countNodes(root.right);

        }

        private int rightDepth(TreeNode root) {
            // TODO Auto-generated method stub
            int dep = 0;
            while (root != null) {
                root = root.right;
                dep++;
            }
            return dep;
        }

        private int leftDepth(TreeNode root) {
            // TODO Auto-generated method stub
            int dep = 0;
            while (root != null) {
                root = root.left;
                dep++;
            }
            return dep;
        }
    }



}
