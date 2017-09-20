package _4_Tree.traveral;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
Leetcode – Binary Tree Postorder Traversal (Java)

Among preoder, inorder and postorder binary tree traversal problems, postorder traversal is the most complicated one.

For example, for the following tree, the post order traversal returns {4, 6, 5, 2, 3, 1}.
binary-tree-postorder-traversal

Java Solution 1

The key to to iterative postorder traversal is the following:

The order of "Postorder" is: left child -> right child -> parent node.
Find the relation between the previously visited node and the current node
Use a stack to track nodes
As we go down the tree to the lft, check the previously visited node. If the current node is the left or right child of the previous node, then keep going down the tree, and add left/right node to stack when applicable. When there is no children for current node, i.e., the current node is a leaf, pop it from the stack. Then the previous node become to be under the current node for next loop. You can using an example to walk through the code.
 */
public class Postorder {

    public ArrayList<Integer> postorderTraversal(TreeNode root) {

        ArrayList<Integer> lst = new ArrayList<Integer>();

        if(root == null)
            return lst;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        TreeNode prev = null;
        while(!stack.empty()){
            TreeNode curr = stack.peek();

            // go down the tree.
            //check if current node is leaf, if so, process it and pop stack,
            //otherwise, keep going down
            if(prev == null || prev.left == curr || prev.right == curr){
                //prev == null is the situation for the root node
                if(curr.left != null){
                    stack.push(curr.left);
                }else if(curr.right != null){
                    stack.push(curr.right);
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }

                //go up the tree from left node
                //need to check if there is a right child
                //if yes, push it to stack
                //otherwise, process parent and pop stack
            }else if(curr.left == prev){
                if(curr.right != null){
                    stack.push(curr.right);
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }

                //go up the tree from right node
                //after coming back from right node, process parent node and pop stack.
            }else if(curr.right == prev){
                stack.pop();
                lst.add(curr.val);
            }

            prev = curr;
        }

        return lst;
    }

////////////////////////////////?

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        if(root==null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            if(temp.left==null && temp.right==null) {
                TreeNode pop = stack.pop();
                res.add(pop.val);
            }
            else {
                if(temp.right!=null) {
                    stack.push(temp.right);
                    temp.right = null;
                }

                if(temp.left!=null) {
                    stack.push(temp.left);
                    temp.left = null;
                }
            }
        }

        return res;
    }
}
