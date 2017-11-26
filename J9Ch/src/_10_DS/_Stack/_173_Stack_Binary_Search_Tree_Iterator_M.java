package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
import lib.*;

public class _173_Stack_Binary_Search_Tree_Iterator_M {
/*    My solutions in 3 languages with Stack
    I use Stack to store directed left children from root.
    When next() be called, I just pop one element and process its right child as new root.
    The code is pretty straightforward.

            So this can satisfy O(h) memory, hasNext() in O(1) time,
    But next() is O(h) time.

    I can't find a solution that can satisfy both next() in O(1) time, space in O(h).

    Java:*/

    public class BSTIterator1 {
        private Stack<TreeNode> stack = new Stack<TreeNode>();

        public BSTIterator1(TreeNode root) {
            pushAll(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode tmpNode = stack.pop();
            pushAll(tmpNode.right);
            return tmpNode.val;
        }

        private void pushAll(TreeNode node) {
            for (; node != null; stack.push(node), node = node.left);
        }
    }
//--------------------------------------------------------------------------------
    // 9Ch
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    TreeNode next = null;
    void AddNodeToStack(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    // @param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        next = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        if (next != null) {
            AddNodeToStack(next);
            next = null;
        }
        return !stack.isEmpty();
    }

    //@return: return next node
    public TreeNode next() {
        if (!hasNext()) {
            return null;
        }
        TreeNode cur = stack.pop();
        next = cur.right;
        return cur;
    }
}

}
/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */