package _05_DFS._DFS;
import java.util.*;import lib.*;
import org.junit.Test;
public class _111_DFS_Minimum_Depth_of_Binary_Tree_E {
    public class Solution {
        public int minDepth(TreeNode root) {
            if(root == null) return 0;
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

        }
    }


    public int minDepth(TreeNode root) {
        if (root == null)	return 0;
        if (root.left == null)	return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left),minDepth(root.right)) + 1;
    }

    public int minDepth3(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null || root.right == null) {
            return 1 + Math.max(minDepth3(root.left), minDepth3(root.right));
        }
        return 1 + Math.min(minDepth3(root.left), minDepth3(root.right));
    }

    public int minDepth4(TreeNode root) {
        if(root==null) return 0;
        else if(root.left!=null && root.right!=null) return 1+Math.min(minDepth4(root.left), minDepth4(root.right));
        else return 1+minDepth4(root.right)+minDepth4(root.left); // either minDepth(root.left) or minDepth(root.right) is 0 in this case
    }

    public int minDepth5(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth5(root.left);
        int right = minDepth5(root.right);
        if (left == 0) return right + 1;
        if (right == 0) return left + 1;
        return Math.min(left, right) + 1;
    }
    public int minDepth6(TreeNode root) {
        if (root == null) return 0;
        ArrayList<TreeNode> nodes = new ArrayList();
        ArrayList<TreeNode> tmp = new ArrayList();
        nodes.add(root);
        for(int i = 1;;i++) {
            for (TreeNode node:nodes) {
                if (node.left == null && node.right == null) return i;
                if (node.left != null) tmp.add(node.left);
                if (node.right != null) tmp.add(node.right);
            }
            nodes.clear();
            nodes.addAll(tmp);
            tmp.clear();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////




//////////////////////////////////////////////////////////////////////////////////////






}
/*

 */