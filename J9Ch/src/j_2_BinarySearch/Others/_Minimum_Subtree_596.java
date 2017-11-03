package j_2_BinarySearch.Others;


//
public class _Minimum_Subtree_596 {

  public class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = this.right = null;
      }
  }


    // version 1 : traverse + divide conquer
    public class Solution1 {
        private TreeNode subtree = null;
        private int subtreeSum = Integer.MAX_VALUE;
        /**
         * @param root the root of binary tree
         * @return the root of the minimum subtree
         */
        public TreeNode findSubtree(TreeNode root) {
            helper(root);
            return subtree;
        }

        private int helper(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int sum = helper(root.left) + helper(root.right) + root.val;
            if (sum < subtreeSum) {
                subtreeSum = sum;
                subtree = root;
            }
            return sum;
        }
    }

    // version 2: Pure divide conquer
    class ResultType {
        public TreeNode minSubtree;
        public int sum, minSum;
        public ResultType(TreeNode minSubtree, int minSum, int sum) {
            this.minSubtree = minSubtree;
            this.minSum = minSum;
            this.sum = sum;
        }
    }

    public class Solution2 {
        /**
         * @param root the root of binary tree
         * @return the root of the minimum subtree
         */
        public TreeNode findSubtree(TreeNode root) {
            ResultType result = helper(root);
            return result.minSubtree;
        }

        public ResultType helper(TreeNode node) {
            if (node == null) {
                return new ResultType(null, Integer.MAX_VALUE, 0);
            }

            ResultType leftResult = helper(node.left);
            ResultType rightResult = helper(node.right);

            ResultType result = new ResultType(
                    node,
                    leftResult.sum + rightResult.sum + node.val,
                    leftResult.sum + rightResult.sum + node.val
            );

            if (leftResult.minSum < result.minSum) {
                result.minSum = leftResult.minSum;
                result.minSubtree = leftResult.minSubtree;
            }

            if (rightResult.minSum < result.minSum) {
                result.minSum = rightResult.minSum;
                result.minSubtree = rightResult.minSubtree;
            }

            return result;
        }
    }
}
//给一棵二叉树, 找到和为最小的子树, 返回其根节点。

