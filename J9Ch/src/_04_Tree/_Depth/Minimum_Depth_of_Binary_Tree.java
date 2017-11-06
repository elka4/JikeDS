package _04_Tree._Depth;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

/**
155
Minimum Depth of Binary Tree

 * Created by tianhuizhu on 6/28/17.
 */
public class Minimum_Depth_of_Binary_Tree {

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

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(minDepth(root));
    }
////////////////////////////////////////////////////////////////////////
    public int minDepth22(TreeNode root) {
        if(root == null){
            return 0;
        }

        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> counts = new LinkedList<Integer>();

        nodes.add(root);
        counts.add(1);

        while(!nodes.isEmpty()){
            TreeNode curr = nodes.remove();
            int count = counts.remove();

            if(curr.left == null && curr.right == null){
                return count;
            }

            if(curr.left != null){
                nodes.add(curr.left);
                counts.add(count+1);
            }

            if(curr.right != null){
                nodes.add(curr.right);
                counts.add(count+1);
            }
        }

        return 0;
    }

    ////////////////////////////////////////////////////////////////////////
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }
    @Test
    public void test02(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(maxDepth(root));
    }

////////////////////////////////////////////////////////////////
    public int minDepth2(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.left == null){
            return 1;
        }

        //让权。非法情况下就把结果变得无穷大。
        int left = root.left == null ? Integer.MAX_VALUE : minDepth2(root.left);
        int right = root.right == null ? Integer.MAX_VALUE : minDepth2(root.right);

        return Math.min(left, right) + 1;
    }

    @Test
    public void test03(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(minDepth2(root));
    }


    /*
    1
   / \
  2   3
 / \
4   5
return [1,2,4,5,3]
     */
}
