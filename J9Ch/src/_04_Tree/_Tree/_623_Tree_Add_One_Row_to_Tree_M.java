package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _623_Tree_Add_One_Row_to_Tree_M {
//Simple Java solution - O(N)
//Simply traverse recursively to the depth d - 1 and add nodes accordingly.

    public class Solution {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 1) {
                TreeNode newRoot = new TreeNode(v);
                newRoot.left = root;
                return newRoot;
            }
            add(root, v, d, 1);
            return root;
        }

        private void add(TreeNode node, int v, int d, int currentDepth) {
            if (node == null) {
                return;
            }

            if (currentDepth == d - 1) {
                TreeNode temp = node.left;
                node.left = new TreeNode(v);
                node.left.left = temp;

                temp = node.right;
                node.right = new TreeNode(v);
                node.right.right = temp;
                return;
            }

            add(node.left, v, d, currentDepth + 1);
            add(node.right, v, d, currentDepth + 1);
        }
    }

    public class Solution2 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if(d == 1) {
                TreeNode newRoot = new TreeNode(v);
                newRoot.left = root;
                return newRoot;
            }
            addSubNodes(root,v,d,2);
            return root;
        }
        private void addSubNodes(TreeNode root, int v, int d, int p) {
            if(d == p) {
                TreeNode newLeft = new TreeNode(v);
                TreeNode newRight = new TreeNode(v);
                newLeft.left = root.left;
                root.left = newLeft;
                newRight.right = root.right;
                root.right = newRight;
                return;
            }
            if(root.left != null)//put null check here to avoid 1/2 stack.
                addSubNodes(root.left,v,d,p+1);
            if(root.right != null)
                addSubNodes(root.right,v,d,p+1);
        }
    }

    public class Solution3 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if (d == 0 || d == 1) {
                TreeNode newroot = new TreeNode(v); newroot.left = d == 1 ? root : null; newroot.right = d == 0 ? root : null; return newroot;
            }
            if (root != null && d >= 2) {
                root.left = addOneRow(root.left, v, d > 2 ? d - 1 : 1);
                root.right = addOneRow(root.right, v, d > 2 ? d - 1 : 0); }
            return root; }
    }
}
