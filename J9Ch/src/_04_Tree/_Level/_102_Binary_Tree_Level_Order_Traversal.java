package _04_Tree._Level;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;

//  102. Binary Tree Level Order Traversal
//  https://leetcode.com/problems/binary-tree-level-order-traversal/
//  http://www.lintcode.com/zh-cn/problem/binary-tree-level-order-traversal/
public class _102_Binary_Tree_Level_Order_Traversal {
    //jiuzhang
    //version 1: BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
             ArrayList<Integer> level = new ArrayList<>();

             int size = queue.size();
            //1. Pop and print all nodes in curernt level
             for (int i = 0; i < size; i++) {
                 TreeNode head = queue.poll();
                 level.add(head.val);
                 //2.add left and right children into queue
                 if (head.left != null) {
                     queue.offer(head.left);
                 }
                 if (head.right != null) {
                     queue.offer(head.right);
                 }
             }
             result.add(level);
        }

        return result;
    }

//////////////////////////////////////////////////////////////////////
    //version 2:  DFS
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();

        if (root == null) {
            return results;
        }

        int maxLevel = 0;
        while (true) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            dfs(root, level, 0, maxLevel);
            if (level.size() == 0) {
                break;
            }

            results.add(level);
            maxLevel++;
        }

        return results;
    }

    private void dfs(TreeNode root, ArrayList<Integer> level,
                     int curtLevel, int maxLevel) {

        if (root == null || curtLevel > maxLevel) {
            return;
        }

        if (curtLevel == maxLevel) {
            level.add(root.val);
            return;
        }

        dfs(root.left, level, curtLevel + 1, maxLevel);
        dfs(root.right, level, curtLevel + 1, maxLevel);
    }


//////////////////////////////////////////////////////////////////////
    //version 3: BFS. two queues
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList<TreeNode> Q1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> Q2 = new ArrayList<TreeNode>();

        Q1.add(root);

        while (Q1.size() != 0) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            Q2.clear();
            for (int i = 0; i < Q1.size(); i++) {
                TreeNode node = Q1.get(i);
                level.add(node.val);
                if (node.left != null) {
                    Q2.add(node.left);
                }
                if (node.right != null) {
                    Q2.add(node.right);
                }
            }

            // swap q1 and q2
            ArrayList<TreeNode> temp = Q1;
            Q1 = Q2;
            Q2 = temp;

            // add to result
            result.add(level);
        }

        return result;
    }


//////////////////////////////////////////////////////////////////////

    //version 4: BFS, queue with dummy node
    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);
        Q.offer(null); // dummy node

        ArrayList<Integer> level = new ArrayList<Integer>();
        while (!Q.isEmpty()) {
            TreeNode node = Q.poll();
            if (node == null) {
                if (level.size() == 0) {
                    break;
                }
                result.add(level);
                level = new ArrayList<Integer>();
                Q.offer(null); // add a new dummy node
                continue;
            }

            level.add(node.val);
            if (node.left != null) {
                Q.offer(node.left);
            }
            if (node.right != null) {
                Q.offer(node.right);
            }
        }

        return result;
    }


//////////////////////////////////////////////////////////////////////

    public List<List<Integer>> levelOrder5 (TreeNode root) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();

            //1. Pop and print all nodes in curernt level
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);

                //2.add left and right children into queue
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);

            }
            res.add(list);
        }
        return res;
    }


    @Test
    public void test05() {
        int[] arr = {5, 3, 7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(levelOrder5(root));
    }
    /*
            root:
                       5
                      / \
                     /   \
                     3   7
                    / \ / \
                    1 2 6 8

                    [[5], [3, 7], [1, 2, 6, 8]]
     */
//////////////////////////////////////////////////////////////////////
    public List<List<Integer>> levelOrder6(TreeNode root) {
        List<List<Integer>> al = new ArrayList<>();
        List<Integer> nodeValues = new ArrayList<Integer>();
        if(root == null)
            return al;

        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.add(root);

        while(!current.isEmpty()){
            TreeNode node = current.remove();// Retrieves and removes the head (first element) of this list.

            if(node.left != null)
                next.add(node.left);
            if(node.right != null)
                next.add(node.right);

            nodeValues.add(node.val);
            if(current.isEmpty()){
                current = next;
                next = new LinkedList<TreeNode>();
                al.add(nodeValues);
                nodeValues = new ArrayList();
            }

        }
        return al;
    }
    @Test
    public void test06(){
        int[] arr = {9,3,20};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.print();
        System.out.println(levelOrder6(root));
//        root.print();
    }
//////////////////////////////////////////////////////////////////////
}
/*
二叉树的层次遍历

给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）

样例
给一棵二叉树 {3,9,20,#,#,15,7} ：

  3
 / \
9  20
  /  \
 15   7
返回他的分层遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
挑战
挑战1：只使用一个队列去实现它

挑战2：用DFS算法来做


 */

/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */