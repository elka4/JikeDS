package _04_Tree.HF3_Algo_DS_II_3BinaryTree;

import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Binary Tree Leaves Order Traversal
public class _2BinaryTreeLeavesOrderTraversal {
    public List<List<Integer>> findLeaves(TreeNode root) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        dfs(root, results);
        return results;
    }

    int dfs(TreeNode root, List<List<Integer>> results) {
        if (root == null) {
            return 0;
        }
        int level = Math.max(dfs(root.left, results), dfs(root.right, results)) + 1;
        int size = results.size();
        if (level > size) {
            results.add(new ArrayList<Integer>());
        }
        results.get(level - 1).add(root.val);
        return level;
    }



////////////////////////////////////////////////////////////////

    // version: 高频题班

    Map<Integer, List<Integer>> depth = new HashMap<>();

    int dfs(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        int d = Math.max(dfs(cur.left), dfs(cur.right)) + 1;

        depth.putIfAbsent(d, new ArrayList<>());
        depth.get(d).add(cur.val);
        return d;
    }

    public List<List<Integer>> findLeaves2(TreeNode root) {
        // Write your code here
        List<List<Integer>> ans = new ArrayList<>();

        int max_depth = dfs(root);

        for (int i = 1; i <= max_depth; i++) {
            ans.add(depth.get(i));
        }
        return ans;
    }

    @Test
    public void test(){
        int[] arr = {2,1,3};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(5));
        root.print();

        List<List<Integer>> result = findLeaves2(root);

        System.out.println(result);
    }
    /*
               1
              / \
             /   \
             2   3
            / \
            4 5

            [[4, 5, 3], [2], [1]]

     */

////////////////////////////////////////////////////////////////

}
/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Have you met this question in a real interview? Yes
Example
Given binary tree:

          1
         / \
        2   3
       / \
      4   5
Returns [[4, 5, 3], [2], [1]].


 */