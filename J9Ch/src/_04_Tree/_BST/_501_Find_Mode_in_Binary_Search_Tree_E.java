package _04_Tree._BST;
import java.util.*;
import lib.TreeNode;

//  501. Find Mode in Binary Search Tree
//  https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
//  Tree
//  Validate Binary Search Tree
//  3:
public class _501_Find_Mode_in_Binary_Search_Tree_E {
    //1
        private int currVal;
        private int currCount = 0;
        private int maxCount = 0;
        private int modeCount = 0;

        private int[] modes;

        public int[] findMode1(TreeNode root) {
            inorder(root);
            modes = new int[modeCount];
            modeCount = 0;
            currCount = 0;

            inorder(root);
            return modes;
        }

        private void inorder(TreeNode root) {
            if (root == null) return;
            inorder(root.left);
            handleValue(root.val);
            inorder(root.right);
        }

        private void handleValue(int val) {
            if (val != currVal) {
                currVal = val;
                currCount = 0;
            }
            currCount++;
            if (currCount > maxCount) {
                maxCount = currCount;
                modeCount = 1;
            } else if (currCount == maxCount) {
                if (modes != null)
                    modes[modeCount] = currVal;
                modeCount++;
            }
        }

/*    private void inorder(TreeNode root) { TreeNode node = root;
        while (node != null) {
            if (node.left == null) { handleValue(node.val); node = node.right;
            } else {
                TreeNode prev = node.left;
                while (prev.right != null && prev.right != node)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = node;
                    node = node.left; } else {
                    prev.right = null; handleValue(node.val); node = node.right;
                } }
        } }*/

//---------------------------------///////////////////
//2
// Java 4ms Beats 100% Extra O(1) solution - No Map
    Integer prev = null;
    int count = 1;
    int max2 = 0;
    public int[] findMode2(TreeNode root) {
        if (root == null) return new int[0];

        List<Integer> list = new ArrayList<>();
        traverse(root, list);

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); ++i)
            result[i] = list.get(i);
        return result;
    }

    //InOrder
    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverse(root.left, list);

        if (prev != null) {
            if (root.val == prev)
                count++;
            else
                count = 1;
        }
        if (count > max2) {
            max2 = count;
            list.clear();
            list.add(root.val);
        } else if (count == max2) {
            list.add(root.val);
        }
        prev = root.val;

        traverse(root.right, list);
    }


//--------------------------------------------------------------------------------
//3
    //Java AC Solution
    //    O(n) time O(n) space
    /*
        Just travel the tree and count, find the those with max counts.
        Nothing much. Spent 10min on figuring out what is mode....

        If using this method (hashmap), inorder/preorder/postorder gives the same result.
        Because essentially you just travel the entire nodes and count. And BST is not necessary.
        This method works for any tree.
    */

    Map<Integer, Integer> map;
    int max = 0;

    public int[] findMode3(TreeNode root) {
        if(root==null) return new int[0];
        this.map = new HashMap<>();

        inorder3(root);

        List<Integer> list = new LinkedList<>();
        for(int key: map.keySet()){
            if(map.get(key) == max) list.add(key);
        }

        int[] res = new int[list.size()];
        for(int i = 0; i<res.length; i++) res[i] = list.get(i);
        return res;
    }

    private void inorder3(TreeNode node){
        if(node.left!=null) inorder3(node.left);

        map.put(node.val, map.getOrDefault(node.val, 0)+1);
        max = Math.max(max, map.get(node.val));

        if(node.right!=null) inorder3(node.right);
    }

//-------------------------------------------------------------------------------
}
/*
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).


 */
/*

 */
