package J_3_Binary_Tree_Divide_Conquer.Related_2;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
/**
155
Minimum Depth of Binary Tree

 * Created by tianhuizhu on 6/28/17.
 */
public class _155_Minimum_Depth_of_Binary_Tree {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    public int getMin(TreeNode root){
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }


    /*
    1
   / \
  2   3
 / \
4   5
return [1,2,4,5,3]
     */

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();

    }
}
