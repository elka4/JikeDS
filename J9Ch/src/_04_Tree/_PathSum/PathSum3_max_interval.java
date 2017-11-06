package _04_Tree._PathSum;

import StdLib.In;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.concurrent.atomic.*;


// top to down
// Return the Max PathSum On The Subsection Of Complete Path From Root To Leaf

// 从上向下求和， 中间一段和最大

public class PathSum3_max_interval {

    public int maxPathSum(TreeNode root) {
        if (root == null)
          return Integer.MIN_VALUE;

        int[] max = {Integer.MIN_VALUE};

        helper(root, max);

        return max[0];
    }

    private int helper(TreeNode root, int[] max) {
        if (root == null)
          return 0;

        int left = helper(root.left, max);
        int right = helper(root.right, max);

        left = Math.max(left, 0);
        right = Math.max(right, 0);

        max[0] = Math.max(max[0], Math.max(left, right) + root.val);

        return Math.max(left, right) + root.val;
    }

    @Test
    public void test01(){
        int[] arr = {-1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum(root));
    }
    /*
    root:
                   -1
                  / \
                 /   \
                 3   5
                / \ / \
                6 7 8 5

                13
     */

    @Test
    public void test02(){
        int[] arr = {-1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.left.left = new TreeNode(-1);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum(root));
    }
    /*
            root:
                   -1
                  / \
                 /   \
                /     \
               /       \
               3       5
              / \     / \
             /   \   /   \
             6   7   8   5
                    /
                    -1

            13
     */

/////////////////////////////////////////////////////////////////
    int max = Integer.MIN_VALUE;

    public int maxPathSum2(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;

        helper2(root);
        return max;
    }

    private int helper2(TreeNode root) {
        if (root == null)
            return 0;

        int left = helper2(root.left);
        int right = helper2(root.right);

        left = Math.max(left, 0);
        right = Math.max(right, 0);

        max = Math.max(max, Math.max(left, right) + root.val);

        return Math.max(left, right) + root.val;
    }

    @Test
    public void test03(){
        int[] arr = {-1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
    }
    /*
    root:
               1
              / \
             /   \
             3   5
            / \ / \
            6 7 8 5

            14
     */
    @Test
    public void test04(){
        int[] arr = {-1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.left.left = new TreeNode(-1);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum2(root));
    }
    /*
                    root:
                       -1
                      / \
                     /   \
                    /     \
                   /       \
                   3       5
                  / \     / \
                 /   \   /   \
                 6   7   8   5
                        /
                        -1

                13
     */
/////////////////////////////////////////////////////////////////

    public int maxPathSum3(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;

        Integer max = Integer.MIN_VALUE;

        max = helper3(root, max);

        return max;
    }

    private Integer helper3(TreeNode root, Integer max) {
        if (root == null)
            return 0;

        Integer left = helper3(root.left, max);
        Integer right = helper3(root.right, max);

        left = Math.max(left, 0);
        right = Math.max(right, 0);

        max = Math.max(max, Math.max(left, right) + root.val);

        return Math.max(left, right) + root.val;
    }
    @Test
    public void test05(){
        int[] arr = {-1,3,5,6,7,8,5};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(maxPathSum3(root));
    }
    /*
    root:
   -1
  / \
 /   \
 3   5
/ \ / \
6 7 8 5

12
     */
/////////////////////////////////////////////////////////////////

    @Test
    public void test06(){
        Integer x;
        AtomicInteger i = new AtomicInteger(1);
        change(i);
        System.out.println(i.intValue());
    }

    private void change(AtomicInteger x) {
        x.set(9);
    }

/////////////////////////////////////////////////////////////////
    class MutableInteger{ int value;}
    @Test
    public void test07(){
        MutableInteger i = new MutableInteger();
        i.value = 1;
        change7(i);
        System.out.println(i.value);
    }
    private void change7(MutableInteger x) {
        x.value = 9;
    }


/////////////////////////////////////////////////////////////////
}
