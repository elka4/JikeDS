package J_3_BTree_Divide.all;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

import java.util.Stack;
/** 86 Binary Search Tree Iterator
 * Created by tianhuizhu on 7/11/17.
 */
public class _86_Binary_Search_Tree_Iterator {


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
    public void test01(){
        int[] arr = {3,9,20};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);
        TreeNode node20 = root.find(20);
        node20.setLeftChild(new TreeNode(15));
        node20.setRightChild(new TreeNode(7));
        System.out.println("root: ");
        root.print();

        BSTIterator itr = new BSTIterator(root);

        while(itr.hasNext()){
            TreeNode node = itr.next();
            node.print();
        }
    }
}
