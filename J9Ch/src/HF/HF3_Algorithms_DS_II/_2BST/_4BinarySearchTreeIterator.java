package HF.HF3_Algorithms_DS_II._2BST;

import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;

//Binary search tree iterator
public class _4BinarySearchTreeIterator {
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

    @Test
    public void test(){
        int[] arr = {1,10,11};
        TreeNode root = TreeNode.createMinimalBST(arr);
        root.left.setRightChild(new TreeNode(6));
        root.right.setRightChild(new TreeNode(12));
        root.print();

        BSTIterator itr = new BSTIterator(root);
//        itr.AddNodeToStack(root);

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
/*
Design an iterator over a binary search tree with the following rules:

Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.
Have you met this question in a real interview? Yes
Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12
 */