package J_3_BTree_Divide.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 68
 Binary Tree Postorder Traversal

 * Created by tianhuizhu on 6/28/17.
 */
public class _68_Binary_Tree_Postorder_Traversal {
    //Recursive
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (root == null) {
            return result;
        }

        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));

        result.add(root.val);

        return result;
    }

//Iterative
public ArrayList<Integer> postorderTraversal2(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode prev = null; // previously traversed node
    TreeNode curr = root;

    if (root == null) {
        return result;
    }

    stack.push(root);
    while (!stack.empty()) {
        curr = stack.peek();

        // traverse down the tree
        if (prev == null || prev.left == curr || prev.right == curr) {
            if (curr.left != null) {
                stack.push(curr.left);
            } else if (curr.right != null) {
                stack.push(curr.right);
            }

        // traverse up the tree from the left
        } else if (curr.left == prev) {
            if (curr.right != null) {
                stack.push(curr.right);
            }
        } else {

        // traverse up the tree from the right
            result.add(curr.val);
            stack.pop();
        }
        prev = curr;
    }

    return result;
}

    @Test
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();
    }
}
