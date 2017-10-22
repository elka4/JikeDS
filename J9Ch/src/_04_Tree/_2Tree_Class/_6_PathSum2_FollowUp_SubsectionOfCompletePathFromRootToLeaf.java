package _04_Tree._2Tree_Class;


import lib.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// top to bottom

public class _6_PathSum2_FollowUp_SubsectionOfCompletePathFromRootToLeaf {

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

        check(sum, list, res);

        helper(root.left, sum - root.val, list, res);
        helper(root.right, sum - root.val, list, res);

        list.remove(list.size() - 1);
    }

	private void check(int target, List<Integer> list, List<List<Integer>> res) {

        for(int i = list.size() - 1; i >= 0; i--) {
            target -= list.get(i);
            System.out.println(" list " + list);
            System.out.println(" sum " + target);
            if(target == 0){
                System.out.println(" sum == 0 ");
                res.add(new ArrayList<Integer>(list));
            }
        }
     }

    @Test
    public void test01(){
        int[] arr = {1,3,5,6,7,8,5,1,1,1,1,1,1,1,1};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        //ystem.out.println(pathSum(root, 10));
        System.out.println(pathSum(root, 12));
    }
}
