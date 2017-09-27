package j_3_BianryTree;

import lib.TreeNode;

//Version 2:
//SinglePath也定义为，至少包含一个点。
class _94Binary_Tree_Maximum_Path_Sum_2 {

    private class ResultType {
     int singlePath, maxPath;
     ResultType(int singlePath, int maxPath) {
         this.singlePath = singlePath;
         this.maxPath = maxPath;
     }
    }

    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum(TreeNode root) {
     ResultType result = helper(root);
     return result.maxPath;
    }

    private ResultType helper(TreeNode root) {
     if (root == null) {
         return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
     }
     // Divide
     ResultType left = helper(root.left);
     ResultType right = helper(root.right);

     // Conquer
     int singlePath =
         Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;

     int maxPath = Math.max(left.maxPath, right.maxPath);
     maxPath = Math.max(maxPath,
                        Math.max(left.singlePath, 0) +
                        Math.max(right.singlePath, 0) + root.val);

     return new ResultType(singlePath, maxPath);
    }
}
