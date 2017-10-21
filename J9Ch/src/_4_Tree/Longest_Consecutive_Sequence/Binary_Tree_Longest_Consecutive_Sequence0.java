package _4_Tree.Longest_Consecutive_Sequence;

import lib.*;
import org.junit.Test;

import java.util.LinkedList;

/*
LeetCode – Binary Tree Longest Consecutive Sequence (Java)

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 */

// Binary Tree Longest Consecutive Sequence
public class Binary_Tree_Longest_Consecutive_Sequence0 {

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

    int max2=0;

    public int longestConsecutive2(TreeNode root) {
        helper(root);
        return max2;
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

        max2 = Math.max(max2, fromLeft);
        max2 = Math.max(max2, fromRight);

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

///////////////////////////////////////////////////////////////////

    public int longestConsecutive33(TreeNode root) {

        return dfs33(root, null, 0);
    }

    private int dfs33(TreeNode p, TreeNode parent, int length) {
        if (p == null) return length;
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        return Math.max(length,
                Math.max(dfs33(p.left, p, length), dfs33(p.right, p, length)));
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
    private int max = 0;

    public int longestConsecutive5(TreeNode root) {
        if (root == null) return 0;
        helper5(root, 0, root.val);
        return max;
    }

    public void helper5(TreeNode root, int cur, int target) {
        if (root == null) return;
        if (root.val == target) cur++;
        else cur = 1;
        max = Math.max(cur, max);
        helper5(root.left, cur, root.val + 1);
        helper5(root.right, cur, root.val + 1);
    }


///////////////////////////////////////////////////////////////////

    //Simple Recursive DFS without global variable
    public int longestConsecutive6(TreeNode root) {
        return (root==null)?0:
                Math.max(dfs6(root.left, 1, root.val),
                        dfs6(root.right, 1, root.val));
    }

    public int dfs6(TreeNode root, int count, int val){
        if(root==null) return count;
        count = (root.val - val == 1)?count+1:1;
        int left = dfs6(root.left, count, root.val);
        int right = dfs6(root.right, count, root.val);
        return Math.max(Math.max(left, right), count);
    }



///////////////////////////////////////////////////////////////////
    @Test
    public void test01(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right.right = new TreeNode(5);
        root.print();
        System.out.println(longestConsecutive6(root));

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.left = new TreeNode(2);
        root2.right.left.left = new TreeNode(1);
        root2.print();
        System.out.println(longestConsecutive6(root2));
    }


///////////////////////////////////////////////////////////////////




}
/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    /
   2
  /
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */