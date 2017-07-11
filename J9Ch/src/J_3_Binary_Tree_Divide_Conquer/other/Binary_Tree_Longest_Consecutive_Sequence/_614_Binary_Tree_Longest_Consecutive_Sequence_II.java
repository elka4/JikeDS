package J_3_Binary_Tree_Divide_Conquer.other.Binary_Tree_Longest_Consecutive_Sequence;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**
 * 614
 * Binary Tree Longest Consecutive Sequence II
 * Created by tianhuizhu on 6/28/17.
 */

// from any to any
public class _614_Binary_Tree_Longest_Consecutive_Sequence_II {
    class ResultType {
        public int max_length, max_down, max_up;
        ResultType(int len, int down, int up) {
            max_length = len;
            max_down = down;
            max_up = up;
        }
    }
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
        // Write your code here
        return helper(root).max_length;
    }

    ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        int down = 0, up = 0;
        if (root.left != null && root.left.val + 1 == root.val)
            down = Math.max(down, left.max_down + 1);

        if (root.left != null && root.left.val - 1 == root.val)
            up = Math.max(up, left.max_up + 1);

        if (root.right != null && root.right.val + 1 == root.val)
            down = Math.max(down, right.max_down + 1);

        if (root.right != null && root.right.val - 1 == root.val)
            up = Math.max(up, right.max_up + 1);

        int len = down + 1 + up;
        len = Math.max(len, Math.max(left.max_length, right.max_length));

        return new ResultType(len, down, up);
    }
    /*
    1
   / \
  2   0
 /
3
Return 4 // 0-1-2-3
     */

    @Test
    public void test01(){
        int[] arr = {1,2,0};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(3));

        System.out.println("root: ");
        root.print();
        System.out.println(longestConsecutive2(root));

    }

}
