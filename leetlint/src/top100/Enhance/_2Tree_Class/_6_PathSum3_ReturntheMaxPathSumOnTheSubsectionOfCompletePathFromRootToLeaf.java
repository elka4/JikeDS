package top100.Enhance._2Tree_Class;


public class _6_PathSum3_ReturntheMaxPathSumOnTheSubsectionOfCompletePathFromRootToLeaf {

public int maxPathSum(TreeNode root) {

    if (root == null)
      return Integer.MIN_VALUE;
    int[] max = {Integer.MIN_VALUE};
    helper(root, max);
    return max[0];
  }


  private int helper(TreeNode root, int[] max) {
    if (root == null)
      return 0;

    int left = helper(root.left, max);
    int right = helper(root.right, max);

    left = Math.max(left, 0);
    right = Math.max(right, 0);

    max[0] = Math.max(max[0], Math.max(left, right) + root.val);

    return Math.max(left, right) + root.val;
  }
  
  private class TreeNode {

		public int val ;
		public TreeNode(int i) {
			// TODO Auto-generated constructor stub
		}
		public TreeNode left;
		public TreeNode right;

	}
}
