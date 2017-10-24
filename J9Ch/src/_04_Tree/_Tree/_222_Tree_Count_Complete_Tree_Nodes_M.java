package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _222_Tree_Count_Complete_Tree_Nodes_M {

    class Solution {
        int height(TreeNode root) {
            return root == null ? -1 : 1 + height(root.left); }
        public int countNodes(TreeNode root) { int h = height(root);
            return h < 0 ? 0 :
                    height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                            : (1 << h-1) + countNodes(root.left);
        }
    }




    class Solution2 {
        int height(TreeNode root) {
            return root == null ? -1 : 1 + height(root.left); }
        public int countNodes(TreeNode root) { int nodes = 0, h = height(root); while (root != null) {
            if (height(root.right) == h - 1) { nodes += 1 << h;
                root = root.right;
            } else {
                nodes += 1 << h-1; root = root.left;
            }
            h--; }
            return nodes;
        }
    }


    public int countNodes3(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes3(root.left) + countNodes3(root.right);
    }
}
