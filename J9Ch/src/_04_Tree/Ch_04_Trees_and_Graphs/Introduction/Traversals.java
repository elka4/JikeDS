package _04_Tree.Ch_04_Trees_and_Graphs.Introduction;

import org.junit.Test;
import CtCILibrary.*;

public class Traversals {
	public static void visit(TreeNode node) {
		if (node != null) {
			System.out.println(node.data);
		}
	}
	
	public static void inOrderTraversal(TreeNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			visit(node);
			inOrderTraversal(node.right);
		}
	}
	
	public static void preOrderTraversal(TreeNode node) {
		if (node != null) {
			visit(node);
			inOrderTraversal(node.left);
			inOrderTraversal(node.right);
		}
	}
	
	public static void postOrderTraversal(TreeNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			inOrderTraversal(node.right);
			visit(node);
		}
	}	
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		// We needed this code for other files, so check out the code in the library
		TreeNode root = TreeNode.createMinimalBST(array);
		root.print();
		inOrderTraversal(root);
	}


	@Test
    public void test01(){
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // We needed this code for other files, so check out the code in the library
        TreeNode root = TreeNode.createMinimalBST(array);
        root.print();
        preOrderTraversal(root);
        root.left.print();
        preOrderTraversal(root.left);
        root.right.print();
        preOrderTraversal(root.right);
    }

    @Test
    public void test02(){
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // We needed this code for other files, so check out the code in the library
        TreeNode root = TreeNode.createMinimalBST(array);
        root.print();
        postOrderTraversal(root);
    }

    @Test
    public void test03(){
        int[] array = {1, 2, 3,4};

        // We needed this code for other files, so check out the code in the library
        TreeNode root = TreeNode.createMinimalBST(array);
        root.print();
        //preOrderTraversal(root);
        //inOrderTraversal(root);
        postOrderTraversal(root);


    }

}
