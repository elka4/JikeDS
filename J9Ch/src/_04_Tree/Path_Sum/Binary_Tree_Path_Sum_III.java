package _04_Tree.Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 472
 Binary Tree Path Sum III
 * Created by tianhuizhu on 6/28/17.
 */

//  any to any
public class Binary_Tree_Path_Sum_III {

    public List<List<Integer>> binaryTreePathSum3( TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<>();
        dfs(root, target, results);
        return results;
    }

    public void dfs( TreeNode root, int target, List<List<Integer>> results) {
        if (root == null)
            return;

        List<Integer> path = new ArrayList<Integer>();
        findSum(root, null, target, path, results);

        dfs(root.left, target, results);
        dfs(root.right, target, results);
    }

    public void findSum( TreeNode root,  TreeNode father, int target,
                        List<Integer> path, List<List<Integer>> results) {
        path.add(root.val);
        target -= root.val;

        if (target == 0) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            Collections.addAll(tmp,  new  Integer[path.size()]);
            Collections.copy(tmp, path);
            results.add(tmp);
        }

        if (root.parent != null && root.parent != father)
            findSum(root.parent, root, target, path, results);

        if (root.left != null && root.left  != father)
            findSum(root.left, root, target, path, results);

        if (root.right != null && root.right != father)
            findSum(root.right, root, target, path, results);

        path.remove(path.size() - 1);
    }



    @Test
    public void test01(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.parent = root;
        root.right.parent = root;
        root.left.left.parent = root.left.left;
        System.out.println("root: ");
        root.print();
        List<List<Integer>> result = binaryTreePathSum3(root, 6);
        System.out.println(result);
    }
/*
            root:
               1
              / \
             /   \
             2   3
            /
            4

            [[2, 1, 3], [2, 4], [3, 1, 2]]
 */
//////////////////////////////////////////////////////////////////////////////

    /*
    root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

          10
         /  \
        5   -3
       / \    \
      3   2   11
     / \   \
    3  -2   1

    Return 3. The paths that sum to 8 are:

    1.  5 -> 3
    2.  5 -> 2 -> 1
    3. -3 -> 11
     */
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }

    public int helper(TreeNode root, int currSum, int target,
                      HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum)
                + helper(root.right, currSum, target, preSum);

        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
    @Test
    public void test02(){
        int[] arr = {10,5,-3,3,2,0,11,3,-1,0,1};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 7));
    }
    /* 7
    root:
       10
      / \
     /   \
    /     \
   /       \
   5       -3
  / \     / \
 /   \   /   \
 3   2   0   11
/ \ / \
3 -1 0 1

5
     */

//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////
}
