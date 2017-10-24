package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _099_Tree_Recover_Binary_Search_Tree_H {


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




    public void morrisTraversal(TreeNode root){ TreeNode temp = null;
        while(root!=null){ if(root.left!=null){
// connect threading for root
            temp = root.left;
            while(temp.right!=null && temp.right != root)
                temp = temp.right;
// the threading already exists
            if(temp.right!=null){
                temp.right = null; System.out.println(root.val); root = root.right;
            }else{
// construct the threading temp.right = root;
                root = root.left;
            } }else{
            System.out.println(root.val);
            root = root.right;
        }
        } }



    public void recoverTree(TreeNode root) { TreeNode pre = null;
        TreeNode first = null, second = null; // Morris Traversal
        TreeNode temp = null; while(root!=null){
            if(root.left!=null){
// connect threading for root
                temp = root.left;
                while(temp.right!=null && temp.right != root)
                    temp = temp.right;
// the threading already exists
                if(temp.right!=null){
                    if(pre!=null && pre.val > root.val){
                        if(first==null){first = pre;second = root;}
                        else{second = root;} }
                    pre = root;
                    temp.right = null;
                    root = root.right; }else{
// construct the threading
                    temp.right = root;
                    root = root.left;
                }
            }else{
                if(pre!=null && pre.val > root.val){
                    if(first==null){first = pre;second = root;}
                    else{second = root;} }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values;
        if(first!= null && second != null){ int t = first.val;
            first.val = second.val; second.val = t;
        }
    }
}