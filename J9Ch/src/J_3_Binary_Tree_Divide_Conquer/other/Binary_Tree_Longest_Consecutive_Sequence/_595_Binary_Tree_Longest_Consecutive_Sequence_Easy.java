package J_3_Binary_Tree_Divide_Conquer.other.Binary_Tree_Longest_Consecutive_Sequence;
import lib.TreeNode;
import org.junit.Test;

/** 595. Binary Tree Longest Consecutive Sequence
 * Easy

 * Created by tianhuizhu on 6/27/17.
 */
public class _595_Binary_Tree_Longest_Consecutive_Sequence_Easy {

    // version 1: Traverse + Divide Conquer
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {

        return helper(root, null, 0);
    }

    private int helper(TreeNode root, TreeNode parent, int lengthWithoutRoot) {
        if (root == null) {
            return 0;
        }

        int length = (parent != null && parent.val + 1 == root.val)?
                lengthWithoutRoot + 1
                : 1;
        int left = helper(root.left, root, length);
        int right = helper(root.right, root, length);
        return Math.max(length, Math.max(left, right));
    }

    // version 2: Another Traverse + Divide Conquer
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    private int longest;
    public int longestConsecutive_2(TreeNode root) {
        longest = 0;
        helper(root);
        return longest;
    }

    private int helper_2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper_2(root.left);
        int right = helper_2(root.right);

        int subtreeLongest = 1; // at least we have root
        if (root.left != null && root.val + 1 == root.left.val) {
            subtreeLongest = Math.max(subtreeLongest, left + 1);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            subtreeLongest = Math.max(subtreeLongest, right + 1);
        }

        if (subtreeLongest > longest) {
            longest = subtreeLongest;
        }
        return subtreeLongest;
    }


    // version 3: Divide Conquer
    private class ResultType {
        int maxInSubtree;
        int maxFromRoot;
        public ResultType(int maxInSubtree, int maxFromRoot) {
            this.maxInSubtree = maxInSubtree;
            this.maxFromRoot = maxFromRoot;
        }
    }
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive_3(TreeNode root) {
        System.out.println("maxInSubtree "+helper(root).maxInSubtree);
        System.out.println("maxFromRoot "+helper(root).maxFromRoot);
        return helper(root).maxInSubtree;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // 1 is the root itself.
        ResultType result = new ResultType(0, 1);

        if (root.left != null && root.val + 1 == root.left.val) {
            result.maxFromRoot = Math.max(
                    result.maxFromRoot,
                    left.maxFromRoot + 1
            );
        }

        if (root.right != null && root.val + 1 == root.right.val) {
            result.maxFromRoot = Math.max(
                    result.maxFromRoot,
                    right.maxFromRoot + 1
            );
        }

        result.maxInSubtree = Math.max(
                result.maxFromRoot,
                Math.max(left.maxInSubtree, right.maxInSubtree)
        );

        return result;
    }

/*
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    /
   2
  /
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */
    @Test
    public void test01() {
        TreeNode root = new TreeNode(1);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(2));
        root.right.setRightChild(new TreeNode(4));
        root.right.right.setRightChild(new TreeNode(5));

        root.print();
        System.out.println(longestConsecutive_3(root));
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(2);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(5));
        root.right.left.setRightChild(new TreeNode(1));

        root.print();
        System.out.println(longestConsecutive_3(root));
    }
}
