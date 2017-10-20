package _4_Tree.Traverse;

import java.util.ArrayList;
import java.util.Stack;
import lib.*;
/*
Leetcode – Binary Tree Preorder Traversal (Java)

Analysis

Preorder binary tree traversal is a classic interview problem about trees. The key to solve this problem is to understand the following:

What is preorder? (parent node is processed before its children)
Use Stack from Java Core library
The key is using a stack to store left and right children, and push right child first so that it is processed after the left child.


 */
public class Preorder {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
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
}
