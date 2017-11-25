package _04_Tree._PathSum;
import lib.*;
import org.junit.Test;

//      leetcode 687. Longest Univalue Path
public class _687_Longest_Univalue_Path {
//Approach #1: Recursion [Accepted]
    class Solution{
        int ans;
        public int longestUnivaluePaht(TreeNode root){
            ans = 0;
            arrowLength(root);
            return ans;
        }
        private  int arrowLength(TreeNode node){
            if(node == null) return 0;

            int left = arrowLength(node.left);
            int right = arrowLength(node.right);

            int arrowLeft = 0, arrowRight = 0;
            if (node.left != null && node.left.val == node.val){
                arrowLeft += left + 1;
            }
            if (node.right != null && node.right.val == node.val) {
                arrowRight += right + 1;
            }
            ans = Math.max(ans, arrowLeft + arrowRight);
            return  Math.max(arrowLeft,arrowRight);
        }
    }
/*  https://leetcode.com/articles/longest-univalue-path/
Intuition

We can think of any path (of nodes with the same values) as up to two arrows extending from it's root.

Specifically, the root of a path will be the unique node such that the parent of that node does not appear in the path, and an arrow will be a path where the root only has one child node in the path.

Then, for each node, we want to know what is the longest possible arrow extending left, and the longest possible arrow extending right? We can solve this using recursion.

Algorithm

Let arrow_length(node) be the length of the longest arrow that extends from the node. That will be 1 + arrow_length(node.left) if node.left exists and has the same value as node. Similarly for the node.right case.

While we are computing arrow lengths, each candidate answer will be the sum of the arrows in both directions from that node. We record these candidate answers and return the best one.
 */

//-----------------------------------------------------------------------------
    //mine
    /*
    Divide and Conquer， postorder
     */
class Solution2 {


    int ans;
    public int longestUnivaluePath(TreeNode root){
        ans = 0;
        arrowLength(root);
        return ans;
    }
    //  the length of the longest arrow that extends from the node. 不算当前node。
    private  int arrowLength(TreeNode node){
        if(node == null) return 0;

        int left = arrowLength(node.left);
        int right = arrowLength(node.right);

        int arrowLeft = node.left != null && node.left.val == node.val ? left + 1:0;
        int arrowRight = node.right != null &&  node.right.val == node.val ? right + 1:0;


        ans = Math.max(ans, arrowLeft + arrowRight);
        return  Math.max(arrowLeft,arrowRight);
    }
}


    @Test
    public void test02(){
        int[] arr = {5,4,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println("root: ");
        root.print();
        System.out.println(new Solution2().longestUnivaluePath(root));
    }
    /*
    root:
   5
  / \
 /   \
 4   5
/ \   \
1 1   5

2
     */
    @Test
    public void test022(){
        int[] arr = {5,4,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println("root: ");
        root.print();
        System.out.println(new Solution2().longestUnivaluePath(root));
    }

/*
root:
   5
  / \
 /   \
 4   5
/ \   \
4 4   5

2
 */

    @Test
    public void test0222(){
        int[] arr = {5,4,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(4);
        root.left.right.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println("root: ");
        root.print();
        System.out.println(new Solution2().longestUnivaluePath(root));
    }
//-----------------------------------------------------------------------------

/*    [Java/C++] Clean Code
    Longest-Univalue-Path of a tree is among those Longest-Univalue-Path-Across at each node;
    Longest-Univalue-Path-Across a node is sum of { Longest-Univalue-Path-Start-At each child with same value, + 1}

    Java*/

    class Solution3 {
        public int longestUnivaluePath(TreeNode root) {
            int[] res = new int[1];
            if (root != null) dfs(root, res);
            return res[0];
        }

        private int dfs(TreeNode node, int[] res) {
            int l = node.left != null ? dfs(node.left, res) : 0;
            int r = node.right != null ? dfs(node.right, res) : 0;
            int resl = node.left != null && node.left.val == node.val ? l + 1 : 0;
            int resr = node.right != null && node.right.val == node.val ? r + 1 : 0;
            res[0] = Math.max(res[0], resl + resr);
            return Math.max(resl, resr);
        }
    }
//-----------------------------------------------------------------------------
    class Solution4{
        int len = 0; // global variable
        public int longestUnivaluePath(TreeNode root) {
            if (root == null) return 0;
            len = 0;
            getLen(root, root.val);
            return len;
        }

        private int getLen(TreeNode node, int val) {
            if (node == null) return 0;
            int left = getLen(node.left, node.val);
            int right = getLen(node.right, node.val);
            len = Math.max(len, left + right);
            if (val == node.val)  return Math.max(left, right) + 1;
            return 0;
        }
    }
//-----------------------------------------------------------------------------
}
/*
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2


Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output:

2
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.


 */
