package _04_Tree.Subtree;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

//find the number of nodes of the largest BST subtree
/*
LeetCode – Largest BST Subtree (Java)

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 */
public class Largest_BST_SubTree {
	 public int largestBSTSubtree(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }

	    if (isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
	        return count(root);
	    }
	    return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
	}

	public boolean isValid(TreeNode root, Integer min, Integer max) {
	    if (root == null) {
	        return true;
	    }
	    if (root.val >= max || root.val <= min) {
	        return false;
	    }

	    return isValid(root.left, min, root.val)
                && isValid(root.right, root.val, max);
	}

	public int count(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }

	    return count(root.left) + count(root.right) + 1;
	}

    @Test
    public void test01(){
        int[] arr = {5,3,6,1,4,7,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(largestBSTSubtree(root));
    }

    @Test
    public void test02(){
        int[] arr = {5,3,6,1,4,7,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(largestBSTSubtree(root));
    }

////////////////////////////////////////////////////////////////////////////

    //这个算法有问题，不能返回subtree的正确node数
    //有可能是 //int[] max = {0};

    class Result {
        int size;
        int min;
        int max;

        public Result (int size, int min, int max) {
            this.size = size;
            this.min = min;
            this.max = max;
        }
        public String toString(){
            return "Result: " + "size  "+ size + " min  "+ min + " max  "+ max;
        }
    }

    public int largestBSTSubtree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = {0};
        Result res = helper(root, max);
        return max[0];
    }

    private Result helper(TreeNode root, int[] max) {
        if (root == null) {
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Result left = helper(root.left, max);
        Result right = helper(root.right, max);

        root.print();
        System.out.println("root: " + root.val);
        System.out.println("left: " + left);
        System.out.println("right: " + right);


        if (left.size == -1 || right.size == -1 ||
                root.val <= left.max || root.val >= right.min) {

            System.out.println("left.size == -1 || right.size == -1 ||");

            return new Result(-1, 0, 0);
        }

        max[0] = Math.max(max[0], left.size + right.size + 1);

        return new Result(left.size + right.size + 1,
                Math.min(left.min, root.val), Math.max(root.val, right.max));
    }

    @Test
    public void test03(){
        //int[] arr = {5,3,6,1,4,8};
        int[] arr = {5,2,7,1,3,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println("largestBSTSubtree2 " + largestBSTSubtree2(root));
    }

    @Test
    public void test04(){
        int[] arr = {5,3,6,1,4,8};

        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println("largestBSTSubtree2 " + largestBSTSubtree2(root));
    }

///////////////////////////////////////////////////////////////////
    class Node {
        boolean isBST;
        int min;
        int max;
        int size;

        public Node() {
            isBST = false;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            size = 0;
        }
    }

    public int largestBSTSubtree3(TreeNode root) {
        int [] res = {0};
        helper(root, res);
        return res[0];
    }

    private Node helper2(TreeNode root, int [] res){
        Node cur = new Node();

        if(root == null){
            cur.isBST = true;
            return cur;
        }

        Node left = helper2(root.left, res);
        Node right = helper2(root.right, res);

        if(left.isBST && root.val > left.max && right.isBST && root.val < right.min){
            cur.isBST = true;

            cur.min = Math.min(root.val, left.min);
            cur.max = Math.max(root.val, right.max);

            cur.size = left.size + right.size + 1;

            if(cur.size > res[0]){
                res[0] = cur.size;
            }
        }
        return cur;
    }

    @Test
    public void test05(){
        //int[] arr = {5,3,6,1,4,8};
        int[] arr = {5,2,7,1,3,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println("largestBSTSubtree3 " + largestBSTSubtree3(root));
    }

    @Test
    public void test06(){
        int[] arr = {5,3,6,1,4,8};

        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println("largestBSTSubtree3 " + largestBSTSubtree3(root));
    }
    
////////////////////////////////////////////////////////////////////////////////////////

    class Result2 {  // (size, rangeLower, rangeUpper) -- size of current tree, range of current tree [rangeLower, rangeUpper]
        int size;
        int lower;
        int upper;

        Result2(int size, int lower, int upper) {
            this.size = size;
            this.lower = lower;
            this.upper = upper;
        }
    }

    int max = 0;

    public int largestBSTSubtree22(TreeNode root) {
        if (root == null) { return 0; }
        traverse(root);
        return max;
    }

    private Result2 traverse(TreeNode root) {
        if (root == null) { return new Result2(0, Integer.MAX_VALUE, Integer.MIN_VALUE); }
        Result2 left = traverse(root.left);
        Result2 right = traverse(root.right);
        if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
            return new Result2(-1, 0, 0);
        }
        int size = left.size + 1 + right.size;
        max = Math.max(size, max);
        return new Result2(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
    }
    
    
////////////////////////////////////////////////////////////////////////////////////////

    //Clean and easy to understand Java Solution
    public int largestBSTSubtree333(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (isValid33(root, null, null)) return countNode(root);
        return Math.max(largestBSTSubtree333(root.left), largestBSTSubtree333(root.right));
    }

    public boolean isValid33(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && min >= root.val) return false;
        if (max != null && max <= root.val) return false;
        return isValid33(root.left, min, root.val) && isValid33(root.right, root.val, max);
    }

    public int countNode(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + countNode(root.left) + countNode(root.right);
    }
    
////////////////////////////////////////////////////////////////////////////////////////

    //Java 1ms solution, by passing a three-element array up to parent

    private int largestBSTSubtreeSize = 0;
    public int largestBSTSubtree4(TreeNode root) {
        helper(root);
        return largestBSTSubtreeSize;
    }

    private int[] helper(TreeNode root) {
        // return 3-element array:
        // # of nodes in the subtree, leftmost value, rightmost value
        // # of nodes in the subtree will be -1 if it is not a BST
        int[] result = new int[3];
        if (root == null) {
            return result;
        }
        int[] leftResult = helper(root.left);
        int[] rightResult = helper(root.right);
        if ((leftResult[0] == 0 || leftResult[0] > 0 && leftResult[2] <= root.val) &&
                (rightResult[0] == 0 || rightResult[0] > 0 && rightResult[1] >= root.val)) {
            int size = 1 + leftResult[0] + rightResult[0];
            largestBSTSubtreeSize = Math.max(largestBSTSubtreeSize, size);
            int leftBoundary = leftResult[0] == 0 ? root.val : leftResult[1];
            int rightBoundary = rightResult[0] == 0 ? root.val : rightResult[2];
            result[0] = size;
            result[1] = leftBoundary;
            result[2] = rightBoundary;
        } else {
            result[0] = -1;
        }
        return result;
    }
    
////////////////////////////////////////////////////////////////////////////////////////

    class Wrapper{
        int size;
        int lower, upper;
        boolean isBST;

        public Wrapper(){
            lower = Integer.MAX_VALUE;
            upper = Integer.MIN_VALUE;
            isBST = false;
            size = 0;
        }
    }


    public int largestBSTSubtree5(TreeNode root) {
        return helper5(root).size;
    }


    public Wrapper helper5(TreeNode node){
        Wrapper curr = new Wrapper();

        if(node == null){
            curr.isBST= true;
            return curr;
        }

        Wrapper l = helper5(node.left);
        Wrapper r = helper5(node.right);

        //current subtree's boundaries
        curr.lower = Math.min(node.val, l.lower);
        curr.upper = Math.max(node.val, r.upper);

        //check left and right subtrees are BST or not
        //check left's upper again current's value and
        // right's lower against current's value
        if(l.isBST && r.isBST && l.upper<=node.val && r.lower>=node.val){
            curr.size = l.size+r.size+1;
            curr.isBST = true;
        }else{
            curr.size = Math.max(l.size, r.size);
            curr.isBST  = false;
        }

        return curr;
    }
    
    
////////////////////////////////////////////////////////////////////////////////////////

    
    
////////////////////////////////////////////////////////////////////////////////////////

    
    

}
/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \
 1   8   7
The Largest BST Subtree in this case is the highlighted one.
The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */