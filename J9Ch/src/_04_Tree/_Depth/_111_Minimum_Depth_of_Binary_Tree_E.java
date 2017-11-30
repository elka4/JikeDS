package _04_Tree._Depth;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.LinkedList;


//  111. Minimum Depth of Binary Tree
//  https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
//  http://www.lintcode.com/zh-cn/problem/minimum-depth-of-binary-tree/
public class _111_Minimum_Depth_of_Binary_Tree_E {

    public int minDepth1(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth1(root.left);
        int right = minDepth1(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
    }

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(minDepth1(root));
    }
    /*
    root:
               3
              / \
             /   \
             9   20
                / \
                15 7

            2
     */
//------------------------------------------------------------------------------
    // 9Ch
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

    @Test
    public void test02(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(minDepth2(root));
    }
//------------------------------------------------------------------------------

    public int minDepth3(TreeNode root) {
        if(root == null){
            return 0;
        }

        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> counts = new LinkedList<Integer>();

        nodes.add(root);
        counts.add(1);

        while(!nodes.isEmpty()){
            TreeNode curr = nodes.remove();
            int count = counts.remove();

            if(curr.left == null && curr.right == null){
                return count;
            }

            if(curr.left != null){
                nodes.add(curr.left);
                counts.add(count+1);
            }

            if(curr.right != null){
                nodes.add(curr.right);
                counts.add(count+1);
            }
        }

        return 0;
    }

    @Test
    public void test03(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(minDepth3(root));
    }

//--------------------------------------------------------------------------------

    public int minDepth4(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.left == null){
            return 1;
        }

        //让权。非法情况下就把结果变得无穷大。
        int left = root.left == null ? Integer.MAX_VALUE : minDepth4(root.left);
        int right = root.right == null ? Integer.MAX_VALUE : minDepth4(root.right);

        return Math.min(left, right) + 1;
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
        System.out.println(minDepth4(root));
    }

//------------------------------------------------------------------------------
}
/*
二叉树的最小深度

 描述
 笔记
 数据
 评测
给定一个二叉树，找出其最小深度。

二叉树的最小深度为根节点到最近叶子节点的距离。
样例
给出一棵如下的二叉树:

        1

     /     \

   2       3

          /    \

        4      5

这个二叉树的最小深度为 2

标签
二叉树 深度优先搜索
 */

/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path
from the root node down to the nearest leaf node.
 */