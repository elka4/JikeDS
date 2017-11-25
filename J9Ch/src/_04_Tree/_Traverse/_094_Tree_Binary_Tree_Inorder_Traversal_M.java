package _04_Tree._Traverse;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  94. Binary Tree Inorder Traversal
//  https://leetcode.com/problems/binary-tree-inorder-traversal/description/
//  http://www.lintcode.com/zh-cn/problem/binary-tree-inorder-traversal/
public class _094_Tree_Binary_Tree_Inorder_Traversal_M {

    //https://leetcode.com/problems/binary-tree-inorder-traversal/solution/

    //Approach #1 Recursive Approach [Accepted]
    class Solution01 {
        public List < Integer > inorderTraversal(TreeNode root) {
            List < Integer > res = new ArrayList < > ();
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List < Integer > res) {
            if (root != null) {
                if (root.left != null) {
                    helper(root.left, res);
                }
                res.add(root.val);
                if (root.right != null) {
                    helper(root.right, res);
                }
            }
        }
    }

    //Approach #2 Iterating method using Stack [Accepted]
    public class Solution02 {
        public List < Integer > inorderTraversal(TreeNode root) {
            List < Integer > res = new ArrayList < > ();
            Stack < TreeNode > stack = new Stack < > ();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
            return res;
        }
    }

    //Approach #3 Morris Traversal [Accepted]
    class Solution03 {
        public List < Integer > inorderTraversal(TreeNode root) {
            List < Integer > res = new ArrayList < > ();
            TreeNode curr = root;
            TreeNode pre;
            while (curr != null) {
                if (curr.left == null) {
                    res.add(curr.val);
                    curr = curr.right; // move to next right node
                } else { // has a left subtree
                    pre = curr.left;
                    while (pre.right != null) { // find rightmost
                        pre = pre.right;
                    }
                    pre.right = curr; // put cur after the pre node
                    TreeNode temp = curr; // store cur node
                    curr = curr.left; // move cur to the top of the new tree
                    temp.left = null; // original cur left be null, avoid infinite loops
                }
            }
            return res;
        }
    }


//------------------------------------------------------------------------------
    //Iterative solution in Java - simple and readable
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur!=null || !stack.empty()){
            while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

/*    Concise JAVA solution based on Stack
    Explanation

    The basic idea is referred from here: using stack to simulate the recursion procedure: for each node, travel to its left child until it's left leaf, then pop to left leaf's higher level node A, and switch to A's right branch. Keep the above steps until cur is null and stack is empty. As the following:

    Runtime = O(n): As each node is visited once

    Space = O(n)*/

    public List<Integer> inorderTraversal5(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {// Travel to each node's left child, till reach the left leaf
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop(); // Backtrack to higher level node A
            res.add(cur.val);  // Add the node to the result list
            cur = cur.right;   // Switch to A'right branch
        }
        return res;
    }

//------------------------------------------------------------------------------

    //Java solution, both recursion and iteration
    public List<Integer> inorderTraversal6(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // method 1: recursion

        helper(root, res);
        return res;
    }
        //helper function for method 1
    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }


    public List<Integer> inorderTraversal7(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // method 2: iteration
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

//------------------------------------------------------------------------------
    //Java Two solutions: Recursive ( 1 ms) and Non-recursive ( 2 ms)
    class Solution7{
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list =new ArrayList();
            addNode(list,root);
            return list;
        }
        public void addNode(List<Integer> list,TreeNode root){
            if(root==null) return;
            addNode(list,root.left);
            list.add(root.val);
            addNode(list,root.right);
        }
    }

    class Solution8{
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list =new ArrayList();
            Stack<TreeNode> stack=new Stack();
            if(root==null) return list;
            while(root!=null){
                stack.push(root);
                root=root.left;
                while(root==null){
                    if(stack.empty()) return list;
                    root=stack.pop();
                    list.add(root.val);
                    root=root.right;
                }
            }
            return list;
        }
    }
//------------------------------------------------------------------------------
    // 9Ch
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        TreeNode curt = root;

        while (curt != null || !stack.empty()) {
            while (curt != null) {
                stack.add(curt);
                curt = curt.left;
            }
            curt = stack.pop();
            result.add(curt.val);
            curt = curt.right;
        }
        return result;
    }

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(inorderTraversal(root));
    }

//------------------------------------------------------------------------------

    //Version 0: Non-Recursion (Recommend)
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> preorder = new ArrayList<Integer>();

        if (root == null) {
            return preorder;
        }

        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return preorder;
    }

    @Test
    public void test02(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(inorderTraversal(root));
    }
//------------------------------------------------------------------------------

    public ArrayList<Integer> inorderTraversal3(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> lst = new ArrayList<Integer>();

        if(root == null)
            return lst;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        //define a pointer to track nodes
        TreeNode p = root;

        while(!stack.empty() || p != null){

            // if it is not null, push to stack
            //and go down the tree to left
            if(p != null){
                stack.push(p);
                p = p.left;

                // if no left child
                // pop stack, process the node
                // then let p point to the right
            }else{
                TreeNode t = stack.pop();
                lst.add(t.val);
                p = t.right;
            }
        }

        return lst;
    }

//-------------------------------------------------------------------------//

    List<Integer> result = new ArrayList<Integer>();

    public List<Integer> inorderTraversal9(TreeNode root) {
        if(root !=null){
            helper(root);
        }

        return result;
    }

    public void helper(TreeNode p){
        if(p.left!=null)
            helper(p.left);

        result.add(p.val);

        if(p.right!=null)
            helper(p.right);
    }
//------------------------------------------------------------------------------
}
/*
二叉树的中序遍历

 描述
 笔记
 数据
 评测
给出一棵二叉树,返回其中序遍历

样例
给出二叉树 {1,#,2,3},

   1
    \
     2
    /
   3
返回 [1,3,2].

挑战
你能使用非递归算法来实现么?
 */

/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?


 */
