package J_3_BTree_Divide.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 246
 Binary Tree Path Sum II
 * Created by tianhuizhu on 6/28/17.
 */

// any to any, top to bottom

public class _246_Binary_Tree_Path_Sum_II {
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        if (root == null)
            return results;
        findSum(root, target, buffer, 0, results);
        return results;
    }

    public void findSum(TreeNode head, int sum, ArrayList<Integer> buffer,
                        int level, List<List<Integer>> results) {
        if (head == null) return;
        int tmp = sum;
        buffer.add(head.val);
        for (int i = level;i >= 0; i--) {
            tmp -= buffer.get(i);
            if (tmp == 0) {
                List<Integer> temp = new ArrayList<Integer>();
                for (int j = i; j <= level; ++j)
                    temp.add(buffer.get(j));
                results.add(temp);
            }
        }
        findSum(head.left, sum, buffer, level + 1, results);
        findSum(head.right, sum, buffer, level + 1, results);
        buffer.remove(buffer.size() - 1);
    }
/*
    1
   / \
  2   3
 /   /
4   2
for target = 6, return

[
  [2, 4],
  [1, 3, 2]
]

 */
    @Test
    public void test01(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(4));
        root.right.setLeftChild(new TreeNode(2));
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum2(root,6));
    }
    @Test
    public void test02(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(4));
        root.right.setLeftChild(new TreeNode(2));
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum2(root,3));

    }
    @Test
    public void test03(){
        int[] arr = {1,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.left.setLeftChild(new TreeNode(4));
        root.right.setLeftChild(new TreeNode(2));
        System.out.println("root: ");
        root.print();
        System.out.println(binaryTreePathSum2(root,4));

    }

}
