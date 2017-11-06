package _04_Tree._Build;

import lib.TreeNode;


//
//
//
public class _617_Tree_Merge_Two_Binary_Trees_E {
    public class Solution {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) return null;

            int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
            TreeNode newNode = new TreeNode(val);

            newNode.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
            newNode.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

            return newNode;
        }
    }

    public class Solution2 {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if(t1 == null && t2 == null) return null;
            int sum = 0;
            if(t1 != null) sum+= t1.val;
            if(t2 != null) sum+= t2.val;
            TreeNode root = new TreeNode(sum);
            root.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
            root.right = mergeTrees(t1 == null ? null : t1.right ,t2 == null ? null : t2.right);
            return root;
        }
    }

    class Solution3 {
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if(t1 == null || t2 == null) return t1==null?t2:t1;
            TreeNode root = new TreeNode(t1.val+t2.val);
            root.left = mergeTrees(t1.left,t2.left);
            root.right = mergeTrees(t1.right,t2.right);
            return root;
        }
    }

}
/*

 */
/*

 */
