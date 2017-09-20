package _4_Tree.BST;

import lib.*;
import org.junit.Test;

import java.util.Stack;

public class Binary_Search_Tree_Iterator {
    public class BSTIterator {
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<TreeNode>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            int result = node.val;
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }
    }

    @Test
    public void test(){
        int[] arr = {1,2,3,4,5,6,7};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.print();

        BSTIterator itr = new BSTIterator(root);

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }

/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////





/////////////////////////////////////////////////////////////////

}
