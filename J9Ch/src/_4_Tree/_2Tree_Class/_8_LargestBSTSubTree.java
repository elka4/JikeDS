package _4_Tree._2Tree_Class;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;
import org.junit.Test;

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

	    return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
	}

	public int count(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }

	    return count(root.left) + count(root.right) + 1;
	}

    @Test
    public void test01(){
        int[] arr = {5,3,7,1,4,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();

        System.out.println(largestBSTSubtree(root));
    }
}
