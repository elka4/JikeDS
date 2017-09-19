package HF.HF3_Algorithms_DS_II._3BinaryTree;

//Binary Tree Flipping
public class _1BinaryTreeFlipping {
    public class Solution {
        /**
         * @param root the root of binary tree
         * @return the new root
         */
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            // Write your code here
            if (root == null || root.left == null) {
                return root;
            }
            TreeNode new_root = upsideDownBinaryTree(root.left);
            root.left.left = root.right;
            root.left.right = root;
            root.right = null;
            root.left = null;
            return new_root;
        }
    }

//////////////////////////////////////////////////////////////

    // version: 高频题班
    public class Solution2 {
        /**
         * @param root the root of binary tree
         * @return the new root
         */
        TreeNode newRoot;

        void dfs(TreeNode cur) {
            if (cur.left == null) {
                newRoot = cur;
                return;
            }
            dfs(cur.left);
            cur.left.right = cur;
            cur.left.left = cur.right;
            cur.left = null;            // important
            cur.right = null;
        }

        public TreeNode upsideDownBinaryTree(TreeNode root) {
            // Write your code here
            if (root == null) {
                return null;
            }
            dfs(root);
            return newRoot;
        }
    }

///////////////////////////////////////////////////////////////

}
/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Have you met this question in a real interview? Yes
Example
Given a binary tree {1,2,3,4,5}

    1
   / \
  2   3
 / \
4   5
return the root of the binary tree {4,5,2,#,#,3,1}.

   4
  / \
 5   2
    / \
   3   1
 */