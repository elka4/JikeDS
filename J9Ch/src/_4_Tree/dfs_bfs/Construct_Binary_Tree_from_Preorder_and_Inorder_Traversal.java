package _4_Tree.dfs_bfs;

/*
LeetCode – Construct Binary Tree from Preorder and Inorder Traversal (Java)

Given preorder and inorder traversal of a tree, construct the binary tree.

Analysis

Consider the following example:

in-order:   4 2 5 (1) 6 7 3 8
pre-order: (1) 2 4 5  3 7 6 8
From the pre-order array, we know that first element is the root. We can find the root in in-order array. Then we can identify the left and right sub-trees of the root from in-order array.

Using the length of left sub-tree, we can identify left and right sub-trees in pre-order array. Recursively, we can build up the tree.

For this example, the constructed tree is:
 */
public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preStart = 0;
        int preEnd = preorder.length-1;
        int inStart = 0;
        int inEnd = inorder.length-1;

        return construct(preorder, preStart, preEnd, inorder, inStart, inEnd);
    }

    public TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart>preEnd||inStart>inEnd){
            return null;
        }

        int val = preorder[preStart];
        TreeNode p = new TreeNode(val);

        //find parent element index from inorder
        int k=0;
        for(int i=0; i<inorder.length; i++){
            if(val == inorder[i]){
                k=i;
                break;
            }
        }

        p.left = construct(preorder, preStart+1, preStart+(k-inStart), inorder, inStart, k-1);
        p.right= construct(preorder, preStart+(k-inStart)+1, preEnd, inorder, k+1 , inEnd);

        return p;
    }
}
