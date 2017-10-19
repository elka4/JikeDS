package _4_Tree.Binary_Tree_Longest_Consecutive_Sequence;
import lib.TreeNode;
import org.junit.Test;

/** 595. Binary Tree Longest Consecutive Sequence
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */

// from top to bottum
public class _595_Binary_Tree_Longest_Consecutive_Sequence {

    // version 1: Traverse + Divide Conquer
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {

        return helper(root, null, 0);
    }

    private int helper00(TreeNode root, TreeNode parent, int lengthWithoutRoot) {
        if (root == null) {
            return 0;
        }

        //从上往下算，一开始给root为1，符合条件就加1，不符合条件就重设为1
        int length = (parent != null && parent.val + 1 == root.val)?
                lengthWithoutRoot + 1
                : 1;

        int left = helper(root.left, root, length);
        int right = helper(root.right, root, length);

        return Math.max(length, Math.max(left, right));
    }

    private int helper(TreeNode root, TreeNode parent, int lengthWithoutRoot) {
        if (root == null) {
            return 0;
        }
        //从上往下算，一开始给root为1，符合条件就加1，不符合条件就重设为1
        int length = (parent != null && parent.val + 1 == root.val)?
                lengthWithoutRoot + 1
                : 1;
        System.out.println("root: " + root.val + ". length: " + length);

        int left = helper(root.left, root, length);

        if(root.left != null ){
            root.left.print();
            System.out.println("left: " + left);
        }
        else System.out.println("root.left is null. " + "left " + left);

        int right = helper(root.right, root, length);

        if(root.right != null ){
            root.right.print();
            System.out.println("right: " + right);
        }
        else System.out.println("root.right is null. " + "right " + right);

        return Math.max(length, Math.max(left, right));
    }

    @Test
    public void test01() {
        TreeNode root = new TreeNode(1);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(2));
        root.right.setRightChild(new TreeNode(4));
        root.right.right.setRightChild(new TreeNode(5));

        root.print();
        System.out.println(longestConsecutive(root));
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(2);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(5));
        root.right.left.setRightChild(new TreeNode(1));

        root.print();
        System.out.println(longestConsecutive3(root));
    }

////////////////////////////////////////////////////////////////

    // version 2: Another Traverse + Divide Conquer
    /**
     * @param root the root of binary tree
     * @return the length of the longest consecutive sequence path
     */
    private int longest;
    public int longestConsecutive2(TreeNode root) {
        longest = 0;
        helper_2(root);
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

    @Test
    public void test03() {
        TreeNode root = new TreeNode(1);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(2));
        root.right.setRightChild(new TreeNode(4));
        root.right.right.setRightChild(new TreeNode(5));

        root.print();
        System.out.println(longestConsecutive2(root));
    }

    @Test
    public void test04() {
        TreeNode root = new TreeNode(2);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(5));
        root.right.left.setRightChild(new TreeNode(1));

        root.print();
        System.out.println(longestConsecutive2(root));
    }

///////////////////////////////////////////////////////////////////

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
    public int longestConsecutive3(TreeNode root) {
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

    @Test
    public void test05() {
        TreeNode root = new TreeNode(1);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(2));
        root.right.setRightChild(new TreeNode(4));
        root.right.right.setRightChild(new TreeNode(5));

        root.print();
        System.out.println(longestConsecutive3(root));
    }

    @Test
    public void test06() {
        TreeNode root = new TreeNode(2);

        root.setRightChild(new TreeNode(3));
        root.right.setLeftChild(new TreeNode(5));
        root.right.left.setRightChild(new TreeNode(1));

        root.print();
        System.out.println(longestConsecutive3(root));
    }

///////////////////////////////////////////////////////////////////

   // Approach #1 (Top Down Depth-first Search) [Accepted]
/*
Algorithm

A top down approach is similar to an in-order traversal.
 We use a variable length to store the current consecutive
 path length and pass it down the tree. As we traverse,
 we compare the current node with its parent node to determine
 if it is consecutive.
  If not, we reset the length.


 */


    private int maxLength4 = 0;
    public int longestConsecutive4(TreeNode root) {
        dfs(root, null, 0);
        return maxLength4;
    }

    private void dfs(TreeNode p, TreeNode parent, int length) {
        if (p == null) return;
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        maxLength4 = Math.max(maxLength4, length);
        dfs(p.left, p, length);
        dfs(p.right, p, length);
    }
/*
@lightmark presents a neat approach without storing the maxLength
 as a global variable.
 */

    public int longestConsecutive5(TreeNode root) {

        return dfs5(root, null, 0);
    }

    private int dfs5(TreeNode p, TreeNode parent, int length) {
        if (p == null) return length;

        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;

        return Math.max(length,
                Math.max(dfs5(p.left, p, length), dfs5(p.right, p, length)));
    }

    /*
    Complexity analysis

Time complexity : O(n)O(n). The time complexity is the same as an in-order
traversal of a binary tree with nn nodes.

Space complexity : O(n)O(n). The extra space comes from implicit stack
space due to recursion. For a skewed binary tree, the recursion could go
 up to nn levels deep.


     */
///////////////////////////////////////////////////////////////////

//Approach #2 (Bottom Up Depth-first Search) [Accepted]

 //   Algorithm The bottom-up approach is similar to a post-order traversal.
// We return the consecutive path length starting at current node to its parent.
// Then its parent can examine if its node value can be included in this
// consecutive path.

    private int maxLength = 0;
    public int longestConsecutive6(TreeNode root) {
        dfs6(root);
        return maxLength;
    }

    private int dfs6(TreeNode p) {
        if (p == null) return 0;

        int L = dfs6(p.left) + 1;
        int R = dfs6(p.right) + 1;

        if (p.left != null && p.val + 1 != p.left.val) {
            L = 1;
        }

        if (p.right != null && p.val + 1 != p.right.val) {
            R = 1;
        }

        int length = Math.max(L, R);

        maxLength = Math.max(maxLength, length);

        return length;
    }

    /*
    Complexity analysis

Time complexity : O(n)O(n). The time complexity is the same as a post-order
 traversal in a binary tree, which is O(n)O(n).

Space complexity : O(n)O(n). The extra space comes from implicit stack
 space due to recursion.
For a skewed binary tree, the recursion could go up to nn levels deep.
     */


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

}
