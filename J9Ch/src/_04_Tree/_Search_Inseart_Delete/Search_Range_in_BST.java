package _04_Tree._Search_Inseart_Delete;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;

public class Search_Range_in_BST {
    private ArrayList<Integer> results;
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in increasing order.
     */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        results = new ArrayList<>();
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
    public void test01(){
        int[] arr = {5,3,7,1,2,6,8};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        System.out.println("root: ");
        root.print();
        System.out.println(searchRange(root, 3,7));
    }

    /*
    root:
   5
  / \
 /   \
 3   7
/ \ / \
1 2 6 8

[3, 5, 6, 7]
     */
}


/*
 * Given two values k1 and k2 (where k1 < k2) and a root pointer
 *  to a Binary Search Tree. Find all the keys of tree in range 
 *  k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key
 *   of given BST. Return all the keys in ascending order.

Have you met this question in a real interview? Yes
Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].

    20
   /  \
  8   22
 / \
4   12
Tags 
Binary Search Tree Binary Tree
Related Problems 
Easy First Position of Target 32 %
Medium Binary Tree Serialization 19 %
 * */