package _4_Tree.Build_Tree;

import lib.TreeNode;
import org.junit.Test;
import java.util.*;

// Construct Binary Tree
public class Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
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
        return myBuildTree(inorder, 0, inorder.length - 1, 
        					   postorder, 0, postorder.length - 1);
    }

    @Test
    public void test01(){
        int[] postorder = {1,3,2};
        int[] inorder = {1,2,3};
        buildTree(postorder, inorder).print();
    }

//////////////////////////////////////////////////////////////////////////////////////
    //My recursive Java code with O(n) time and O(n) space
    /*
    The the basic idea is to take the last element in postorder array as the root, find the position of the root in the inorder array; then locate the range for left sub-tree and right sub-tree and do recursion. Use a HashMap to record the index of root in the inorder array.
     */
    public TreeNode buildTreePostIn2(int[] inorder, int[] postorder) {
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
//////////////////////////////////////////////////////////////////////////////////////
// Java iterative solution with explanation
    public TreeNode buildTree3(int[] inorder, int[] postorder) {
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
//////////////////////////////////////////////////////////////////////////////////////


}

/*Given inorder and postorder traversal of a tree, construct the binary tree.

 Notice

You may assume that duplicates do not exist in the tree.

Have you met this question in a real interview? Yes
Example
Given inorder [1,2,3] and postorder [1,3,2], return a tree:

  2
 / \
1   3
Tags 
Binary Tree*/