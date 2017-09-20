package _4_Tree.dfs_bfs;

import lib.TreeNode;
/*
LeetCode – Count Complete Tree Nodes (Java)

Given a complete binary tree, count the number of nodes.

Analysis

Steps to solve this problem:
1) get the height of left-most part
2) get the height of right-most part
3) when they are equal, the # of nodes = 2^h -1
4) when they are not equal, recursively get # of nodes from left&right sub-trees

Time complexity is O(h^2).


 */
public class Count_Complete_Tree_Nodes {

    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;

        int left = getLeftHeight(root)+1;
        int right = getRightHeight(root)+1;

        if(left==right){
            return (2<<(left-1))-1;
        }else{
            return countNodes(root.left)+countNodes(root.right)+1;
        }
    }

    public int getLeftHeight(TreeNode n){
        if(n==null) return 0;

        int height=0;
        while(n.left!=null){
            height++;
            n = n.left;
        }
        return height;
    }

    public int getRightHeight(TreeNode n){
        if(n==null) return 0;

        int height=0;
        while(n.right!=null){
            height++;
            n = n.right;
        }
        return height;
    }


//////////////////////////////////////////////////////////////

   /* Concise Java solutions O(log(n)^2)
    Main Solution - 572 ms*/

    class Solution2 {
        int height(TreeNode root) {
            return root == null ? -1 : 1 + height(root.left);
        }
        public int countNodes(TreeNode root) {
            int h = height(root);
            return h < 0 ? 0 :
                    height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                            : (1 << h-1) + countNodes(root.left);
        }
    }

    /*
    Explanation

The height of a tree can be found by just going left. Let a single node tree have height 0. Find the height h of the whole tree. If the whole tree is empty, i.e., has height -1, there are 0 nodes.

Otherwise check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height.

If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).
     */


    class Solution3 {
        int height(TreeNode root) {
            return root == null ? -1 : 1 + height(root.left);
        }
        public int countNodes(TreeNode root) {
            int nodes = 0, h = height(root);
            while (root != null) {
                if (height(root.right) == h - 1) {
                    nodes += 1 << h;
                    root = root.right;
                } else {
                    nodes += 1 << h-1;
                    root = root.left;
                }
                h--;
            }
            return nodes;
        }
    }
//////////////////////////////////////////////////////////////

   /* A Different Solution - 544 ms

    Here's one based on victorlee's C++ solution.*/

    class Solution4 {
        public int countNodes(TreeNode root) {
            if (root == null)
                return 0;
            TreeNode left = root, right = root;
            int height = 0;
            while (right != null) {
                left = left.left;
                right = right.right;
                height++;
            }
            if (left == null)
                return (1 << height) - 1;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }


//////////////////////////////////////////////////////////////
    class Solution5 {
        public int countNodes(TreeNode root) {
            if (root == null)
                return 0;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }


       /* That would be O(n). But... the actual solution has a gigantic optimization. It first walks all the way left and right to determine the height and whether it's a full tree, meaning the last row is full. If so, then the answer is just 2^height-1. And since always at least one of the two recursive calls is such a full tree, at least one of the two calls immediately stops. Again we have runtime O(log(n)^2).*/

//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////

}
