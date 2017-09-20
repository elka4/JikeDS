package _4_Tree.dfs_bfs;

import lib.TreeNode;

/*
LeetCode – Balanced Binary Tree (Java)

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Analysis

This is a typical tree problem that can be solve by using recursion.
 */


public class Binary_Tree_Paths {


    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        if (getHeight(root) == -1)
            return false;

        return true;
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if (left == -1 || right == -1)
            return -1;

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;

    }

//////////////////////////////////////////////////////////

    //Java solution based on height, check left and right node in every recursion to avoid further useless search

    class Solution2{
        public boolean isBalanced(TreeNode root) {
            if(root==null){
                return true;
            }
            return height(root)!=-1;

        }
        public int height(TreeNode node){
            if(node==null){
                return 0;
            }
            int lH=height(node.left);
            if(lH==-1){
                return -1;
            }
            int rH=height(node.right);
            if(rH==-1){
                return -1;
            }
            if(lH-rH<-1 || lH-rH>1){
                return -1;
            }
            return Math.max(lH,rH)+1;
        }
    }


//////////////////////////////////////////////////////////
    /*        Accepted O(n) solution
        We determine recursively the height of the root node but when the recursion is coming upwards we return UNBALANCED instead of the actual height if we know that the tree is already known to be unbalanced.

        We visit each node just once thus it has linear time complexity.*/

    class Solution3{

        private static final int UNBALANCED = -99;

        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            return getHeight(root) != UNBALANCED;
        }

        private int getHeight(TreeNode root) {
            if (root == null) {
                return -1;
            }
            int l = getHeight(root.left);
            int r = getHeight(root.right);
            if (l == UNBALANCED || r == UNBALANCED || Math.abs(l-r) > 1) {
                return UNBALANCED;
            }
            return 1 + Math.max(l,r);
        }
    }


//////////////////////////////////////////////////////////

    //JAVA O(n) solution based on Maximum Depth of Binary Tree

    public class Solution4 {
        private boolean result = true;

        public boolean isBalanced(TreeNode root) {
            maxDepth(root);
            return result;
        }

        public int maxDepth(TreeNode root) {
            if (root == null)
                return 0;
            int l = maxDepth(root.left);
            int r = maxDepth(root.right);
            if (Math.abs(l - r) > 1)
                result = false;
            return 1 + Math.max(l, r);
        }
    }




//////////////////////////////////////////////////////////

    //Accepted O(n) solution

    /*
    We determine recursively the height of the root node but when the recursion is coming upwards we return UNBALANCED instead of the actual height if we know that the tree is already known to be unbalanced.

We visit each node just once thus it has linear time complexity.
     */

    class Solution5{
        private static final int UNBALANCED = -99;

        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            return getHeight(root) != UNBALANCED;
        }

        private int getHeight(TreeNode root) {
            if (root == null) {
                return -1;
            }
            int l = getHeight(root.left);
            int r = getHeight(root.right);
            if (l == UNBALANCED || r == UNBALANCED || Math.abs(l-r) > 1) {
                return UNBALANCED;
            }
            return 1 + Math.max(l,r);
        }
    }


//////////////////////////////////////////////////////////

    //Can we have a better solution

    public class Solution6 {

        int height(TreeNode root) {
            if(root==null) return 0;
            int lh=height(root.left);
            int rh=height(root.right);
            return 1+(lh>rh ?lh :rh);
        }
        public boolean isBalanced(TreeNode root) {
            if(root==null) return true;
            int lh=height(root.left);
            int rh=height(root.right);

            return (rh>lh ?(1>=(rh-lh)) :(1>=(lh-rh))) && isBalanced(root.left) && isBalanced(root.right);

        }}

//////////////////////////////////////////////////////////

    //This is the way to avoid 2 recursive method calls. The idea is exactly same as 2 above.

    public class Solution7
    {
        public boolean isBalanced(TreeNode root)
        {
            if (root == null)
                return true;

            int[] num = new int[1];
            num[0] = 0;

            checkHeight(root, num);

            return num[0] == 0;
        }

        private int checkHeight(TreeNode p, int[] num)
        {
            if (num[0] == 1)
                return 0;

            if (p.left == null && p.right == null)
            {
                return 1;
            }

            int leftDepth = 1;
            if (p.left != null)
                leftDepth = checkHeight(p.left, num) + 1;


            int rightDepth = 1;
            if (p.right != null)
                rightDepth = checkHeight(p.right, num) + 1;


            if ( Math.abs(rightDepth - leftDepth) > 1)
            {
                num[0] = 1;
            }

            return Math.max(leftDepth, rightDepth);
        }
    }
//////////////////////////////////////////////////////////

   // i use the Maximum Depth of Binary Tree code and change a bit,solve the problem with Single Recursion

    public class Solution8 {
        public boolean isBalanced=true;

        public boolean isBalanced(TreeNode root) {
            maxDepth(root);
            return isBalanced;
        }

        public int maxDepth(TreeNode root) {
            if(root==null)
                return 0;
            else{
                int leftDepth=maxDepth(root.left)+1;
                int rightDepth=maxDepth(root.right)+1;
                if(Math.abs(leftDepth-rightDepth)>1)
                    isBalanced=false;
                return leftDepth>rightDepth?(leftDepth):(rightDepth);
            }
        }
    }


//////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////

}
