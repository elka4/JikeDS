package J_3_Binary_Tree_Divide_Conquer.other.subtree;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**
 * Created by tianhuizhu on 7/8/17.
 * ?м????double
 */
public class _597_Subtree_with_Maximum_Average_my {

    private class ResultType{
        int sum;
        int size;
        ResultType(int sum, int size){
            this.sum = sum;
            this.size = size;
        }
    }
    private TreeNode subTree;
    private ResultType subType;
    /**
     * @param root the root of binary tree
     * @return the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // Write your code here
        // subType = new ResultType(0,0);
        helper(root);
        return subTree;
    }
    private ResultType helper(TreeNode root){
        if(root == null){
            return new ResultType(0,0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        int currentSum = left.sum + right.sum + root.val;
        int currentSize = left.size + right.size + 1;
        double avg = (currentSum * 1.0) / currentSize;
        ResultType currentType = new ResultType(currentSum, currentSize);

        //不能提前计算subType.sum * 1.0， 因为subType一开始是null
        //
        if(subTree == null || avg > subType.sum * 1.0 / subType.size){
            subType = currentType;
            subTree = root;
        }
        return new ResultType(currentSum, currentSize);
    }

    @Test
    public void test01(){
        int[] arr = {1,-5,11,1,2,4,-2};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        TreeNode result = findSubtree2(root);
        System.out.println("root: ");
        result.print();

    }
}
