package _4_Tree._2Tree_PreClass;

import lib.*;
import org.junit.Test;

public class _2_BinaryTree_2Medium_3ValidBinaryTree {
	public boolean isValidBST(TreeNode root) {
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

//////////////////////////////////////////////////

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST(root));

    }
    @Test
    public void test02() {
        int[] arr = {2,1,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(isValidBST(root));

    }
}
