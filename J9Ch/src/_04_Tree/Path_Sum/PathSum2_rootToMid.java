package _04_Tree.Path_Sum;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// root to bottom
// FollowUp_SubsectionOfCompletePathFromRootToLeaf
// 从根向下加和， 到中间某点是否等于sum
public class PathSum2_rootToMid {

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

        // 每向下走到一个node，就从这里向上检查是否list sum为target
        check(sum, list, res);

        helper(root.left, sum - root.val, list, res);
        helper(root.right, sum - root.val, list, res);

        list.remove(list.size() - 1);
    }

    // list从下往上逐个从target中减当前value，如果能得到0，将这个list加入result
	private void check(int target, List<Integer> list, List<List<Integer>> res) {

        for(int i = list.size() - 1; i >= 0; i--) {
            target -= list.get(i);
//            System.out.println(" list " + list);
//            System.out.println(" sum " + target);
            if(target == 0){
//                System.out.println(" sum == 0 ");
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
    /*
       1
      / \
     /   \
    /     \
   /       \
   3       5
  / \     / \
 /   \   /   \
 6   7   8   5
/ \ / \ / \ / \
1 1 1 1 1 1 1 1

[[1, 3, 7, 1], [1, 3, 7, 1], [1, 5, 5, 1], [1, 5, 5, 1]]
     */


    @Test
    public void test02(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 11));
    }
/*
   1
  / \
 /   \
 3   5
/ \ / \
6 7 8 5
[[1, 3, 7], [1, 5, 5]]
 */

    @Test
    public void test03(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 10));
    }
    /*
   1
  / \
 /   \
 3   5
/ \ / \
6 7 8 5
[[1, 3, 6]]
     */

    @Test
    public void test04(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 6));
    }
    /*
   1
  / \
 /   \
 3   5
/ \ / \
6 7 8 5

[[1, 5]]
     */

    @Test
    public void test05(){
        int[] arr = {1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(pathSum(root, 4));
    }
    // [[1, 3]]
/////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////



}
