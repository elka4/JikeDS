package top100.Enhance._2Tree_PreClass;

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
		if (root.val == lastValue + 1) {
			curLen++;
		} else {
			curLen = 1;
		}
			
		int left = helper(root.left, curLen, root.val);
		int right = helper(root.right, curLen, root.val);
		return Math.max(Math.max(left, right), curLen);
	}
}
