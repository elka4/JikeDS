package _4_Tree.subtree;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

//find the number of nodes of the largest BST subtree

public class _8_LargestBSTSubTree {
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


}
