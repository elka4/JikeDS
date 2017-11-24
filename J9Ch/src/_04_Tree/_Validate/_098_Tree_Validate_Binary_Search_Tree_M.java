package _04_Tree._Validate;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  98. Validate Binary Search Tree
//  https://leetcode.com/problems/validate-binary-search-tree/description/
//  http://www.lintcode.com/zh-cn/problem/validate-binary-search-tree/
public class _098_Tree_Validate_Binary_Search_Tree_M {
    // 9Ch

    // version 1 Traverse
    private int lastVal = Integer.MIN_VALUE;
    private boolean firstNode = true;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;

        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }

    @Test
    public void test01(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.print();
        root.right.setLeftChild(new TreeNode(3));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST(root));
    }

//------------------------------------------------------------------------------////////////
    // 9Ch

    // version 2  Divide and Conquer, with result type
    class ResultType {
        boolean is_bst;
        int maxValue, minValue;

        ResultType(boolean is_bst, int maxValue, int minValue) {
            this.is_bst = is_bst;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }

    }

    public boolean isValidBST2(TreeNode root) {
        ResultType r = validateHelper(root);
        return r.is_bst;
    }

    private ResultType validateHelper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ResultType left = validateHelper(root.left);
        ResultType right = validateHelper(root.right);

        if (!left.is_bst || !right.is_bst) {
            // if is_bst is false then minValue and maxValue are useless
            return new ResultType(false, 0, 0);
        }

        if (root.left != null && left.maxValue >= root.val ||
                root.right != null && right.minValue <= root.val) {
            return new ResultType(false, 0, 0);
        }

        return new ResultType(true,
                Math.max(root.val, right.maxValue),
                Math.min(root.val, left.minValue));
    }

    @Test
    public void test02(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.print();
        root.right.setLeftChild(new TreeNode(3));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST2(root));
    }

//------------------------------------------------------------------------------////////////
    // 9Ch

    // version 3  Divide and Conquer
    public boolean isValidBST3(TreeNode root) {
        // write your code here
        return divConq(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean divConq(TreeNode root, long min, long max){
        if (root == null){
            return true;
        }
        if (root.val <= min || root.val >= max){
            return false;
        }
        return divConq(root.left, min, Math.min(max, root.val)) &&
                divConq(root.right, Math.max(min, root.val), max);
    }

    @Test
    public void test03(){
        int[] arr = {2,1,4};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.right.setLeftChild(new TreeNode(3));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST3(root));
    }

//------------------------------------------------------------------------------////////////

    public boolean isValidBST11(TreeNode root) {
        if(root == null)
            return true;
        return isValidBST(root, Long.MAX_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root,  long min, long max) {
        if(root == null)
            return true;
        //current level: check root.val
        if(root.val >= max || root.val <= min)
            return false;
        //recurse down
        return isValidBST(root.left,  min, root.val) &&
                isValidBST(root.right, root.val, max);
    }

    @Test
    public void test011() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST(root));

    }
    @Test
    public void test022() {
        int[] arr = {2,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST(root));

    }
//------------------------------------------------------------------------------////////////

    /*Java Solution 1 - Recursive
    All values on the left sub tree must be less than root, and all values on the
     right sub tree must be greater than root. So we just check the boundaries for each node.*/

    public boolean isValidBST4(TreeNode root) {
        return isValidBST4(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public boolean isValidBST4(TreeNode p, double min, double max) {
        if (p == null)
            return true;

        if (p.val <= min || p.val >= max)
            return false;

        return isValidBST4(p.left, min, p.val) && isValidBST4(p.right, p.val, max);
    }

    /*This solution also goes to the left subtree first. If the violation occurs close
    to the root but on the right subtree, the method still cost O(n).
    The second solution below can handle violations close to root node faster.

    This solution can also be written as the following:*/

    public boolean isValidBST5(TreeNode root) {
        if(root==null)
            return true;

        return helper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public boolean helper(TreeNode root, double low, double high){

        if(root.val<=low || root.val>=high)
            return false;

        if(root.left!=null && !helper(root.left, low, root.val)){
            return false;
        }

        if(root.right!=null && !helper(root.right, root.val, high)){
            return false;
        }

        return true;
    }

//------------------------------------------------------------------------------

    //Java Solution 2 - Iterative
    public class Solution {
        public boolean isValidBST(TreeNode root) {
            if(root == null)
                return true;

            LinkedList<BNode> queue = new LinkedList<BNode>();
            queue.add(new BNode(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
            while(!queue.isEmpty()){
                BNode b = queue.poll();
                if(b.n.val <= b.left || b.n.val >=b.right){
                    return false;
                }
                if(b.n.left!=null){
                    queue.offer(new BNode(b.n.left, b.left, b.n.val));
                }
                if(b.n.right!=null){
                    queue.offer(new BNode(b.n.right, b.n.val, b.right));
                }
            }
            return true;
        }
    }

    //define a BNode class with TreeNode and it's boundaries
    class BNode{
        TreeNode n;
        double left;
        double right;
        public BNode(TreeNode n, double left, double right){
            this.n = n;
            this.left = left;
            this.right = right;
        }
    }

//------------------------------------------------------------------------------

    //My simple Java solution in 3 lines
    public class Solution4 {
        public boolean isValidBST4(TreeNode root) {

            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
            if (root == null) return true;
            if (root.val >= maxVal || root.val <= minVal) return false;
            return isValidBST(root.left, minVal, root.val) &&
                    isValidBST(root.right, root.val, maxVal);
        }
    }

//------------------------------------------------------------------------------

    //Learn one iterative inorder traversal, apply it to multiple tree questions (Java Solution)
    //I will show you all how to tackle various tree questions using iterative inorder traversal.
    // First one is the standard iterative inorder traversal using stack.
    // Hope everyone agrees with this solution.

    public List<Integer> inorderTraversal5(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;

        }
        return list;
    }

//------------------------------------------------------------------------------

    //My java inorder iteration solution
    //the idea is to do a inorder Traversal and keep the value of the
    public boolean isValidBST6 (TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode> ();
        TreeNode cur = root ;
        TreeNode pre = null ;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left ;
            } else {
                TreeNode p = stack.pop() ;
                if (pre != null && p.val <= pre.val) {
                    return false ;
                }
                pre = p ;
                cur = p.right ;
            }
        }
        return true ;
    }

//------------------------------------------------------------------------------

    //Another passed Java solution
    public class Solution7 {
        public boolean isValidBST(TreeNode root) {

            return helper(root, null, null);
        }

        boolean helper(TreeNode root, Integer min, Integer max) {
            if (root == null)
                return true;

            if ((min != null && root.val <= min) || (max != null && root.val >= max))
                return false;

            return helper(root.left, min, root.val) && helper(root.right, root.val, max);
        }
    }


//------------------------------------------------------------------------------

    //1 ms Java Solution using Recursion
    public class Solution8 {
        public boolean isValidBST(TreeNode root) {

            return isValid(root, null, null);
        }

        public boolean isValid(TreeNode root, Integer min, Integer max) {
            if(root == null) return true;
            if(min != null && root.val <= min) return false;
            if(max != null && root.val >= max) return false;

            return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
        }
    }

//------------------------------------------------------------------------------

    //1ms Java solution, O(n) time and O(1) space, using Integer object and null pointer
    public class Solution9 {
        public boolean isValidBST(TreeNode root) {

            return isValidBSTHelper(root, null, null);
        }

        private boolean isValidBSTHelper(TreeNode root, Integer leftBound, Integer rightBound) {
            // recursively pass left and right bounds from higher level to lower level
            if (root == null) {
                return true;
            }
            if (leftBound != null && root.val <= leftBound ||
                    rightBound != null && root.val >= rightBound) {
                return false;
            }
            return isValidBSTHelper(root.left, leftBound, root.val) &&
                    isValidBSTHelper(root.right, root.val, rightBound);
        }
    }

//------------------------------------------------------------------------------////////////

    public class Solution0 {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE); }
        public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
            if (root == null) return true;
            if (root.val >= maxVal || root.val <= minVal) return false;
            return isValidBST(root.left, minVal, root.val) && isValidBST(root.right,
                    root.val, maxVal);
        }
    }

//------------------------------------------------------------------------------////////////
//------------------------------------------------------------------------------////////////
//------------------------------------------------------------------------------////////////
/*Learn one iterative inorder traversal, apply it to multiple tree questions (Java Solution)
    I will show you all how to tackle various tree questions using iterative inorder traversal. First one is the standard iterative inorder traversal using stack. Hope everyone agrees with this solution.

            Question : Binary Tree Inorder Traversal*/

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;

        }
        return list;
    }
/*    Now, we can use this structure to find the Kth smallest element in BST.

            Question : Kth Smallest Element in a BST*/

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) break;
            root = root.right;
        }
        return root.val;
    }
/*
    We can also use this structure to solve BST validation question.

    Question : Validate Binary Search Tree
*/

    public boolean isValidBST_(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

//------------------------------------------------------------------------------////////////
//------------------------------------------------------------------------------////////////
//------------------------------------------------------------------------------////////////
}
/*
验证二叉查找树

 描述
 笔记
 数据
 评测
给定一个二叉树，判断它是否是合法的二叉查找树(BST)

一棵BST定义为：

节点的左子树中的值要严格小于该节点的值。
节点的右子树中的值要严格大于该节点的值。
左右子树也必须是二叉查找树。
一个节点的树也是二叉查找树。
样例
一个例子：

  2
 / \
1   4
   / \
  3   5
上述这棵二叉树序列化为 {2,1,4,#,#,3,5}.

标签
 */

/*
LeetCode – Validate Binary Search Tree (Java)

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.

 */