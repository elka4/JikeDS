package top100.Enhance._2Tree_Class;

//O(n) Time Solution
public class _8_LargestBSTSubTree_2 {
	class Result {
        int size;
        int min;
        int max;

        public Result (int size, int min, int max) {
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = {0};
        Result res = helper(root);
        return max[0];
    }

    private Result helper(TreeNode root) {
        if (root == null) {
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Result left = helper(root.left);
        Result right = helper(root.right);

        if (left.size == -1 || right.size == -1 ||
        		root.val <= left.max || root.val >= right.min) {
            return new Result(-1, 0, 0);
        } else {

            return new Result(left.size + right.size + 1, Math.min(left.min, root.val), Math.max(root.val, right.max));
        }
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
