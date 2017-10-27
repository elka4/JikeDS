package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
import lib.*;

public class _094_Stack_Binary_Tree_Inorder_Traversal_M {

//Approach #2 Iterating method using Stack [Accepted]

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

}
