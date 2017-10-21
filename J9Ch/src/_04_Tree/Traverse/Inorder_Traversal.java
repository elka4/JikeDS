package _04_Tree.Traverse;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
67
Binary Tree Inorder Traversal

 * Created by tianhuizhu on 6/28/17.
 */
public class Inorder_Traversal {
    /**
     * @param root: The root of binary tree.
     * @return: Inorder in ArrayList which contains node values.
     */
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

/////////////////////////////////////////////////////////////////////

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
///////////////////////////////////////////////////////////////////////
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

////////////////////////////////////////////////////////////////

    List<Integer> result = new ArrayList<Integer>();

    public List<Integer> inorderTraversal4(TreeNode root) {
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
////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////


}
