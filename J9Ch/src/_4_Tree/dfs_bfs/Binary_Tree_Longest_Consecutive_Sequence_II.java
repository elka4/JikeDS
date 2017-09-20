package _4_Tree.dfs_bfs;

import lib.*;

/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */

public class Binary_Tree_Longest_Consecutive_Sequence_II {

    //Approach #1 Brute Force [Time Limit Exceeded]




/////////////////////////////////////////////////////////////


    //Approach #2 Single traversal [Accepted]

    /*
    Algorithm

This solution is very simple. With every node, we associate two values/variables named inrinr and dcrdcr, where incrincr represents the length of the longest incrementing branch below the current node including itself, and dcrdcr represents the length of the longest decrementing branch below the current node including itself.

We make use of a recursive function longestPath(node) which returns an array of the form [inr, dcr][inr,dcr] for the calling node. We start off by assigning both inrinr and dcrdcr as 1 for the current node. This is because the node itself always forms a consecutive increasing as well as decreasing path of length 1.

Then, we obtain the length of the longest path for the left child of the current node using longestPath[root.left]. Now, if the left child is just smaller than the current node, it forms a decreasing sequence with the current node. Thus, the dcrdcr value for the current node is stored as the left child's dcrdcr value + 1. But, if the left child is just larger than the current node, it forms an incrementing sequence with the current node. Thus, we update the current node's inrinr value as left\_child(inr) + 1left_child(inr)+1.

Then, we do the same process with the right child as well. But, for obtaining the inrinr and dcrdcr value for the current node, we need to consider the maximum value out of the two values obtained from the left and the right child for both inrinr and dcrdcr, since we need to consider the longest sequence possible.

Further, after we've obtained the final updated values of inrinr and dcrdcr for a node, we update the length of the longest consecutive path found so far as maxval = \text{max}(inr + dcr - 1)maxval=max(inr+dcr−1).

The following animation will make the process clear:


     */
    public class Solution2 {
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


/*
Time complexity : O(n)O(n). The whole tree is traversed only once.
Space complexity : O(n)O(n). The recursion goes upto a depth of nn in the worst case.

 */




/////////////////////////////////////////////////////////////


    //Neat Java Solution Single pass O(n)
    public class Solution3 {
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





/////////////////////////////////////////////////////////////

    //Java solution, Binary Tree Post Order Traversal
    public class Solution4 {
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






/////////////////////////////////////////////////////////////


    /*Java recursively compute ascending and descending sequence
    For each subtree we recursively compute the length of longest ascending and descending path starting from the subtree root. Then we can efficiently check if we could join the two subtree together to get a longer child-parent-child path. In another word, for each subtree, the longest child-parent-child consecutive (with root being the parent) is dec+inc-1 since both the ascending and descending path start from root.*/

    private int maxlen = 0;
    private int[] helper(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int inc = 1, dec = 1;
        int[] left = helper(root.left), right = helper(root.right);
        if (root.left != null) {
            if (root.left.val == root.val+1) inc += left[0];
            if (root.left.val == root.val-1) dec += left[1];
        }
        if (root.right != null) {
            if (root.right.val == root.val+1) inc = Math.max(inc, 1+right[0]);
            if (root.right.val == root.val-1) dec = Math.max(dec, 1+right[1]);
        }
        maxlen = Math.max(inc+dec-1, maxlen);
        return new int[]{inc, dec};
    }
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return maxlen;
    }





/////////////////////////////////////////////////////////////
    //Java Recursive Solution with some comments.

    class solution6{
        int maxlen;
        public int longestConsecutive(TreeNode root) {
            maxlen = 0;
            if(root==null) return maxlen;
            incdecPath(root);
            return maxlen;
        }
        private int[] incdecPath(TreeNode curr){
            int[] ret = {1,1,1,1}; // index 0 and 1 used to record from current node to left node. index 0 used to record the length pointing from current node to current.left.
            //Of course, you can use a 2D array here using positive and negative numbers to mark the "flow" direction.
            if(curr.left!=null){
                int[] leftchild = incdecPath(curr.left);
                if(curr.val - curr.left.val == 1){
                    ret[0] += Math.max(leftchild[0], leftchild[2]);
                }
                if(curr.val - curr.left.val == -1){
                    ret[1] += Math.max(leftchild[1], leftchild[3]);
                }
            }
            if(curr.right!=null){
                int[] rightchild = incdecPath(curr.right);
                if(curr.val - curr.right.val == 1){
                    ret[2] += Math.max(rightchild[0], rightchild[2]);
                }
                if(curr.val - curr.right.val == -1){
                    ret[3] += Math.max(rightchild[1], rightchild[3]);
                }
            }
            maxlen = Math.max(maxlen,Math.max(ret[0] + ret[3], ret[1] + ret[2]) - 1);
            return ret;
        }
    }




/////////////////////////////////////////////////////////////






/////////////////////////////////////////////////////////////






/////////////////////////////////////////////////////////////








}
