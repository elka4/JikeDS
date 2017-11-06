package _04_Tree._Depth;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


//  104. Maximum Depth of Binary Tree
//  https://leetcode.com/problems/maximum-depth-of-binary-tree/
//  http://www.lintcode.com/zh-cn/problem/maximum-depth-of-binary-tree/
public class _104_Tree_Maximum_Depth_of_Binary_Tree_E {


    // Version 1: Divide Conquer. With no global depth.

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth1(root.left);
        int right = maxDepth1(root.right);

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
    public int minDepth2(TreeNode root) {
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

    public int maxDepth(TreeNode root) { if(root==null){
        return 0; }
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }


//    DFS

    public int maxDepth2(TreeNode root) {
        if(root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            max = Math.max(temp, max);
            if(node.left != null) {
                stack.push(node.left);
                value.push(temp+1);
            }
            if(node.right != null) {
                stack.push(node.right);
                value.push(temp+1);
            }
        }
        return max;
    }
// 7ms
//    BFS

    public int maxDepth4(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            count++;
        }
        return count;
    }
// 3ms
}
/*
二叉树的最大深度

 描述
 笔记
 数据
 评测
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的距离。

样例
给出一棵如下的二叉树:

  1
 / \
2   3
   / \
  4   5
这个二叉树的最大深度为3.

标签
分治法 递归 二叉树 优步
 */
/*

 */
