package _04_Tree.Top_Tree._2Tree_Class;


import lib.*;
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


    private void helper(TreeNode root, int sum, List<Integer> list,
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

////////////////////////////////////////////////////////////////////

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
}
