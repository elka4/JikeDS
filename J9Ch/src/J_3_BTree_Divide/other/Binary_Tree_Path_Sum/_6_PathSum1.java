package J_3_BTree_Divide.other.Binary_Tree_Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//root to leaf, 所有node的value和为sum， 返回所有的path
public class _6_PathSum1 {

  public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        List<Integer> list = new ArrayList<Integer>();
        helper(root, sum, list, res);

        return res;
    }

    private void helper00(TreeNode root, int sum, List<Integer> list,
                        List<List<Integer>> res) {

        if (root == null) {
            return;
        }

        list.add(root.val);

        //叶子节点，且叶子节点的value和剩余在sum中的值相等时，
        // 将list生成新的list并加入到结果中
        if (sum == root.val && root.left == null && root.right == null) {
            res.add(new ArrayList<Integer>(list));

        } else {
            helper(root.left, sum - root.val, list, res);
            helper(root.right, sum - root.val, list, res);
        }
        list.remove(list.size() - 1);
    }


    private void helper(TreeNode root, int sum, List<Integer> list,
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
    }


    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 11));
    }

    @Test
    public void test02(){
        int[] arr = {1,3,5,6,7,4,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 10));
    }

    @Test
    public void test03(){
        int[] arr = {1,2,4,2,3};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 5));
    }


//------------------------------------------------------------------------------////

    // 9Ch

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




//------------------------------------------------------------------------------////




//------------------------------------------------------------------------------////



}
