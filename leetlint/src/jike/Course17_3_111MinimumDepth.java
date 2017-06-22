package jike;


/**
 * 最小深度
 */
public class  Course17_3_111MinimumDepth {
	public int min = Integer.MAX_VALUE / 2;
	public int minDepth(Course17_TreeNode root) {
		if (root == null) {
			return 0;
		}
		preOrder(root, 1);
		return min;
	}
	public void preOrder(Course17_TreeNode p, int level) {
		if (p != null) {
			if (p.left == null && p.right == null) {
				if (level < min) {
					min = level;
				}
			}
			preOrder(p.left, level + 1);
			preOrder(p.right, level + 1);
		}
	}
}
