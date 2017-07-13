package J_3_Binary_Tree_Divide_Conquer.other.subtree;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import org.junit.Test;

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
        //int[] max = {0};
        Result res = helper(root);
        return res.size;
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

            return new Result(left.size + right.size + 1,
                    Math.min(left.min, root.val), Math.max(root.val, right.max));
        }
    }

    @Test
    public void test01(){
        int[] arr = {5,3,7,1,4,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(largestBSTSubtree(root));
    }

}
