package ss;

import org.junit.Test;


/**
 * 最大深度
 */
public class Course17_3_104MaximumDepth {
	public int max = 0;
	public int maxLevel(Course17_TreeNode root) {
		preOrder(root, 1);
		return max;
	}
	public void preOrder(Course17_TreeNode p, int level) {
		if (p != null) {
			if (p.left == null && p.right == null) {
				if (level > max) {
					max = level;
				}
			}
			preOrder(p.left, level + 1);
			preOrder(p.right, level + 1);
		}
	}
	public int maxDepth(Course17_TreeNode root) {
		return root == null ? 0 : Math.max(maxDepth(root.left),
				maxDepth(root.right)) + 1;
	}
	@Test
	public void test() {
		Integer[] array = {1, 2, 3, 4, 5, null, 6, null, null, null, 7, 8};
		Course17_TreeNode root = Course17_CreateBinaryTree.arrayToBinaryTree(array);
		System.out.println(maxDepth(root));
	}
}
