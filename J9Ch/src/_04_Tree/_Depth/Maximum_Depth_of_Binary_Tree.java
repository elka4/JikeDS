package _04_Tree._Depth;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**97. Maximum Depth of Binary Tree
 * Easy
 * Created by tianhuizhu on 6/27/17.
 */
public class Maximum_Depth_of_Binary_Tree {

    // Version 1: Divide Conquer. With no global depth.

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    @Test
    public void test01() {
        int[] arr = {1, 2, 3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(4));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(maxDepth(root));
    }


/////////////////////////////////////////////////////////////////////////////////
    class sol{
    public int maxDepth0(TreeNode root) {
        if(root==null)
            return 0;

        int leftDepth = maxDepth0(root.left);
        int rightDepth = maxDepth0(root.right);

        int bigger = Math.max(leftDepth, rightDepth);

        return bigger+1;
    }
}

/////////////////////////////////////////////////////////////////////////////////
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root){
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }
/////////////////////////////////////////////////////////////////////////////////

// version 2: Traverse. With global depth.

    private int depth;

    public int maxDepth_2(TreeNode root) {
        depth = 0;
        helper(root, 1);

        return depth;
    }

    private void helper(TreeNode node, int curtDepth) {
        if (node == null) {
            return;
        }

        if (curtDepth > depth) {
            depth = curtDepth;
        }

        helper(node.left, curtDepth + 1);
        helper(node.right, curtDepth + 1);
    }

    @Test
    public void test02() {
        int[] arr = {1, 2, 3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(4));
        root.right.setRightChild(new TreeNode(5));
        System.out.println("root: ");
        root.print();
        System.out.println(maxDepth_2(root));
    }

/////////////////////////////////////////////////////////////////////////////////
    //non-recursion
    @SuppressWarnings("all")
    public int maxDepth3(TreeNode root) {
        if (root == null){
            return 0;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        int count = 0;

        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size-- > 0) {
                TreeNode cur = stack.pop();
                if (cur.left != null){
                    stack.addLast(cur.left);
                }
                if (cur.right != null){
                    stack.addLast(cur.right);
                }
            }
            count++;

        }

        return count;
    }


/////////////////////////////////////////////////////////////////////////////////
//技巧：求最小值，那就先都把默认设成无穷大。最后用Max.min（）来滤过无穷大的值。
@SuppressWarnings("all")

class _155Minimum_Depth_of_Binary_Tree_1 {
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }

//////////////////////////////////////////////////////////////


    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.left == null) {
            return 1;
        }

        //让权。非法情况下就把结果变得无穷大。
        int left = root.left == null ? Integer.MAX_VALUE : minDepth2(root.left);
        int right = root.right == null ? Integer.MAX_VALUE : minDepth2(root.right);

        return Math.min(left, right) + 1;
    }
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

/*
          1
         / \
        2   3
           / \
          4   5
        The maximum depth is 3.
 */
}

}
/*
 * Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest
 path from the root node down to the farthest leaf node.

Example: Given a binary tree as follow:
  1
 / \
2   3
   / \
  4   5
The maximum depth is 3.
Tags: Divide and Conquer, Recursion, Binary Tree, Uber
Related Problems: Easy Minimum Depth of Binary Tree 31 %
 * */

/*
 * Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest
path from the root node down to the nearest leaf node.

Example: Given a binary tree as follow:

  1
 / \
2   3
   / \
  4   5
The minimum depth is 2.

Tags: Binary Tree Depth First Search
Related Problems: Easy Maximum Depth of Binary Tree 55 %
 * */