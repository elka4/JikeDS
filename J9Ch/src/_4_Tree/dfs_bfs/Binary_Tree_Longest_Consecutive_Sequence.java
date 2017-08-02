package _4_Tree.dfs_bfs;
import java.util.*;
import lib.*;

/*
LeetCode – Binary Tree Longest Consecutive Sequence (Java)

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 */


public class Binary_Tree_Longest_Consecutive_Sequence {

    //Java Solution 1 - Queue

    public int longestConsecutive(TreeNode root) {
        if(root==null)
            return 0;

        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        LinkedList<Integer> sizeQueue = new LinkedList<Integer>();

        nodeQueue.offer(root);
        sizeQueue.offer(1);
        int max=1;

        while(!nodeQueue.isEmpty()){
            TreeNode head = nodeQueue.poll();
            int size = sizeQueue.poll();

            if(head.left!=null){
                int leftSize=size;
                if(head.val==head.left.val-1){
                    leftSize++;
                    max = Math.max(max, leftSize);
                }else{
                    leftSize=1;
                }

                nodeQueue.offer(head.left);
                sizeQueue.offer(leftSize);
            }

            if(head.right!=null){
                int rightSize=size;
                if(head.val==head.right.val-1){
                    rightSize++;
                    max = Math.max(max, rightSize);
                }else{
                    rightSize=1;
                }

                nodeQueue.offer(head.right);
                sizeQueue.offer(rightSize);
            }


        }

        return max;
    }

///////////////////////////////////////////////////////////////////


    //Java Solution 2 - Recursion

    int max=0;

    public int longestConsecutive2(TreeNode root) {
        helper(root);
        return max;
    }

    public int helper(TreeNode root) {
        if(root==null)
            return 0;

        int l = helper(root.left);
        int r = helper(root.right);

        int fromLeft = 0;
        int fromRight= 0;

        if(root.left==null){
            fromLeft=1;
        }else if(root.left.val-1==root.val){
            fromLeft = l+1;
        }else{
            fromLeft=1;
        }

        if(root.right==null){
            fromRight=1;
        }else if(root.right.val-1==root.val){
            fromRight = r+1;
        }else{
            fromRight=1;
        }

        max = Math.max(max, fromLeft);
        max = Math.max(max, fromRight);

        return Math.max(fromLeft, fromRight);
    }


///////////////////////////////////////////////////////////////////

    //Approach #1 (Top Down Depth-first Search) [Accepted]

    //

    private int maxLength3 = 0;
    public int longestConsecutive3(TreeNode root) {
        dfs3(root, null, 0);
        return maxLength;
    }

    private void dfs3(TreeNode p, TreeNode parent, int length) {
        if (p == null) return;
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        maxLength3 = Math.max(maxLength, length);
        dfs3(p.left, p, length);
        dfs3(p.right, p, length);
    }
    //@lightmark presents a neat approach without storing the maxLength as a global variable.

    public int longestConsecutive33(TreeNode root) {
        return dfs33(root, null, 0);
    }

    private int dfs33(TreeNode p, TreeNode parent, int length) {
        if (p == null) return length;
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        return Math.max(length, Math.max(dfs33(p.left, p, length),
                dfs33(p.right, p, length)));
    }
    /*Complexity analysis

    Time complexity : O(n)O(n). The time complexity is the same as an in-order traversal of a binary tree with nn nodes.

    Space complexity : O(n)O(n). The extra space comes from implicit stack space due to recursion. For a skewed binary tree, the recursion could go up to nn levels deep.*/



///////////////////////////////////////////////////////////////////

    //Approach #2 (Bottom Up Depth-first Search) [Accepted]

   /* Algorithm:

    The bottom-up approach is similar to a post-order traversal. We return the consecutive path length starting at current node to its parent. Then its parent can examine if its node value can be included in this consecutive path.*/

    private int maxLength = 0;
    public int longestConsecutive4(TreeNode root) {
        dfs4(root);
        return maxLength;
    }

    private int dfs4(TreeNode p) {
        if (p == null) return 0;
        int L = dfs4(p.left) + 1;
        int R = dfs4(p.right) + 1;
        if (p.left != null && p.val + 1 != p.left.val) {
            L = 1;
        }
        if (p.right != null && p.val + 1 != p.right.val) {
            R = 1;
        }
        int length = Math.max(L, R);
        maxLength = Math.max(maxLength, length);
        return length;
    }
   /* Complexity analysis

    Time complexity : O(n)O(n). The time complexity is the same as a post-order traversal in a binary tree, which is O(n)O(n).

    Space complexity : O(n)O(n). The extra space comes from implicit stack space due to recursion. For a skewed binary tree, the recursion could go up to nn levels deep.*/

///////////////////////////////////////////////////////////////////

    /*Easy Java DFS, is there better time complexity solution?
    Just very intuitive depth-first search, send cur node value to the next level and compare it with the next level node.*/

    public class Solution5 {
        private int max = 0;

        public int longestConsecutive(TreeNode root) {
            if (root == null) return 0;
            helper(root, 0, root.val);
            return max;
        }

        public void helper(TreeNode root, int cur, int target) {
            if (root == null) return;
            if (root.val == target) cur++;
            else cur = 1;
            max = Math.max(cur, max);
            helper(root.left, cur, root.val + 1);
            helper(root.right, cur, root.val + 1);
        }

    }
///////////////////////////////////////////////////////////////////

    //Simple Recursive DFS without global variable
    public class Solution6 {
        public int longestConsecutive(TreeNode root) {
            return (root==null)?0:Math.max(dfs(root.left, 1, root.val), dfs(root.right, 1, root.val));
        }

        public int dfs(TreeNode root, int count, int val){
            if(root==null) return count;
            count = (root.val - val == 1)?count+1:1;
            int left = dfs(root.left, count, root.val);
            int right = dfs(root.right, count, root.val);
            return Math.max(Math.max(left, right), count);
        }
    }


///////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////




}
