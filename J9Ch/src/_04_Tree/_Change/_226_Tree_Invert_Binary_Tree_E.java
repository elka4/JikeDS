package _04_Tree._Change;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


//  226. Invert Binary Tree
//  https://leetcode.com/problems/invert-binary-tree/description/
//  http://www.lintcode.com/zh-cn/problem/invert-binary-tree/
public class _226_Tree_Invert_Binary_Tree_E {


    public class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            final TreeNode left = root.left, right = root.right;
            root.left = invertTree(right);
            root.right = invertTree(left);
            return root;
        }
    }


    public class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            final Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                final TreeNode node = stack.pop();
                final TreeNode left = node.left;
                node.left = node.right;
                node.right = left;
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
            return root;
        }
    }



    public class Solution3 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) { return null;
            }
            final Queue<TreeNode> queue = new LinkedList<>(); queue.offer(root);
            while(!queue.isEmpty()) {
                final TreeNode node = queue.poll(); final TreeNode left = node.left; node.left = node.right;
                node.right = left;
                if(node.left != null) { queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right); }
            }
            return root;
        }
    }

/////////////////////////////////////////////////
    //Java Solution 1 - Recursive

    public TreeNode invertTree(TreeNode root) {
        if(root!=null){
            helper(root);
        }

        return root;
    }

    public void helper(TreeNode p){

        TreeNode temp = p.left;
        p.left = p.right;
        p.right = temp;

        if(p.left!=null)
            helper(p.left);

        if(p.right!=null)
            helper(p.right);
    }

    @Test
    public void test01() {
        int[] arr = {5, 3, 7,1,2,6};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        invertTree(root).print();

    }
/*
root:
   5
  / \
 /   \
 3   7
/ \ /
1 2 6

   5
  / \
 /   \
 7   3
  \ / \
  6 2 1

 */

//////////////////////////////////////////////////////////////////////////


    //Java Solution 2 - Iterative

    public TreeNode invertTree2(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

        if(root!=null){
            queue.add(root);
        }

        while(!queue.isEmpty()){
            TreeNode p = queue.poll();
            if(p.left!=null)
                queue.add(p.left);
            if(p.right!=null)
                queue.add(p.right);

            TreeNode temp = p.left;
            p.left = p.right;
            p.right = temp;
        }

        return root;
    }

}
/*
翻转二叉树

 描述
 笔记
 数据
 评测
翻转一棵二叉树

样例
  1         1
 / \       / \
2   3  => 3   2
   /       \
  4         4
挑战
递归固然可行，能否写个非递归的？
 */
/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:
Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
 */
