package _04_Tree._BST;
import lib.*;

import java.util.Stack;


/*  LeetCode 230. Kth Smallest Element in a BST

LeetCode – Kth Smallest Element in a BST (Java)

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it. (1 ≤ k ≤ BST's total elements)
 */
public class Kth_Smallest_Element_in_a_BST {
    /*Java Solution 1 - Inorder Traversal

    We can inorder traverse the tree and get the kth smallest element. Time is O(n).*/

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode p = root;
        int result = 0;

        while(!stack.isEmpty() || p!=null){
            if(p!=null){
                stack.push(p);

                p = p.left;
            }else{
                TreeNode t = stack.pop();

                k--;
                if(k==0) result = t.val;

                p = t.right;
            }
        }

        return result;
    }


//////////////////////////////////////////////////////////////////////////

    //Similarly, we can also write the inorder traversal as the following:

    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while(p!=null){
            stack.push(p);
            p=p.left;
        }
        int i=0;
        while(!stack.isEmpty()){
            TreeNode t = stack.pop();
            i++;

            if(i==k)
                return t.val;

            TreeNode r = t.right;
            while(r!=null){
                stack.push(r);
                r=r.left;
            }

        }

        return -1;
    }
    /*Java Solution 2 - Extra Data Structure

    We can let each node track the order, i.e., the number of elements that are less than itself. Time is O(log(n)).*/


//////////////////////////////////////////////////////////////////////////
/*3 ways implemented in JAVA (Python): Binary Search, in-order iterative & recursive*/

//    Binary Search (dfs): most preferable
    class Solution1{
        public int kthSmallest(TreeNode root, int k) {
            int count = countNodes(root.left);
            if (k <= count) {
                return kthSmallest(root.left, k);
            } else if (k > count + 1) {
                return kthSmallest(root.right, k-1-count); // 1 is counted as current node
            }

            return root.val;
        }

        public int countNodes(TreeNode n) {
            if (n == null) return 0;

            return 1 + countNodes(n.left) + countNodes(n.right);
        }
    }

    //    DFS in-order recursive:
    // better keep these two variables in a wrapper class
    class Solution2{
        private int number = 0;
        private int count = 0;

        public int kthSmallest(TreeNode root, int k) {
            count = k;
            helper(root);
            return number;
        }

        public void helper(TreeNode n) {
            if (n.left != null) helper(n.left);
            count--;
            if (count == 0) {
                number = n.val;
                return;
            }
            if (n.right != null) helper(n.right);
        }
    }

    //    DFS in-order iterative:
    class Solution3{
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> st = new Stack<>();

            while (root != null) {
                st.push(root);
                root = root.left;
            }

            while (k != 0) {
                TreeNode n = st.pop();
                k--;
                if (k == 0) return n.val;
                TreeNode right = n.right;
                while (right != null) {
                    st.push(right);
                    right = right.left;
                }
            }

            return -1; // never hit if k is valid
        }
    }

/*2 yrs later...
    Appreciated everyone reviewing my answers and leaving insightful comments here through the last two years, I've never got chance to reply to all of them, and unfortunately, I no longer write in JAVA and this might be the excuse I won't go back editing my stupid codes any more lol. Below is my Python answer that I just picked up lately, it's more fun and hopefully, easier to understand by its simple structure.

    note: requirement has been changed a bit since last time I visited that the counting could be looked up frequently and BST itself could be altered (inserted/deleted) by multiple times, so that's the main reason that I stored them in an array.*/





//////////////////////////////////////////////////////////////////////////
/*
Two Easiest In Order Traverse (Java)
In order traverse for BST gives the natural order of numbers. No need to use array.

Recursive:
 */

class Solution4{
    int count = 0;
    int result = Integer.MIN_VALUE;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return result;
    }

    public void traverse(TreeNode root, int k) {
        if(root == null) return;
        traverse(root.left, k);
        count ++;
        if(count == k) result = root.val;
        traverse(root.right, k);
    }
}

//Iterative:

class Solution5{
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        int count = 0;

        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);  // Just like recursion
                p = p.left;

            } else {
                TreeNode node = stack.pop();
                if(++count == k) return node.val;
                p = node.right;
            }
        }

        return Integer.MIN_VALUE;
    }
}

//////////////////////////////////////////////////////////////////////////

}
/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */