package _04_Tree.Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.*;

//  leetcode        113. Path Sum II
//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

//root to leaf, 所有node的value和为sum， 返回所有的path
// 从根到底 加和 是否等于sum
public class PathSum_II {

  public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        List<Integer> list = new ArrayList<Integer>();

        helper00(root, sum, list, res);

        return res;
    }

    private void helper00(TreeNode root, int sum, List<Integer> list,
                        List<List<Integer>> res) {

        if (root == null) {
            return;
        }

        list.add(root.val);         // 经典的recursion开始结构

        //叶子节点，且叶子节点的value和剩余在sum中的值相等时，
        // 将list生成新的list并加入到结果中

        // 注意这个条件，必须左右都为null就是走到leaf，而且root.val就是sum
        // 注意必须要new 一个list
        if (sum == root.val && root.left == null && root.right == null) {
            res.add(new ArrayList<Integer>(list));

        } else {
            helper00(root.left, sum - root.val, list, res);
            helper00(root.right, sum - root.val, list, res);
        }

        list.remove(list.size() - 1); // 经典的recursion结束结构
    }


/*    private void helper(TreeNode root, int sum, List<Integer> list,
            List<List<Integer>> res) {

        if (root == null) {
            return;
        }

        list.add(root.val);

        //叶子节点，且叶子节点的value和剩余在sum中的值相等时，
        // 将list生成新的list并加入到结果中
        if (sum == root.val && root.left == null && root.right == null) {
            System.out.println("sum == root.val " + sum );
            res.add(new ArrayList<Integer>(list));

        } else {
            System.out.println("root.left " + root.left);
            helper(root.left, sum - root.val, list, res);
            System.out.println("root.left " + root.left + " ||| "
                    + "root.right " + root.right);
            helper(root.right, sum - root.val, list, res);
            System.out.println("root.right " + root.right);
        }
        System.out.println("list " + list);
        list.remove(list.size() - 1);
        System.out.println("list " + list);
    }*/


    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 11));
    }
    /*
    root:
           1
          / \
         /   \
         3   5
        / \ / \
        6 7 8 5

        [[1, 3, 7], [1, 5, 5]]
     */

    @Test
    public void test02(){
        int[] arr = {1,3,5,6,7,4,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 10));
    }
    /*
    root:
               1
              / \
             /   \
             3   5
            / \ / \
            6 7 4 5

            [[1, 3, 6], [1, 5, 4]]
     */

    @Test
    public void test03(){
        int[] arr = {1,2,4,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 5));
    }
/*         5
        root:
           1
          / \
         /   \
         2   4
        / \
        2 3

        [[1, 2, 2], [1, 4]]
 */

/////////////////////////////////////////////////////////////////////////

    //jiuzhang

    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Algorithm: Traverse
        // Use recursion to traverse the tree in preorder, pass with a parameter
        // `sum` as the sum of each node from root to current node.
        // Put the whole path into result if the leaf has a sum equal to target.

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();

        path.add(root.val);

        helper(root, path, root.val, target, result);

        return result;
    }

    private void helper(TreeNode root,
                        ArrayList<Integer> path,
                        int sum,
                        int target,
                        List<List<Integer>> result) {

        // meet leaf
        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }

        // go left
        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, path, sum + root.left.val, target, result);
            path.remove(path.size() - 1);
        }

        // go right
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, path, sum + root.right.val, target, result);
            path.remove(path.size() - 1);
        }
    }


    public class Jiuzhang {
        public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
            ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> solution = new ArrayList<Integer>();

            findSum(rst, solution, root, sum);
            return rst;
        }

        private void findSum(ArrayList<ArrayList<Integer>> result,
                             ArrayList<Integer> solution, TreeNode root, int sum){
            if (root == null) {
                return;
            }

            sum -= root.val;

            if (root.left == null && root.right == null) {
                if (sum == 0){
                    solution.add(root.val);
                    result.add(new ArrayList<Integer>(solution));
                    solution.remove(solution.size()-1);
                }
                return;
            }

            solution.add(root.val);
            findSum(result, solution, root.left, sum);
            findSum(result, solution, root.right, sum);
            solution.remove(solution.size()-1);
        }
    }

/////////////////////////////////////////////////////////////////////////

    public List<List<Integer>> pathSum4(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;

        List<Integer> l = new ArrayList<Integer>();
        l.add(root.val);
        dfs(root, sum-root.val, result, l);
        return result;
    }


    public void dfs(TreeNode t, int sum, List<List<Integer>> result,
                    List<Integer> l){

        if(t.left==null && t.right==null && sum==0){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(l);
            result.add(temp);
        }

        //search path of left node
        if(t.left != null){
            l.add(t.left.val);
            dfs(t.left, sum-t.left.val, result, l);
            l.remove(l.size()-1);
        }

        //search path of right node
        if(t.right!=null){
            l.add(t.right.val);
            dfs(t.right, sum-t.right.val, result, l);
            l.remove(l.size()-1);
        }
    }


/////////////////////////////////////////////////////////////////////////

public class Solution {

    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();

    public void pathSumInner(TreeNode root, int sum, Stack<Integer>path) {

        path.push(root.val);

        if(root.left == null && root.right == null)
            if(sum == root.val) resultList.add(new ArrayList<Integer>(path));

        if(root.left!=null) pathSumInner(root.left, sum-root.val, path);
        if(root.right!=null)pathSumInner(root.right, sum-root.val, path);

        path.pop();
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null) return resultList;
        Stack<Integer> path = new Stack<Integer>();

        pathSumInner(root, sum, path);

        return resultList;
    }
}
/////////////////////////////////////////////////////////////////////////



}
/*
LeetCode – Path Sum II (Java)

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example, given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
the method returns the following:

[
   [5,4,11,2],
   [5,8,4,5]
]

Analysis

This problem can be converted to be a typical depth-first search problem. A recursive depth-first search algorithm usually requires a recursive method call, a reference to the final result, a temporary result, etc.
 */