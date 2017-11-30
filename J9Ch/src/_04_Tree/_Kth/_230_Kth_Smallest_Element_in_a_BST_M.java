package _04_Tree._Kth;
import lib.TreeNode;

import java.util.Stack;

//  230. Kth Smallest Element in a BST
//  https://leetcode.com/problems/kth-smallest-element-in-a-bst/
//  Binary Search Tree
//  Binary Tree Inorder Traversal
//  _671_Tree_Second_Minimum_Node_In_a_Binary_Tree_E
//  8:
public class _230_Kth_Smallest_Element_in_a_BST_M {
    //3 ways implemented in JAVA (Python): Binary Search, in-order iterative & recursive
//-------------------------------------------------------------------------
    //1
    //    1. Binary Search (dfs): most preferable
    public int kthSmallest1(TreeNode root, int k) {
        int count = countNodes(root.left);//先计算树中node总数
        if (k <= count) {                 //如果k小于等于count，就在左树中
            return kthSmallest1(root.left, k);
        } else if (k > count + 1) {        //如果大于count+1，就在右树中找k - (count + 1)个
            return kthSmallest1(root.right, k-1-count); // 1 is counted as current node
        }

        return root.val;                    //否则root就是第k个
    }

    public int countNodes(TreeNode n) { //计算n为root的树中node总数
        if (n == null) return 0;

        return 1 + countNodes(n.left) + countNodes(n.right); //1是root的计数
    }

//-------------------------------------------------------------------------
    //2
    //    2. DFS in-order recursive:
    // better keep these two variables in a wrapper class
    private static int number = 0;
    private static int count2 = 0;

    public int kthSmallest2(TreeNode root, int k) {
        count2 = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count2--;
        if (count2 == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }

//-------------------------------------------------------------------------
    //3
    //    3. DFS in-order iterative:     这个性能不是太好，因为这个是O(n)
    public int kthSmallest3(TreeNode root, int k) {
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

//-------------------------------------------------------------------------
    //4
    public int kthSmallest4(TreeNode root, int k) {
        TreeNodeWithCount rootWithCount = buildTreeWithCount(root);
        return kthSmallest(rootWithCount, k);
    }

    private TreeNodeWithCount buildTreeWithCount(TreeNode root) {
        if (root == null) return null;
        TreeNodeWithCount rootWithCount = new TreeNodeWithCount(root.val);
        rootWithCount.left = buildTreeWithCount(root.left);
        rootWithCount.right = buildTreeWithCount(root.right);
        if (rootWithCount.left != null) rootWithCount.count += rootWithCount.left.count;
        if (rootWithCount.right != null) rootWithCount.count += rootWithCount.right.count;
        return rootWithCount;
    }

    private int kthSmallest(TreeNodeWithCount rootWithCount, int k) {
        if (k <= 0 || k > rootWithCount.count) return -1;
        if (rootWithCount.left != null) {
            if (rootWithCount.left.count >= k) return kthSmallest(rootWithCount.left, k);
            if (rootWithCount.left.count == k-1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k-1-rootWithCount.left.count);
        } else {
            if (k == 1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k-1);
        }
    }

    class TreeNodeWithCount {
        int val;
        int count;
        TreeNodeWithCount left;
        TreeNodeWithCount right;
        TreeNodeWithCount(int x) {val = x; count = 1;};
    }

//-------------------------------------------------------------------------
    //5
    /*Java Solution 1 - Inorder Traversal
    We can inorder traverse the tree and get the kth smallest element. Time is O(n).*/
    public int kthSmallest5(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode p = root;      //其实并不需要指定pointer，直接用root也没问题
        int result = 0;

        while(!stack.isEmpty() || p!=null){
            if(p!=null){    //反正就是把root的左树全部压进去
                stack.push(p);

                p = p.left;
            }else{  //上一步中左树为null
                TreeNode t = stack.pop();

                k--;
                if(k==0) result = t.val;

                p = t.right;
            }
        }

        return result;
    }


//-------------------------------------------------------------------------
    //6
    //Similarly, we can also write the inorder traversal as the following:
    /*Java Solution 2 - Extra Data Structure

    We can let each node track the order, i.e., the number of elements that are less than itself.
    Time is O(log(n)).*/
    public int kthSmallest6(TreeNode root, int k) {
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


//-------------------------------------------------------------------------
    //7
    /*
    Two Easiest In Order Traverse (Java)
    In order traverse for BST gives the natural order of numbers. No need to use array.

    Recursive:
     */
    int count = 0;
    int result = Integer.MIN_VALUE;

    public int kthSmallest7(TreeNode root, int k) {
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

//-------------------------------------------------------------------------
    //8
    //Iterative:
    public int kthSmallest8(TreeNode root, int k) {
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

//-------------------------------------------------------------------------
}
/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
