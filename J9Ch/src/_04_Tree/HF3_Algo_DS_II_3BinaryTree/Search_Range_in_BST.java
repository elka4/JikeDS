package _04_Tree.HF3_Algo_DS_II_3BinaryTree;
import lib.*;
import org.junit.Test;

import java.util.*;
 // lintcode    Search Range in Binary Search Tree
//
public class Search_Range_in_BST {
    private ArrayList<Integer> results;
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in increasing order.
     */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        results = new ArrayList<Integer>();
        helper(root, k1, k2);
        return results;
    }

    private void helper(TreeNode root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.val > k1) {
            helper(root.left, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            results.add(root.val);
        }
        if (root.val < k2) {
            helper(root.right, k1, k2);
        }
    }

     @Test
     public void test(){
         int[] arr = {8,20,22};
         TreeNode root = TreeNode.createMinimalBST(arr);
         root.left.setLeftChild(new TreeNode(4));
         root.left.setRightChild(new TreeNode(12));
         root.print();


         System.out.println(searchRange(root, 10, 22));
     }
     /*
                                       20
                                      / \
                                     /   \
                                     8   22
                                    / \
                                    4 12

                                    [12, 20, 22]
      */



//------------------------------------------------------------------------------

/*
实际上是一个inorder的遍历

 */

     public ArrayList<Integer> searchRange2(TreeNode root, int k1, int k2) {
         results = new ArrayList<Integer>();
         helper2(root, k1, k2, results);
         return results;
     }

     private void helper2(TreeNode root, int k1, int k2, ArrayList<Integer> results) {
         if (root == null) {
             return;
         }
         if (root.val > k1) {                       //只要是root比左边届大，就可以向左
             helper2(root.left, k1, k2, results);
         }
         if (root.val >= k1 && root.val <= k2) {    //只要是在范围内
             results.add(root.val);
         }
         if (root.val < k2) {                       //只要是root比右边届小，就可以向右
             helper2(root.right, k1, k2, results);
         }
     }

     @Test
     public void test2(){
         int[] arr = {8,20,22};
         TreeNode root = TreeNode.createMinimalBST(arr);
         root.left.setLeftChild(new TreeNode(4));
         root.left.setRightChild(new TreeNode(12));
         root.print();

         System.out.println(searchRange2(root, 10, 22));
     }
     /*
                       20
                      / \
                     /   \
                     8   22
                    / \
                    4 12

                    [12, 20, 22]
      */
//------------------------------------------------------------------------------


//------------------------------------------------------------------------------

}
/*
Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. Find all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST. Return all the keys in ascending order.

Have you met this question in a real interview? Yes
Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].

    20
   /  \
  8   22
 / \
4   12
 */