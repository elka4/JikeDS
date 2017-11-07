package _04_Tree._LCS;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;


//  298. Binary Tree Longest Consecutive Sequence
//  https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/
//
public class _298_Tree_Binary_Tree_Longest_Consecutive_Sequence_M {

    private int max1 = 0;
    public int longestConsecutive1(TreeNode root) {
        if(root == null) return 0;
        helper1(root, 0, root.val);
        return max1;
    }

    public void helper1(TreeNode root, int cur, int target){
        if(root == null) return;
        if(root.val == target) cur++;
        else cur = 1;
        max1 = Math.max(cur, max1);
        helper1(root.left, cur, root.val + 1);
        helper1(root.right, cur, root.val + 1);
    }


///////////////////////////////////////////////////////////////////////////////////
    public int longestConsecutive2(TreeNode root) {
        int[] lens = new int[1];
        if (root == null)  return 0;
        helper2(root, 0, lens, root.val);
        return lens[0];
    }

    private void helper2(TreeNode root, int cur, int[] cnt, int target) {
        if (root == null)  return;
        if (root.val == target)  cur++;
        else  cur = 1;
        cnt[0] = Math.max(cur,cnt[0]);
        helper2(root.left, cur, cnt, root.val+1);
        helper2(root.right, cur, cnt, root.val+1);
    }


///////////////////////////////////////////////////////////////////////////////////
    public int longestConsecutive3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return DFS3(root, root.val + 1, 1, 1);
    }

    private int DFS3(TreeNode node, int target, int curr, int max) {
        if (node == null) {
            return max;
        }
        if (node.val == target) {
            curr++;
            max = Math.max(max, curr);
        } else {
            curr = 1;
        }
        return Math.max(DFS3(node.left, node.val + 1, curr, max),
                DFS3(node.right, node.val + 1, curr, max));
    }
///////////////////////////////////////////////////////////////////////////////////

    // Simple Recursive DFS without global variable
    public int longestConsecutive4(TreeNode root) {
        return (root==null)? 0 : Math.max(dfs(root.left, 1, root.val),
                dfs(root.right, 1, root.val));
    }

    public int dfs(TreeNode root, int count, int val){
        if(root==null) return count;
        count = (root.val - val == 1)?count+1:1;
        int left = dfs(root.left, count, root.val);
        int right = dfs(root.right, count, root.val);
        return Math.max(Math.max(left, right), count);
    }


///////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
    // version 1: Traverse + Divide Conquer

    public int longestConsecutive(TreeNode root) {

        return helper(root, null, 0);
    }

    private int helper(TreeNode root, TreeNode parent, int lengthWithoutRoot) {
        if (root == null) {
            return 0;
        }

        //从上往下算，一开始给root为1，符合条件就加1，不符合条件就重设为1
        int length = (parent != null && parent.val + 1 == root.val)?
                lengthWithoutRoot + 1
                : 1;

        int left = helper(root.left, root, length);
        int right = helper(root.right, root, length);

        return Math.max(length, Math.max(left, right));
    }

/*    private int helper(TreeNode root, TreeNode parent, int lengthWithoutRoot) {
        if (root == null) {
            return 0;
        }
        //从上往下算，一开始给root为1，符合条件就加1，不符合条件就重设为1
        int length = (parent != null && parent.val + 1 == root.val)?
                lengthWithoutRoot + 1
                : 1;
        System.out.println("root: " + root.val + ". length: " + length);

        int left = helper(root.left, root, length);

        if(root.left != null ){
            root.left.print();
            System.out.println("left: " + left);
        }
        else System.out.println("root.left is null. " + "left " + left);

        int right = helper(root.right, root, length);

        if(root.right != null ){
            root.right.print();
            System.out.println("right: " + right);
        }
        else System.out.println("root.right is null. " + "right " + right);

        return Math.max(length, Math.max(left, right));
    }*/

    @Test
    public void test01() {
        TreeNode root = new TreeNode(1);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(2));
        root.right.setRightChild(new TreeNode(4));
        root.right.right.setRightChild(new TreeNode(5));

        root.print();
        System.out.println(longestConsecutive(root));
    }


///////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
    // version 2: Another Traverse + Divide Conquer
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    private int longest;
    public int longestConsecutive5(TreeNode root) {
        longest = 0;
        helper5(root);
        return longest;
    }

    private int helper5(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper5(root.left);
        int right = helper5(root.right);

        int subtreeLongest = 1; // at least we have root

        if (root.left != null && root.val + 1 == root.left.val) {
            subtreeLongest = Math.max(subtreeLongest, left + 1);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            subtreeLongest = Math.max(subtreeLongest, right + 1);
        }

        if (subtreeLongest > longest) {
            longest = subtreeLongest;
        }
        return subtreeLongest;
    }

    @Test
    public void test03() {
        TreeNode root = new TreeNode(1);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(2));
        root.right.setRightChild(new TreeNode(4));
        root.right.right.setRightChild(new TreeNode(5));

        root.print();
        System.out.println(longestConsecutive2(root));
    }

    @Test
    public void test04() {
        TreeNode root = new TreeNode(2);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(5));
        root.right.left.setRightChild(new TreeNode(1));

        root.print();
        System.out.println(longestConsecutive2(root));
    }

///////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
    // version 3: Divide Conquer
    private class ResultType {
        int maxInSubtree;
        int maxFromRoot;
        public ResultType(int maxInSubtree, int maxFromRoot) {
            this.maxInSubtree = maxInSubtree;
            this.maxFromRoot = maxFromRoot;
        }
    }
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive6(TreeNode root) {
        System.out.println("maxInSubtree "+helper6(root).maxInSubtree);
        System.out.println("maxFromRoot "+helper6(root).maxFromRoot);
        return helper6(root).maxInSubtree;
    }


    private ResultType helper6(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }

        ResultType left = helper6(root.left);
        ResultType right = helper6(root.right);

        // 1 is the root itself.
        ResultType result = new ResultType(0, 1);

        if (root.left != null && root.val + 1 == root.left.val) {
            result.maxFromRoot = Math.max(
                    result.maxFromRoot,
                    left.maxFromRoot + 1
            );
        }

        if (root.right != null && root.val + 1 == root.right.val) {
            result.maxFromRoot = Math.max(
                    result.maxFromRoot,
                    right.maxFromRoot + 1
            );
        }

        result.maxInSubtree = Math.max(
                result.maxFromRoot,
                Math.max(left.maxInSubtree, right.maxInSubtree)
        );

        return result;
    }

    @Test
    public void test05() {
        TreeNode root = new TreeNode(1);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(2));
        root.right.setRightChild(new TreeNode(4));
        root.right.right.setRightChild(new TreeNode(5));

        root.print();
        System.out.println(longestConsecutive3(root));
    }

    @Test
    public void test06() {
        TreeNode root = new TreeNode(2);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(5));
        root.right.left.setRightChild(new TreeNode(1));

        root.print();
        System.out.println(longestConsecutive3(root));
    }

///////////////////////////////////////////////////////////////////

    // Approach #1 (Top Down Depth-first Search) [Accepted]
    /*
    Algorithm

    A top down approach is similar to an in-order traversal.
     We use a variable length to store the current consecutive
     path length and pass it down the tree. As we traverse,
     we compare the current node with its parent node to determine
     if it is consecutive.
      If not, we reset the length.
     */
    private int maxLength7 = 0;
    public int longestConsecutive7(TreeNode root) {
        dfs7(root, null, 0);
        return maxLength7;
    }

    private void dfs7(TreeNode p, TreeNode parent, int length) {
        if (p == null) return;
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        maxLength7 = Math.max(maxLength7, length);
        dfs7(p.left, p, length);
        dfs7(p.right, p, length);
    }
    /*
    @lightmark presents a neat approach without storing the maxLength
     as a global variable.
     */

    public int longestConsecutive8(TreeNode root) {

        return dfs8(root, null, 0);
    }

    private int dfs8(TreeNode p, TreeNode parent, int length) {
        if (p == null) return length;

        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;

        return Math.max(length,
                Math.max(dfs8(p.left, p, length), dfs8(p.right, p, length)));
    }

    /*
    Complexity analysis

    Time complexity : O(n)O(n). The time complexity is the same as an in-order
    traversal of a binary tree with nn nodes.

    Space complexity : O(n)O(n). The extra space comes from implicit stack
    space due to recursion. For a skewed binary tree, the recursion could go
     up to nn levels deep.
     */

///////////////////////////////////////////////////////////////////

    //Approach #2 (Bottom Up Depth-first Search) [Accepted]

    // Algorithm The bottom-up approach is similar to a post-order traversal.
    // We return the consecutive path length starting at current node to its parent.
    // Then its parent can examine if its node value can be included in this
    // consecutive path.

    private int maxLength9 = 0;
    public int longestConsecutive9(TreeNode root) {
        dfs9(root);
        return maxLength9;
    }

    private int dfs9(TreeNode p) {
        if (p == null) return 0;

        int L = dfs9(p.left) + 1;
        int R = dfs9(p.right) + 1;

        if (p.left != null && p.val + 1 != p.left.val) {
            L = 1;
        }

        if (p.right != null && p.val + 1 != p.right.val) {
            R = 1;
        }

        int length = Math.max(L, R);

        maxLength9 = Math.max(maxLength9, length);

        return length;
    }

    /*
    Complexity analysis

Time complexity : O(n)O(n). The time complexity is the same as a post-order
 traversal in a binary tree, which is O(n)O(n).

Space complexity : O(n)O(n). The extra space comes from implicit stack
 space due to recursion.
For a skewed binary tree, the recursion could go up to nn levels deep.
     */


    /*
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
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
    // BitTigger
    public int longestConsecutive10(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper10(root, 1, Integer.MAX_VALUE);
    }
    private int helper10(TreeNode root, int curLen, int lastValue) {
        if (root == null)
            return curLen;

        //subproblem 1: ends at cur
        if (root.val == lastValue + 1) {
            curLen++;
        } else {
            curLen = 1;
        }

        //subproblem 2&3: ends at left || right
        int left = helper10(root.left, curLen, root.val);
        int right = helper10(root.right, curLen, root.val);

        return Math.max(Math.max(left, right), curLen);
    }

    @Test
    public void test10() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.right.right = new TreeNode(9);
        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive10(root));
    }


////////////////////////////////////////////////////////////////////

    //传统做法，但使用了全局变量
    private int max11 = 0;//global变量

    public void helper11(TreeNode root, int last, int count){
        //Base case
        if(root == null){
            System.out.println("root == null");
            max11 = Integer.max(max, count);
            return;
        }

        System.out.println("last " + last);
        System.out.println("count " + count);

        //curretn level
        if(root.val == last + 1){
            System.out.println("root.val == last + 1" );
            count++;
        }else{
            System.out.println("max = Integer.max(max, count);");
            max11 = Integer.max(max11, count);
        }

        //next level
        helper11(root.left, root.val, count);
        helper11(root.right, root.val, count);
    }

    public int longestConsecutive11(TreeNode root){
        if(root == null){
            return 0;
        }
        helper11(root, Integer.MIN_VALUE, 1);
        return max;
    }


    @Test
    public void test0222() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.right.right = new TreeNode(9);
        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive2(root));
    }
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
    //Java Solution 1 - Queue

    public int longestConsecutive12(TreeNode root) {
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

    int max13=0;

    public int longestConsecutive13(TreeNode root) {
        helper13(root);
        return max13;
    }

    public int helper13(TreeNode root) {
        if(root==null)
            return 0;

        int l = helper13(root.left);
        int r = helper13(root.right);

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

        max13 = Math.max(max13, fromLeft);
        max13 = Math.max(max13, fromRight);

        return Math.max(fromLeft, fromRight);
    }


///////////////////////////////////////////////////////////////////

    //Approach #1 (Top Down Depth-first Search) [Accepted]

    //

    private int maxLength3 = 0;
    public int longestConsecutive333(TreeNode root) {
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
    The bottom-up approach is similar to a post-order traversal. We return the consecutive
    path length starting at current node to its parent. Then its parent can examine if its
    node value can be included in this consecutive path.*/

    private int maxLength = 0;
    public int longestConsecutive44(TreeNode root) {
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

    Time complexity : O(n)O(n). The time complexity is the same as a post-order
    traversal in a binary tree, which is O(n)O(n).

    Space complexity : O(n)O(n). The extra space comes from implicit stack space
    due to recursion. For a skewed binary tree, the recursion could go up to nn levels deep.*/

///////////////////////////////////////////////////////////////////

    /*Easy Java DFS, is there better time complexity solution?
    Just very intuitive depth-first search, send cur node value to the next level
    and compare it with the next level node.*/
    private int max = 0;

    public int longestConsecutive55(TreeNode root) {
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
    public int longestConsecutive66(TreeNode root) {
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




    @Test
    public void test0111(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right.right = new TreeNode(5);
        root.print();
        System.out.println(longestConsecutive66(root));

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.left = new TreeNode(2);
        root2.right.left.left = new TreeNode(1);
        root2.print();
        System.out.println(longestConsecutive66(root2));
    }
    /*
           1
        \
         \
          \
           \
           3
          / \
         /   \
         2   4
              \
              5

3
       2
        \
         \
          \
           \
           3
          /
         /
         2
        /
        1

2

     */
//////////////////////////////////////////////////////////////

}
/*
给一棵二叉树，找到最长连续路径的长度。
这条路径是指 任何的节点序列中的起始节点到树中的任一节点都必须遵循 父-子 联系。最长的连续路径必须是从父亲节点到孩子节点（不能逆序）。
 */

/*  leet 298. Binary Tree Longest Consecutive Sequence

LeetCode – Binary Tree Longest Consecutive Sequence (Java)

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 */

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