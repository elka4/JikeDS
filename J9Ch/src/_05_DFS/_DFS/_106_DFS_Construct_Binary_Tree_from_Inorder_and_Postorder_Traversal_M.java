package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
//  106. Construct Binary Tree from Inorder and Postorder Traversal

public class _106_DFS_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_M {

    //My recursive Java code with O(n) time and O(n) space
    /*
    The the basic idea is to take the last element in postorder array as the root, find the position of the root in the inorder array; then locate the range for left sub-tree and right sub-tree and do recursion. Use a HashMap to record the index of root in the inorder array.
     */
    class Solution{
        public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
            if (inorder == null || postorder == null || inorder.length != postorder.length)
                return null;
            HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
            for (int i=0;i<inorder.length;++i)
                hm.put(inorder[i], i);
            return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0,
                    postorder.length-1,hm);
        }

        private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
                                         HashMap<Integer,Integer> hm){
            if (ps>pe || is>ie) return null;
            TreeNode root = new TreeNode(postorder[pe]);
            int ri = hm.get(postorder[pe]);
            TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps+ri-is-1, hm);
            TreeNode rightchild = buildTreePostIn(inorder,ri+1, ie, postorder, ps+ri-is, pe-1, hm);
            root.left = leftchild;
            root.right = rightchild;
            return root;
        }
    }

    class Solution2{
        int pInorder;   // index of inorder array
        int pPostorder; // index of postorder array

        private TreeNode buildTree(int[] inorder, int[] postorder, TreeNode end) {
            if (pPostorder < 0) {
                return null;
            }

            // create root node
            TreeNode n = new TreeNode(postorder[pPostorder--]);

            // if right node exist, create right subtree
            if (inorder[pInorder] != n.val) {
                n.right = buildTree(inorder, postorder, n);
            }

            pInorder--;

            // if left node exist, create left subtree
            if ((end == null) || (inorder[pInorder] != end.val)) {
                n.left = buildTree(inorder, postorder, end);
            }

            return n;
        }

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            pInorder = inorder.length - 1;
            pPostorder = postorder.length - 1;

            return buildTree(inorder, postorder, null);
        }
    }


//    Java iterative solution with explanation
    class Solution3{
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder.length == 0 || postorder.length == 0) return null;
            int ip = inorder.length - 1;
            int pp = postorder.length - 1;

            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode prev = null;
            TreeNode root = new TreeNode(postorder[pp]);
            stack.push(root);
            pp--;

            while (pp >= 0) {
                while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
                    prev = stack.pop();
                    ip--;
                }
                TreeNode newNode = new TreeNode(postorder[pp]);
                if (prev != null) {
                    prev.left = newNode;
                } else if (!stack.isEmpty()) {
                    TreeNode currTop = stack.peek();
                    currTop.right = newNode;
                }
                stack.push(newNode);
                prev = null;
                pp--;
            }

            return root;
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////
public class Jiuzhang {
    private int findPosition(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private TreeNode myBuildTree(int[] inorder, int instart, int inend,
                                 int[] postorder, int poststart, int postend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postend]);
        int position = findPosition(inorder, instart, inend, postorder[postend]);

        root.left = myBuildTree(inorder, instart, position - 1,
                postorder, poststart, poststart + position - instart - 1);
        root.right = myBuildTree(inorder, position + 1, inend,
                postorder, poststart + position - instart, postend - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
}



//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */