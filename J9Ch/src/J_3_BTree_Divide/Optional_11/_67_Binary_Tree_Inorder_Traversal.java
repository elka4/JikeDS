package J_3_BTree_Divide.Optional_11;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;
/**
67
Binary Tree Inorder Traversal

 * Created by tianhuizhu on 6/28/17.
 */
public class _67_Binary_Tree_Inorder_Traversal {
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
        lib.TreeNode root = AssortedMethods.createTreeFromArray(arr);

        lib.TreeNode node20 = root.find(20);
        node20.setLeftChild(new lib.TreeNode(15));
        node20.setRightChild(new lib.TreeNode(7));
        System.out.println("root: ");
        root.print();

    }
}
