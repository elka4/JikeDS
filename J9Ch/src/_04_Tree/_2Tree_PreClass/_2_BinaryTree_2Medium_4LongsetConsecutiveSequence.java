package _04_Tree._2Tree_PreClass;

import lib.*;
import org.junit.Test;

public class _2_BinaryTree_2Medium_4LongsetConsecutiveSequence {
	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return helper(root, 1, Integer.MAX_VALUE);
	}
	private int helper(TreeNode root, int curLen, int lastValue) {
		if (root == null)
			return curLen;

		//subproblem 1: ends at cur
		if (root.val == lastValue + 1) {
			curLen++;
		} else {
			curLen = 1;
		}

		//subproblem 2&3: ends at left || right
		int left = helper(root.left, curLen, root.val);
		int right = helper(root.right, curLen, root.val);

		return Math.max(Math.max(left, right), curLen);
	}

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.right.right = new TreeNode(9);
        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive(root));
    }


////////////////////////////////////////////////////////////////////

	//传统做法，但使用了全局变量
	private int max = 0;//global变量

	public void helper2(TreeNode root, int last, int count){
		//Base case
		if(root == null){
            System.out.println("root == null");
            max = Integer.max(max, count);
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
            max = Integer.max(max, count);
		}

		//next level
        helper2(root.left, root.val, count);
        helper2(root.right, root.val, count);
	}

	public int longestConsecutive2(TreeNode root){
		if(root == null){
			return 0;
		}
		helper2(root, Integer.MIN_VALUE, 1);
		return max;
	}

////////////////////////////////////////////////////////////////////


    @Test
    public void test02() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.right.right = new TreeNode(9);
        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive2(root));
    }


}
