package _04_Tree._Validate;
import lib.TreeNode;
import java.util.*;

//  110. Balanced Binary Tree
//  https://leetcode.com/problems/balanced-binary-tree/description/
//  http://www.lintcode.com/zh-cn/problem/balanced-binary-tree/
public class _110_Tree_Balanced_Binary_Tree_E {
    //Java solution based on height, check left and right node in
    // every recursion to avoid further useless search
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        return height(root)!=-1;
    }
    public int height(TreeNode node){
        if(node==null){ return 0;
        }
        int lH=height(node.left); if(lH==-1){
            return -1; }
        int rH=height(node.right); if(rH==-1){
            return -1; }
        if(lH-rH<-1 || lH-rH>1){ return -1;
        }
        return Math.max(lH,rH)+1;
    }

//------------------------------------------------------------------------------///////////
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
//------------------------------------------------------------------------------///////////
/*    Accepted O(n) solution
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
//------------------------------------------------------------------------------///////////

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

    //    A revision without using global value is following,
    // but your solution is definitely better performed when running:
    public class Solution5 {
        public boolean isBalanced(TreeNode root) {
            if (root == null)
                return true;
            if (Math.abs(height(root.left)-height(root.right)) <= 1)
                return (isBalanced(root.left) && isBalanced(root.right));
            return false;
        }
        public int height(TreeNode root) {
            if (root == null)
                return 0;
            int left = height(root.left);
            int right= height(root.right);
            return (Math.max(left,right)+1);

        }
    }

//------------------------------------------------------------------------------///////////

//    Easy Top Down && Bottom Up(beat 89.35%) Solutions in JAVA
//
//    Top Down Solution, which is O(n^2) time complexity
    class Solution6{
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            if (root.left == null && root.right == null) return true;
            int left = depth(root.left);
            int right = depth(root.right);
            return Math.abs(left-right) <=1 && isBalanced(root.left) && isBalanced(root.right);
        }
        public int depth(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            return Math.max(depth(root.left),depth(root.right))+1;

        }
    }


//    Bottom Up Solution, which is O(n) time complexity
    class Solution7{
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            int depth = depth(root);
            if (depth == -1) return false;
            else return true;
        }
        private int depth(TreeNode root) {
            if (root == null) return 0;
            int left = depth(root.left);
            int right = depth(root.right);
            if (left == -1 || right == -1 || Math.abs(left-right) > 1) return -1;
            return Math.max(left,right)+1;
        }
    }

//------------------------------------------------------------------------------///////////
//A Iterative PostOrder Traversal Java Solution
//
//    A recursive method is straightforward. Doing it iteratively is a lot of fun. I use postorder traversal to realize a iterative version.

    public class Solution8 {
        public boolean isBalanced(TreeNode root) {
            if(root==null) return true;
            Stack<TreeNode> stack = new Stack<TreeNode>();
            Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
            stack.push(root);
            while(!stack.isEmpty()){
                TreeNode node = stack.pop();
                if((node.left==null || node.left!=null && map.containsKey(node.left)) &&(node.right==null || node.right!=null && map.containsKey(node.right))){
                    int left = node.left==null?0:map.get(node.left);
                    int right = node.right==null?0:map.get(node.right);
                    if(Math.abs(left-right) > 1) return false;
                    map.put(node, Math.max(left, right)+1);
                }else{
                    if(node.left!=null && !map.containsKey(node.left)){
                        stack.push(node);
                        stack.push(node.left);
                    }else{
                        stack.push(node);
                        stack.push(node.right);
                    }
                }
            }
            return true;
        }
    }
//------------------------------------------------------------------------------///////////
    // 9Ch
    // Version 1: with ResultType
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */
    class ResultType {
        public boolean isBalanced;
        public int maxDepth;
        public ResultType(boolean isBalanced, int maxDepth) {
            this.isBalanced = isBalanced;
            this.maxDepth = maxDepth;
        }
    }

    public class Jiuzhang1 {
        /**
         * @param root: The root of binary tree.
         * @return: True if this Binary tree is Balanced, or false.
         */
        public boolean isBalanced(TreeNode root) {
            return helper(root).isBalanced;
        }

        private ResultType helper(TreeNode root) {
            if (root == null) {
                return new ResultType(true, 0);
            }

            ResultType left = helper(root.left);
            ResultType right = helper(root.right);

            // subtree not balance
            if (!left.isBalanced || !right.isBalanced) {
                return new ResultType(false, -1);
            }

            // root not balance
            if (Math.abs(left.maxDepth - right.maxDepth) > 1) {
                return new ResultType(false, -1);
            }

            return new ResultType(true,
                    Math.max(left.maxDepth, right.maxDepth) + 1);
        }
    }

    // Version 2: without ResultType
    public class Jiuzhang2 {
        public boolean isBalanced(TreeNode root) {
            return maxDepth(root) != -1;
        }

        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
                return -1;
            }
            return Math.max(left, right) + 1;
        }
    }
//------------------------------------------------------------------------------///////////
//------------------------------------------------------------------------------///////////
    //---------------------------------/////////////////////

    //Java solution based on height, check left and right node in every recursion to avoid further useless search

    class Solution22{
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


//---------------------------------/////////////////////
    /*        Accepted O(n) solution
        We determine recursively the height of the root node but when the recursion is coming upwards we return UNBALANCED instead of the actual height if we know that the tree is already known to be unbalanced.

        We visit each node just once thus it has linear time complexity.*/

    class Solution33{

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


//---------------------------------/////////////////////

    //JAVA O(n) solution based on Maximum Depth of Binary Tree

    public class Solution44 {
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




//---------------------------------/////////////////////

    //Accepted O(n) solution

    /*
    We determine recursively the height of the root node but when the recursion is coming upwards we return UNBALANCED instead of the actual height if we know that the tree is already known to be unbalanced.

We visit each node just once thus it has linear time complexity.
     */

    class Solution55{
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


//---------------------------------/////////////////////

    //Can we have a better solution

    public class Solution66 {

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

//---------------------------------/////////////////////

    //This is the way to avoid 2 recursive method calls. The idea is exactly same as 2 above.

    public class Solution77
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
//---------------------------------/////////////////////

    // i use the Maximum Depth of Binary Tree code and change a bit,solve the problem with Single Recursion

    public class Solution88 {
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

//------------------------------------------------------------------------------///////////
//------------------------------------------------------------------------------///////////
}
/*
平衡二叉树

 描述
 笔记
 数据
 评测
给定一个二叉树,确定它是高度平衡的。对于这个问题,一棵高度平衡的二叉树的定义是：一棵二叉树中每个节点的两个子树的深度相差不会超过1。

样例
给出二叉树 A={3,9,20,#,#,15,7}, B={3,#,20,15,7}

A)  3            B)    3
   / \                  \
  9  20                 20
    /  \                / \
   15   7              15  7
二叉树A是高度平衡的二叉树，但是B不是
 */

/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


 */