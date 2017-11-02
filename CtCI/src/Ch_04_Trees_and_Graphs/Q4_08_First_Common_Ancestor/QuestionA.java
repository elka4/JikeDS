package Ch_04_Trees_and_Graphs.Q4_08_First_Common_Ancestor;

import CtCILibrary.TreeNode;

public class QuestionA {
	public static TreeNode commonAncestor(TreeNode p, TreeNode q) {
		if (p == q) return p;
		
		TreeNode ancestor = p;
		while (ancestor != null) {
			if (isOnPath(ancestor, q)) {
				return ancestor;
			}
			ancestor = ancestor.parent;
		}
		return null;
	}
	
	public static boolean isOnPath(TreeNode ancestor, TreeNode node) {
		while (node != ancestor && node != null) {
			node = node.parent;
		}
		return node == ancestor;
	}	
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		root.print();
		TreeNode n3 = root.find(3);
		TreeNode n7 = root.find(7);
		TreeNode ancestor = commonAncestor(n3, n7);
		System.out.println(ancestor.data);
	}

	/*
                               5
                              / \
                             /   \
                            /     \
                           /       \
                           2       8
                          / \     / \
                         /   \   /   \
                         1   3   6   9
                              \   \   \
                              4   7   10

                        5
	 */

}
