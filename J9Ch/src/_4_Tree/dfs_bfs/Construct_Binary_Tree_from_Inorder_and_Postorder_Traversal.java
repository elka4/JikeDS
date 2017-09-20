package _4_Tree.dfs_bfs;

import lib.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/*
Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Analysis

This problem can be illustrated by using a simple example.

in-order:   4 2 5  (1)  6 7 3 8
post-order: 4 5 2  6 7 8 3  (1)
From the post-order array, we know that last element is the root. We can find the root in in-order array. Then we can identify the left and right sub-trees of the root from in-order array.

Using the length of left sub-tree, we can identify left and right sub-trees in post-order array. Recursively, we can build up the tree.

For this example, the constructed tree is:
 */


public class Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inStart = 0;
        int inEnd = inorder.length - 1;
        int postStart = 0;
        int postEnd = postorder.length - 1;

        return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                              int[] postorder, int postStart, int postEnd) {

        if (inStart > inEnd || postStart > postEnd)
            return null;

        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        int k = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                k = i;
                break;
            }
        }

        root.left = buildTree(inorder, inStart, k - 1,
                postorder, postStart,postStart + k - (inStart + 1));

        // Becuase k is not the length, it it need to -(inStart+1) to
        // get the length
        root.right = buildTree(inorder, k + 1, inEnd,
                postorder, postStart + k- inStart, postEnd - 1);

        // postStart+k-inStart = postStart+k-(inStart+1) +1

        return root;
    }


//////////////////////////////////////////////////////////////


    class Solution2{


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


//////////////////////////////////////////////////////////////

    //Java iterative solution with explanation

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


    /*
    This is my iterative solution, think about "Constructing Binary Tree from inorder and preorder array", the idea is quite similar. Instead of scanning the preorder array from beginning to end and using inorder array as a kind of mark, in this question, the key point is to scanning the postorder array from end to beginning and also use inorder array from end to beginning as a mark because the logic is more clear in this way. The core idea is: Starting from the last element of the postorder and inorder array, we put elements from postorder array to a stack and each one is the right child of the last one until an element in postorder array is equal to the element on the inorder array. Then, we pop as many as elements we can from the stack and decrease the mark in inorder array until the peek() element is not equal to the mark value or the stack is empty. Then, the new element that we are gonna scan from postorder array is the left child of the last element we have popped out from the stack.
     */

//////////////////////////////////////////////////////////////

    //Simple and clean Java solution with comments, recursive.


    class Solution4{
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return buildTree(inorder, inorder.length-1, 0, postorder, postorder.length-1);
        }

        private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder,
                                   int postStart) {
            if (postStart < 0 || inStart < inEnd)
                return null;

            //The last element in postorder is the root.
            TreeNode root = new TreeNode(postorder[postStart]);

            //find the index of the root from inorder. Iterating from the end.
            int rIndex = inStart;
            for (int i = inStart; i >= inEnd; i--) {
                if (inorder[i] == postorder[postStart]) {
                    rIndex = i;
                    break;
                }
            }
            //build right and left subtrees. Again, scanning from the end to find the sections.
            root.right = buildTree(inorder, inStart, rIndex + 1, postorder, postStart-1);
            root.left = buildTree(inorder, rIndex - 1, inEnd, postorder, postStart - (inStart - rIndex) -1);
            return root;
        }
    }



//////////////////////////////////////////////////////////////


    //My JAVA recursive answer, BEAT 92.9%, 2ms


    class Solution5{
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return build(inorder,inorder.length-1,0,postorder,postorder.length-1);
        }

        public TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart){
            if(inEnd > inStart){
                return null;
            }
            TreeNode root = new TreeNode(postorder[postStart]);
            if(inEnd == inStart){
                return root;
            }
            int index = 0;
            // find the index in inorder:
            for(int i = inStart; i >= inEnd; i--){
                if(inorder[i] == root.val){
                    index = i;
                    break;
                }
            }

            root.right = build(inorder,inStart,index+1,postorder,postStart-1);
            root.left = build(inorder,index-1,inEnd,postorder,postStart-(inStart-index)-1);

            return root;
        }
    }

//////////////////////////////////////////////////////////////


    //Concise recursive Java code by making slight modification to the previous problem.

    //Code for Problem 106:

    class Solution6{

        public  TreeNode buildTree(int[] inorder, int[] postorder) {
            return helper(postorder, postorder.length - 1, inorder, 0, inorder.length - 1);
        }

        public  TreeNode helper(int[] postorder, int postStart, int[] inorder, int inStart, int inEnd) {
            if (postStart < 0 || inStart > inEnd)
                return null;
            TreeNode root = new TreeNode(postorder[postStart]);
            int target = inStart;
            while (inorder[target] != postorder[postStart]) target++;
            root.left = helper(postorder, postStart - inEnd + target - 1, inorder, inStart, target - 1);
            root.right = helper(postorder, postStart - 1, inorder, target + 1, inEnd);

            return root;
        }
    }


//////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




}
