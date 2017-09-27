package J_3_BTree_Divide.other.Successor_Iterator;

import lib.AssortedMethods;
import lib.TreeNode;
import org.junit.Test;

/**
 448
 Inorder Successor in Binary Search Tree
 * Created by tianhuizhu on 6/28/17.
 */
public class _448_Inorder_Successor_in_Binary_Search_Tree {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null && root != p) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (root == null) {
            return null;
        }

        if (root.right == null) {
            return successor;
        }

        root = root.right;

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    @Test
    public void test01(){
        int[] arr = {7,5,10};
        TreeNode root = AssortedMethods.createTreeFromArray(arr);

        root.right.setLeftChild(new TreeNode(8));
        root.right.setRightChild(new TreeNode(12));
        System.out.println("root: ");
        root.print();
        inorderSuccessor(root, root).print();
        //System.out.println(inorderSuccessor(root, root).val);
        //System.out.println(inorderSuccessor(root, root.right).val);
        inorderSuccessor(root, root.left).print();
        inorderSuccessor(root, root.right.left).print();
    }

}
