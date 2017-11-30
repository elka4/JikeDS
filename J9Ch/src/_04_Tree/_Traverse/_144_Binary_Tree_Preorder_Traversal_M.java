package _04_Tree._Traverse;
import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;
import java.util.*;

//  144. Binary Tree Preorder Traversal
//  https://leetcode.com/problems/binary-tree-preorder-traversal/description/
//  http://www.lintcode.com/zh-cn/problem/binary-tree-preorder-traversal/
public class _144_Binary_Tree_Preorder_Traversal_M {
    //Accepted iterative solution in Java using stack.
    //Note that in this solution only right children are stored to stack.
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> rights = new Stack<TreeNode>();
        while(node != null) {
            list.add(node.val);
            if (node.right != null) {
                rights.push(node.right);
            }
            node = node.left;
            if (node == null && !rights.isEmpty()) {
                node = rights.pop();
            }
        }
        return list;
    }

//------------------------------------------------------------------------------
//    3 Different Solutions
//    Recursive method with List as returning value:
    public List<Integer> preorderTraversal01(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        if(root==null) return pre;
        pre.add(root.val);
        pre.addAll(preorderTraversal01(root.left));
        pre.addAll(preorderTraversal01(root.right));
        return pre;
    }

//    Recursive method with Helper method to have a List as paramater,
// so we can modify the parameter and don't have to instantiate a new List at each recursive call:
    public List<Integer> preorderTraversal02(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        preHelper(root,pre);
        return pre;
    }
    public void preHelper(TreeNode root, List<Integer> pre) {
        if(root==null) return;
        pre.add(root.val);
        preHelper(root.left,pre);
        preHelper(root.right,pre);
    }

    //    Iterative method with Stack:
    public List<Integer> preorderIt03(TreeNode root) {
        List<Integer> pre = new LinkedList<Integer>();
        if(root==null) return pre;
        Stack<TreeNode> tovisit = new Stack<TreeNode>();
        tovisit.push(root);
        while(!tovisit.empty()) {
            TreeNode visiting = tovisit.pop();
            pre.add(visiting.val);
            if(visiting.right!=null) tovisit.push(visiting.right);
            if(visiting.left!=null) tovisit.push(visiting.left);
        }
        return pre;
    }
//------------------------------------------------------------------------------

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                result.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return result;
    }

    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                result.add(node.val);
                stack.push(node.right);
                node = node.left;
            } else {
                node = stack.pop();
            }
        }
        return result;
    }

//------------------------------------------------------------------------------
    // 9Ch
//Version 0: Non-Recursion (Recommend)
public List<Integer> preorderTraversal0(TreeNode root) {
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
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(preorderTraversal(root));
    }

//------------------------------------------------------------------------------
    // 9Ch
    //Version 1: Traverse
    public ArrayList<Integer> preorderTraversal_2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        traverse(root, result);
        return result;
    }

    // 1递归定义：把root为根的preorder加入result里面
    private void traverse(TreeNode root, ArrayList<Integer> result) {
        // 3.递归的出口：极端小的问题
        // 以root为null还是leaf作为出口？
        if (root == null) {
            return;
        }
        // 2.如何拆分为更小的情况（如何扒皮）。一边走一边构造。
        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }

    /* inorder
    traverse(root.left, result);
    result.add(root.val);
    traverse(root.right, result);
     */

    /* postorder
    traverse(root.left, result);
    traverse(root.right, result);
    result.add(root.val);
     */

    @Test
    public void test02(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(preorderTraversal_2(root));
    }

//------------------------------------------------------------------------------
    // 9Ch
    //Version 2: Divide & Conquer
    public ArrayList<Integer> preorderTraversal_3(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        // 递归的出口  // null or leaf? null!
        if (root == null) {
            return result;
        }
        // 递归的拆解：left，right // 先divide，再想每个具体做什么
        // Divide
        ArrayList<Integer> left = preorderTraversal_3(root.left);
        ArrayList<Integer> right = preorderTraversal_3(root.right);

        // Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }

    @Test
    public void test03(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        root.right.setLeftChild(new TreeNode(15));
        root.right.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
        System.out.println(preorderTraversal_3(root));
    }

//---------------------------------/////////////////
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> inorderTraversal4(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode curt = root;
        while (curt != null || !stack.empty()) {
            while (curt != null) {
                stack.add(curt);
                curt = curt.left;
            }
            curt = stack.peek();
            stack.pop();
            result.add(curt.val);
            curt = curt.right;
        }
        return result;
    }
//---------------------------------///////////////////

    public List<Integer> inorderTraversal5(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();

            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

//---------------------------------///////////////////

    public List<Integer> inorderTraversal6(TreeNode root) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        // if (root == null) {
        //     return list;
        // }
        helper(root, list);
        return list;
    }

    private void helper (TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }

//---------------------------------///////////////////

    public ArrayList<Integer> preorderTraversal7(TreeNode root) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();

        if(root == null)
            return returnList;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while(!stack.empty()){
            TreeNode n = stack.pop();
            returnList.add(n.val);

            if(n.right != null){
                stack.push(n.right);
            }
            if(n.left != null){
                stack.push(n.left);
            }

        }
        return returnList;
    }


//------------------------------------------------------------------------------
}
/* inorder
result.addAll(left);
result.add(root.val);
result.addAll(right);*/

/* postorder
result.addAll(left);
result.addAll(right);
result.add(root.val);*/

/*
二叉树的前序遍历

 描述
 笔记
 数据
 评测
给出一棵二叉树，返回其节点值的前序遍历。

样例
给出一棵二叉树 {1,#,2,3},

   1
    \
     2
    /
   3
 返回 [1,2,3].

挑战
你能使用非递归实现么？
 */

/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
 */