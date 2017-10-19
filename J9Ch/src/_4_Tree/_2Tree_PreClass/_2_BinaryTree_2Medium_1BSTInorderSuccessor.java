package _4_Tree._2Tree_PreClass;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;
import org.junit.Test;

//time O(logn) spaceO(1)
public class _2_BinaryTree_2Medium_1BSTInorderSuccessor {

	public TreeNode inorderSuccessor(TreeNode root, TreeNode p){
		if(root == null) {
			return null;
		}
		//Case 1: has right child
		if (p.right != null) {
			return searchLeftMost(p.right);
		} else {
			//Case 2: no right -> find the first left turing on the searching path
			return searchPar(root, p);
		}
	}

	private TreeNode searchPar(TreeNode root, TreeNode p) {
		TreeNode par = null;
		while(root != p) {
			if(p.val < root.val) {
				par = root;
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return par;
	}

	private TreeNode searchLeftMost(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}
	
//////////////////////////////////////////////////

	//mehtod 2
	public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
		TreeNode par = null;
		while (root != null){
			if (p.val < root.val) {
				par = root;
				root = root.left;
				
			} else {
				root = root.right;
			}
		}
		return par;
				
	}

//////////////////////////////////////////////////

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        inorderSuccessor(root,root.left).print();
        inorderSuccessor(root,root.right.left).print();

    }
}
