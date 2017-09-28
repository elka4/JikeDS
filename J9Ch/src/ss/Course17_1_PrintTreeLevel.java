package ss;

import org.junit.Test;


/**
打印每个节点所在的层数
 */
public class Course17_1_PrintTreeLevel {
	public void printLevel(Course17_TreeNode root) {
		preOrder(root, 1);
	}
	public void preOrder(Course17_TreeNode p, int level) {
		if (p != null) {
			System.out.println(p.val + " " + level);
			preOrder(p.left, level + 1);
			preOrder(p.right, level + 1);
		}
	}
	@Test
	public void test() {
		Integer[] array = {1, 2, 3, 4, 5, null, 6, null, null, null, 7, 8};
		Course17_TreeNode root = Course17_CreateBinaryTree.arrayToBinaryTree(array);
		preOrder(root, 1);
	}
}
