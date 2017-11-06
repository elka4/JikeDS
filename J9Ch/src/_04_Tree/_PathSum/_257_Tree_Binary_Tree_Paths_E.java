package _04_Tree._PathSum;
import lib.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//  257. Binary Tree Paths
//  https://leetcode.com/problems/binary-tree-paths/description/
//  http://www.lintcode.com/zh-cn/problem/binary-tree-paths/
public class _257_Tree_Binary_Tree_Paths_E {

    //Accepted Java simple solution in 8 lines
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<String>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }
    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }


    /*
    Clean Java solution (Accepted) without any helper recursive function
Lot of recursive solutions on this forum involves creating a helper recursive function with added parameters. The added parameter which usually is of the type List<String> , carries the supplementary path information. However, the approach below doesn't use such a helper function.
     */
    public List<String> binaryTreePaths2(TreeNode root) {

        List<String> paths = new LinkedList<>();

        if(root == null) return paths;

        if(root.left == null && root.right == null){
            paths.add(root.val+"");
            return paths;
        }

        for (String path : binaryTreePaths(root.left)) {
            paths.add(root.val + "->" + path);
        }

        for (String path : binaryTreePaths(root.right)) {
            paths.add(root.val + "->" + path);
        }

        return paths;

    }
///////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
// version 1: Divide Conquer
public class Jiuzhang {
    /**
     * @param root the root of the binary tree
     * @return all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);
        for (String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        for (String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }

        // root is a leaf
        if (paths.size() == 0) {
            paths.add("" + root.val);
        }

        return paths;
    }
}
///////////////////////////////////////////////////////////////////////////////////
}
/*
二叉树的所有路径

 描述
 笔记
 数据
 评测
给一棵二叉树，找出从根节点到叶子节点的所有路径。

样例
给出下面这棵二叉树：

   1
 /   \
2     3
 \
  5
所有根到叶子的路径为：

[
  "1->2->5",
  "1->3"
]
 */

/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

 */
