package _04_Tree._Subtree;

import lib.TreeNode;


//
//
//
public class _333_Tree_Largest_BST_Subtree_M {
    public class Solution {

        class Result {  // (size, rangeLower, rangeUpper) -- size of current tree, range of current tree [rangeLower, rangeUpper]
            int size;
            int lower;
            int upper;

            Result(int size, int lower, int upper) {
                this.size = size;
                this.lower = lower;
                this.upper = upper;
            }
        }

        int max = 0;

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) { return 0; }
            traverse(root);
            return max;
        }

        private Result traverse(TreeNode root) {
            if (root == null) { return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE); }
            Result left = traverse(root.left);
            Result right = traverse(root.right);
            if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
                return new Result(-1, 0, 0);
            }
            int size = left.size + 1 + right.size;
            max = Math.max(size, max);
            return new Result(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
        }
    }
    /*
        in brute-force solution, we get information in a top-down manner.
        for O(n) solution, we do it in bottom-up manner, meaning we collect information during backtracking.
    */
    public class Solution2 {

        class Result {  // (size, rangeLower, rangeUpper) -- size of current tree, range of current tree [rangeLower, rangeUpper]
            int size;
            int lower;
            int upper;

            Result(int size, int lower, int upper) {
                this.size = size;
                this.lower = lower;
                this.upper = upper;
            }
        }

        int max = 0;

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) { return 0; }
            traverse(root, null);
            return max;
        }

        private Result traverse(TreeNode root, TreeNode parent) {
            if (root == null) { return new Result(0, parent.val, parent.val); }
            Result left = traverse(root.left, root);
            Result right = traverse(root.right, root);
            if (left.size==-1 || right.size==-1 || root.val<left.upper || root.val>right.lower) {
                return new Result(-1, 0, 0);
            }
            int size = left.size + 1 + right.size;
            max = Math.max(size, max);
            return new Result(size, left.lower, right.upper);
        }
    }



    public class Solution3 {
        class Result {
            int res;
            int min;
            int max;
            public Result(int res, int min, int max) {
                this.res = res;
                this.min = min;
                this.max = max;
            }
        }

        public int largestBSTSubtree(TreeNode root) {
            Result res = BSTSubstree(root);
            return Math.abs(res.res);
        }

        private Result BSTSubstree(TreeNode root) {
            if (root == null) return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            Result left = BSTSubstree(root.left);
            Result right = BSTSubstree(root.right);
            if (left.res < 0 || right.res < 0 || root.val < left.max || root.val > right.min) {
                return new Result(Math.max(Math.abs(left.res), Math.abs(right.res)) * -1, 0, 0);
            } else {
                return new Result(left.res + right.res + 1, Math.min(root.val, left.min), Math.max(root.val, right.max));
            }
        }
    }

    public class Solution4 {
        public int largestBSTSubtree(TreeNode root) {
            int[] ans = new int[]{0};
            findBST(root, ans);
            return ans[0];
        }

        private class Res {
            int min, max, size;

            public Res(int l, int r, int k) {
                min = l; max = r; size = k;
            }
        }

        private Res findBST(TreeNode rt, int[] ans) {
            if (rt == null) return null;
            boolean isBST = true;
            int min = rt.val, max = rt.val, size = 1;
            Res l = findBST(rt.left, ans), r = findBST(rt.right, ans);
            if (rt.left != null) {
                if (l == null || l.max > rt.val) return null;
                min = l.min;
                size += l.size;
            }
            if (rt.right != null) {
                if (r == null || r.min < rt.val) return null;
                max = r.max;
                size += r.size;
            }
            ans[0] = Math.max(ans[0], size);
            return new Res(min, max, size);
        }
    }
}
/*

 */
/*

 */
