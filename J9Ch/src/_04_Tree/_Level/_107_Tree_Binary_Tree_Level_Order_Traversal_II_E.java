package _04_Tree._Level;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.*;

//  107. Binary Tree Level Order Traversal II
//  https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
//  http://www.lintcode.com/zh-cn/problem/binary-tree-level-order-traversal-ii/
//  Tree， Breadth-first Search
//  4:123都很好。1，BFS。2，DFS（这个有难度啊，要理解一下）。3,BFS + Collections.reverse。
public class _107_Tree_Binary_Tree_Level_Order_Traversal_II_E {
    //1
    //BFS iterative:
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        if(root == null) return result;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> list = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                //处理Queue
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                //处理List
                list.add(queue.poll().val);
            }
            result.add(0, list);//唯一的区别
        }
        return result;
    }

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");root.print();
        System.out.println(levelOrderBottom1(root));
    }
/*
            root:
               3
              / \
             /   \
             9   20
                / \
                15 7

            [[15, 7], [9, 20], [3]]
 */
//-------------------------------------------------------------------------//
    //2
    //DFS solution:
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        levelMaker(result, root, 0);
        return result;
    }
    //post order
    public void levelMaker(List<List<Integer>> result, TreeNode root, int level) {
        if(root == null) return;
        if(level == result.size()) {//原本是>=
            result.add(0, new LinkedList<Integer>());//唯一区别
        }
        levelMaker(result, root.left, level+1);
        levelMaker(result, root.right, level+1);

        result.get(result.size()-level-1).add(root.val);//注意这里。这个怎么理解？！！
    }

    @Test
    public void test02(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");root.print();
        System.out.println(levelOrderBottom2(root));
    }

/*
            root:
               3
              / \
             /   \
             9   20
                / \
                15 7

            [[15, 7], [9, 20], [3]]
 */
//-------------------------------------------------------------------------//////////
    //3
    //jiuzhang
    // BFS iterative with Queue
    //   Collections.reverse(result);
    //超级简单啊，只是用了Collections.reverse(）。要记住这个方法。
    public List<List<Integer>>  levelOrderBottom3(TreeNode root) {
        List<List<Integer>>  result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }

        Collections.reverse(result);
        return result;
    }


    @Test
    public void test03(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");root.print();
        System.out.println(levelOrderBottom3(root));
    }
/*
root:
   3
  / \
 /   \
 9   20
    / \
    15 7

[[15, 7], [9, 20], [3]]
 */
//-------------------------------------------------------------------------
    //4
    //双Queue
    public List<ArrayList<Integer>> levelOrderBottom4(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new  ArrayList<ArrayList<Integer>>();

        if(root == null){
            return result;
        }

        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.offer(root);

        ArrayList<Integer> numberList = new ArrayList<Integer>();

        // need to track when each level starts
        while(!current.isEmpty()){
            TreeNode head = current.poll();

            //处理List
            numberList.add(head.val);

            //处理Queue
            if(head.left != null){
                next.offer(head.left);
            }
            if(head.right!= null){
                next.offer(head.right);
            }
            //swap Queue
            if(current.isEmpty()){
                current = next;
                next = new LinkedList<TreeNode>();
                result.add(numberList);
                numberList = new ArrayList<Integer>();
            }
        }

        //return Collections.reverse(result);
        ArrayList<ArrayList<Integer>> reversedResult = new  ArrayList<ArrayList<Integer>>();
        for(int i=result.size()-1; i>=0; i--){
            reversedResult.add(result.get(i));
        }

        return reversedResult;
    }
    @Test
    public void test04(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(levelOrderBottom3(root));
    }

//-------------------------------------------------------------------------//////////
}
/*
二叉树的层次遍历 II

 描述
 笔记
 数据
 评测
给出一棵二叉树，返回其节点值从底向上的层次序遍历（按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历）

样例
给出一棵二叉树 {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
按照从下往上的层次遍历为：

[
  [15,7],
  [9,20],
  [3]
]
 */

/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 */