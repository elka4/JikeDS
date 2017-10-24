package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _106_DFS_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_M {

    //My recursive Java code with O(n) time and O(n) space
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

//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */