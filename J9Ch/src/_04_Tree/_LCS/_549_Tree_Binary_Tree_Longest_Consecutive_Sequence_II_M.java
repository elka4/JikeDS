package _04_Tree._LCS;

import lib.TreeNode;


//
//
//
public class _549_Tree_Binary_Tree_Longest_Consecutive_Sequence_II_M {
    public class Solution {
        int maxval = 0;
        public int longestConsecutive(TreeNode root) {
            longestPath(root);
            return maxval;
        }
        public int[] longestPath(TreeNode root) {
            if (root == null)
                return new int[] {0,0};
            int inr = 1, dcr = 1;
            if (root.left != null) {
                int[] l = longestPath(root.left);
                if (root.val == root.left.val + 1)
                    dcr = l[1] + 1;
                else if (root.val == root.left.val - 1)
                    inr = l[0] + 1;
            }
            if (root.right != null) {
                int[] r = longestPath(root.right);
                if (root.val == root.right.val + 1)
                    dcr = Math.max(dcr, r[1] + 1);
                else if (root.val == root.right.val - 1)
                    inr = Math.max(inr, r[0] + 1);
            }
            maxval = Math.max(maxval, dcr + inr - 1);
            return new int[] {inr, dcr};
        }
    }



    public class Solution2 {
        int max = 0;

        class Result {
            TreeNode node;
            int inc;
            int des;
        }

        public int longestConsecutive(TreeNode root) {
            traverse(root);
            return max;
        }

        private Result traverse(TreeNode node) {
            if (node == null) return null;

            Result left = traverse(node.left);
            Result right = traverse(node.right);

            Result curr = new Result();
            curr.node = node;
            curr.inc = 1;
            curr.des = 1;

            if (left != null) {
                if (node.val - left.node.val == 1) {
                    curr.inc = Math.max(curr.inc, left.inc + 1);
                }
                else if (node.val - left.node.val == -1) {
                    curr.des = Math.max(curr.des, left.des + 1);
                }
            }

            if (right != null) {
                if (node.val - right.node.val == 1) {
                    curr.inc = Math.max(curr.inc, right.inc + 1);
                }
                else if (node.val - right.node.val == -1) {
                    curr.des = Math.max(curr.des, right.des + 1);
                }
            }

            max = Math.max(max, curr.inc + curr.des - 1);

            return curr;
        }
    }
}
/*

 */
/*

 */
