package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _114_DFS_Flatten_Binary_Tree_to_Linked_List_M {
    //My short post order traversal Java solution for share
    class Solution{
        private TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null)
                return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }
    }

    class Solutoin2{
        public void flatten(TreeNode root) {
            flatten(root,null);
        }
        private TreeNode flatten(TreeNode root, TreeNode pre) {
            if(root==null) return pre;
            pre=flatten(root.right,pre);
            pre=flatten(root.left,pre);
            root.right=pre;
            root.left=null;
            pre=root;
            return pre;
        }
    }

    public class Solution3 {
        private TreeNode pointer = null;

        public void flatten(TreeNode root) {
            if(root == null)
                return;

            if(pointer != null)
                pointer.right = root;

            pointer = root;

            TreeNode right = root.right;
            flatten(root.left);
            root.left = null;
            flatten(right);
        }
    }
}
