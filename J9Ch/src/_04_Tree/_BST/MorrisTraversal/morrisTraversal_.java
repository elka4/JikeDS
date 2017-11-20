package _04_Tree._BST.MorrisTraversal;

import lib.TreeNode;
import org.junit.Test;

public class morrisTraversal_ {
    public void morrisTraversal(TreeNode root){
        TreeNode temp = null;
        while(root!=null){
            if(root.left!=null){
                // connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if(temp.right!=null){
                    temp.right = null;
                    System.out.println(root.val);
                    root = root.right;
                }else{
                    // construct the threading temp.right = root;
                    root = root.left;
                }
            }else{
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(7);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(5);
        root.left.setLeftChild(new TreeNode(4));
        root.left.setRightChild(new TreeNode(6));
        System.out.println("root: ");
        root.print();
        morrisTraversal(root);
        root.print();
    }
}
