package _04_Tree.Ch_04_Trees_and_Graphs.Q4_04_Check_Balanced;
import lib.TreeNode;

public class QuestionImproved {
		
	public static int checkHeight(TreeNode root) {
		if (root == null) {
			return -1;
		}

		int leftHeight = checkHeight(root.left);

        // Propagate error up
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

		int rightHeight = checkHeight(root.right);

        // Propagate error up
		if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		
		int heightDiff = leftHeight - rightHeight;

		if (Math.abs(heightDiff) > 1) {
			return Integer.MIN_VALUE; // Found error -> pass it back
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}
	
	public static boolean isBalanced(TreeNode root) {

	    return checkHeight(root) != Integer.MIN_VALUE;
	}
	
	public static void main(String[] args) {
		// Create balanced tree
		int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		root.print();
		
		System.out.println("Is balanced? " + isBalanced(root));
		
		root.insertInOrder(4); // Add 4 to make it unbalanced
        root.print();
		System.out.println("Is balanced? " + isBalanced(root));
	}

}
