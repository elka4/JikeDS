package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
//99. Recover Binary Search Tree

public class _099_DFS_Recover_Binary_Search_Tree_H {

    //No Fancy Algorithm, just Simple and Powerful In-Order Traversal
    public class Solution {

        TreeNode firstElement = null;
        TreeNode secondElement = null;
        // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
        TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {

            // In order traversal to find the two elements
            traverse(root);

            // Swap the values of the two nodes
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }

        private void traverse(TreeNode root) {

            if (root == null)
                return;

            traverse(root.left);

            // Start of "do some business",
            // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
            if (firstElement == null && prevElement.val >= root.val) {
                firstElement = prevElement;
            }

            // If first element is found, assign the second element to the root (refer to 2 in the example above)
            if (firstElement != null && prevElement.val >= root.val) {
                secondElement = root;
            }
            prevElement = root;

            // End of "do some business"

            traverse(root.right);
        }
    }


    //Detail Explain about How Morris Traversal Finds two Incorrect Pointer
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
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

//----------------------------------------------------------------------------
    // 9Ch
    public class Jiuzhang {
        private TreeNode firstElement = null;
        private TreeNode secondElement = null;
        private TreeNode lastElement = new TreeNode(Integer.MIN_VALUE);

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            traverse(root.left);
            if (firstElement == null && root.val < lastElement.val) {
                firstElement = lastElement;
            }
            if (firstElement != null && root.val < lastElement.val) {
                secondElement = root;
            }
            lastElement = root;
            traverse(root.right);
        }

        public void recoverTree(TreeNode root) {
            // traverse and get two elements
            traverse(root);
            // swap
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }


//----------------------------------------------------------------------------

}
/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */